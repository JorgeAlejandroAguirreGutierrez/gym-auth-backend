package co.com.bancolombia.consumer;

import co.com.bancolombia.binstash.model.api.ObjectCache;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.secretconsumer.SecretGithubResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.cache.CacheMono;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.io.IOException;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

@Builder
public class RestConsumerToken {
    private static final Logger logger = LoggerFactory.getLogger(RestConsumerToken.class);
    private final Mono<SecretGithubResponse> secret;
    private final String issuer;
    private final String installID;
    private int expiredAfter;
    private final WebClient client;
    @Autowired
    @Qualifier("githubCache")
    private final ObjectCache<String> cache;
    @Autowired
    private ObjectMapper mapper;
    public Mono<String> getTokenValue(String tokenName) {
        return CacheMono
                .lookup(k -> cache.get(k, String.class).map(Signal::next), tokenName)
                .onCacheMissResume(buildToken())
                .andWriteWith((k, sig) -> cache.save(k, sig.get(), expiredAfter).then());
    }
    public Mono<String> buildToken() {
        return secret.flatMap(secret -> {
            try {
                java.security.Security.addProvider(
                        new org.bouncycastle.jce.provider.BouncyCastleProvider()
                );
                PemReader pemReader = new PemReader(new StringReader(buildRSAKey(secret.getKeyrsa())));
                PemObject pemObject = pemReader.readPemObject();
                KeyFactory factory = KeyFactory.getInstance("RSA");

                byte[] content = pemObject.getContent();
                PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(content);
                RSAPrivateKey privateKey = null;
                privateKey = (RSAPrivateKey) factory.generatePrivate(privateKeySpec);

                Date dateIssuedAt = new Date(System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateIssuedAt);
                calendar.add(Calendar.MINUTE, 10);
                Date dateExpiration = calendar.getTime();

                String payload = Jwts.builder()
                        .setIssuer(issuer)
                        .setIssuedAt(dateIssuedAt)
                        .setExpiration(dateExpiration)
                        .signWith(SignatureAlgorithm.RS256, privateKey).compact();

                return client
                        .post()
                        .uri(uriBuilder -> uriBuilder.path(Enums.GithubUri.GITHUB_APP_TOKEN.value)
                                .build(installID))
                        .headers(h -> h.setBearerAuth(payload))
                        .exchangeToMono(res -> res.bodyToMono(String.class).map(this::mapDataToken));
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private String mapDataToken(String string){
        try {
            JsonNode jsonNode = mapper.readTree(string);
            return jsonNode.get("token").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private String buildRSAKey(String key){
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN RSA PRIVATE KEY-----\n");
        result.append(key);
        result.append("\n-----END RSA PRIVATE KEY-----");
        return result.toString();
    }
}
