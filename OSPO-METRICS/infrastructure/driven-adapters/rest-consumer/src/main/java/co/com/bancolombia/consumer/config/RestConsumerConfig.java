package co.com.bancolombia.consumer.config;

import co.com.bancolombia.binstash.LocalCacheFactory;
import co.com.bancolombia.binstash.model.api.ObjectCache;
import co.com.bancolombia.consumer.RestConsumerToken;
import co.com.bancolombia.secretconsumer.SecretGithubResponse;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Configuration
public class RestConsumerConfig {
    private static final Logger logger = LoggerFactory.getLogger(RestConsumerConfig.class);
    @Value("${adapter.restconsumer.github.url}")
    private String _Url;
    @Value("${adapter.restconsumer.github.timeout}")
    private int _Timeout;
    @Value("${adapter.restconsumer.github.mimeType}")
    private String _MimeType;
    @Value("${adapter.restconsumer.github.userAgentName}")
    private String _UserAgentName;
    @Value("${adapter.restconsumer.github.token.issuer}")
    private String _Issuer;
    @Value("${adapter.restconsumer.github.token.installID}")
    private String _InstallID;
    @Value("${adapter.restconsumer.github.token.expiredAfter}")
    private int _ExpiredAfter;
    @Autowired
    @Qualifier("secretGithub")
    private Mono<SecretGithubResponse> _Secret;
    @Bean("githubSecret")
    public Mono<SecretGithubResponse> getSecret() {
        return _Secret;
    }
    @Bean("githubCache")
    public ObjectCache<String> getCache(LocalCacheFactory<String> localCacheFactory) {
        return localCacheFactory.newObjectCache();
    }
    @Bean(name = "githubClient")
    public WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(_Url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, _MimeType)
                .defaultHeader(HttpHeaders.USER_AGENT, _UserAgentName)
                .clientConnector(getClientHttpConnector("poolClientGithub"))
                .exchangeStrategies(
                        ExchangeStrategies.builder()
                                .codecs(conf -> conf
                                        .defaultCodecs()
                                        .maxInMemorySize(16 * 1024 * 1024))
                .build())
                .filter(logRequest())
                .build();
    }
    @Bean(name = "githubClientToken")
    public WebClient getWebClientToken() {
        return WebClient.builder()
                .baseUrl(_Url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, _MimeType)
                .defaultHeader(HttpHeaders.USER_AGENT, _UserAgentName)
                .clientConnector(getClientHttpConnector("poolClientGithubToken"))
                .exchangeStrategies(
                        ExchangeStrategies.builder()
                                .codecs(conf -> conf
                                        .defaultCodecs()
                                        .maxInMemorySize(16 * 1024 * 1024))
                                .build())
                .filter(logRequest())
                .build();
    }
    @Bean(name = "githubToken")
    public RestConsumerToken getRestConsumerToken() {
        return RestConsumerToken.builder()
                .issuer(_Issuer)
                .installID(_InstallID)
                .expiredAfter(_ExpiredAfter)
                .secret(_Secret)
                .client(getWebClientToken())
                .build();
    }
    private ClientHttpConnector getClientHttpConnector(String poolConnection) {
        ConnectionProvider connectionProvider = ConnectionProvider.builder(poolConnection)
                .maxConnections(100)
                .pendingAcquireMaxCount(100)
                .build();
        Duration responseTimeout = Duration.ofMillis(_Timeout);
        return new ReactorClientHttpConnector(HttpClient.create(connectionProvider)
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .compress(true)
                .keepAlive(true)
                .option(CONNECT_TIMEOUT_MILLIS, _Timeout)
                .responseTimeout(responseTimeout)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(_Timeout, MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(_Timeout, MILLISECONDS));
                }));
    }
    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }
}
