package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.model.azuretoken.AzureToken;
import co.com.bancolombia.model.azuretoken.exception.AzureErrorException;
import co.com.bancolombia.model.azuretoken.gateways.AzureTokenService;
import co.com.bancolombia.secretconsumer.AzureSecuritySecret;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AzureRestConsumer implements AzureTokenService {

    @Autowired
    @Qualifier("azureClient")
    private WebClient client;

    @Autowired
    @Qualifier("AzureSecuritySecret")
    private AzureSecuritySecret azureSecurity;

    @Override
    public Mono<AzureToken> getAzureToken() {
        log.info("Getting azure token oauth...");

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", azureSecurity.getClientId());
        formData.add("grant_type", azureSecurity.getGrantType());
        formData.add("scope", azureSecurity.getScope());
        formData.add("client_secret", azureSecurity.getClientSecret());

        return client.post()
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(AzureResponse.class)
                .onErrorMap(ex -> new AzureErrorException(ex.getMessage()))
                .flatMap(res -> Mono.just(buildAzureTokenResponse(res)));
    }

    private AzureToken buildAzureTokenResponse(AzureResponse resAzure) {
        return AzureToken.builder()
                .tokenType(resAzure.getTokenType())
                .expiresIn(resAzure.getExpiresIn())
                .extExpiresIn(resAzure.getExtExpiresIn())
                .accessToken(resAzure.getAccessToken())
                .build();
    }
}
