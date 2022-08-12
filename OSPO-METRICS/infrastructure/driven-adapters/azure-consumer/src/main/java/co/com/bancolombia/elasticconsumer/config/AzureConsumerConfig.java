package co.com.bancolombia.elasticconsumer.config;

import co.com.bancolombia.secretconsumer.AzureSecuritySecret;
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

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Configuration
@Slf4j
public class AzureConsumerConfig {

    @Value("${adapter.azureconsumer.timeout}")
    private int timeout;

    @Autowired
    @Qualifier("AzureSecuritySecret")
    private AzureSecuritySecret azureSecurity;

    @Bean(name = "azureClient")
    public WebClient getWebClient() {
        log.info("Building client for azure token oauth...");
        return WebClient.builder()
            .baseUrl(azureSecurity.getTokenUri())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .clientConnector(getClientHttpConnector())
            .build();
    }

    private ClientHttpConnector getClientHttpConnector() {
        /* IF YO REQUIRE APPEND SSL CERTIFICATE SELF SIGNED */
        return new ReactorClientHttpConnector(HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(initSSLContext()))
                .compress(true)
                .keepAlive(true)
                .option(CONNECT_TIMEOUT_MILLIS, timeout)
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
