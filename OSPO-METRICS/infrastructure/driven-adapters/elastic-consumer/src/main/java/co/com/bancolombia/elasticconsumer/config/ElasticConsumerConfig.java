package co.com.bancolombia.elasticconsumer.config;

import co.com.bancolombia.secretconsumer.SecretResponse;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Configuration
@Slf4j
public class ElasticConsumerConfig {

    @Value("${adapter.elasticconsumer.url}")
    private String url;
    @Value("${adapter.elasticconsumer.timeout}")
    private int timeout;

    @Autowired
    @Qualifier("secretOpenSearch")
    private SecretResponse secretOpenSearch;

    @Bean(name = "openSearchClient")
    public WebClient getWebClient() {
        log.debug("Loading secrets for: " + (secretOpenSearch != null ? secretOpenSearch.getUsername():""));
        return WebClient.builder()
            .baseUrl(url)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeaders(httpHeaders -> httpHeaders
                    .setBasicAuth(secretOpenSearch.getUsername(), secretOpenSearch.getPassword()))
            .clientConnector(getClientHttpConnector("poolClientElastic"))
            .build();
    }

    private ClientHttpConnector getClientHttpConnector(String poolConnection) {
        ConnectionProvider connectionProvider = ConnectionProvider.builder(poolConnection)
                .maxConnections(1000000)
                .pendingAcquireMaxCount(1000000)
                .build();
        Duration responseTimeout = Duration.ofMillis(timeout);
        return new ReactorClientHttpConnector(HttpClient.create(connectionProvider)
                .secure(sslContextSpec -> sslContextSpec.sslContext(initSSLContext()))
                .compress(true)
                .keepAlive(true)
                .option(CONNECT_TIMEOUT_MILLIS, timeout)
                .responseTimeout(responseTimeout)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(timeout, MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(timeout, MILLISECONDS));
                }));
    }

    private SslContext initSSLContext() {
        try {
            return SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();
        } catch(Exception ex) {
            return null;
        }
    }
}
