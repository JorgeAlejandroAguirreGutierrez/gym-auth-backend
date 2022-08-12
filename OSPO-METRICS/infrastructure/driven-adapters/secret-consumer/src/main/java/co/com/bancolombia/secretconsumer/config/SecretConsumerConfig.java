package co.com.bancolombia.secretconsumer.config;

import co.com.bancolombia.secretconsumer.AzureSecuritySecret;
import co.com.bancolombia.secretconsumer.SecretDBResponse;
import co.com.bancolombia.secretconsumer.SecretGithubResponse;
import co.com.bancolombia.secretconsumer.SecretResponse;
import co.com.bancolombia.secretsmanager.config.AWSSecretsManagerConfig;
import co.com.bancolombia.secretsmanager.connector.AWSSecretManagerConnectorAsync;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.regions.Region;

@Configuration
public class SecretConsumerConfig {

    @Value("${aws.secret.opensearch}")
    private String secretOpenSearch;

    @Value("${aws.secret.github}")
    private String secretGithub;

    @Value("${aws.secret.metric}")
    private String secretMetric;

    @Value("${aws.secret.azure-security}")
    private String azureSecurity;

    @Value("${aws.secret.amazon-mq}")
    private String secretMq;

    public AWSSecretManagerConnectorAsync secretConfig() {
        AWSSecretsManagerConfig config = AWSSecretsManagerConfig.builder()
                .region(Region.US_EAST_1) //define your region
                .cacheSeconds(600)  //define your cache time
                .cacheSize(300) //define your cache size
                .endpoint("http://localhost:4566") // Override the enpoint
                .build();
        AWSSecretManagerConnectorAsync connector = new AWSSecretManagerConnectorAsync(config);
        return connector;
    }

    @Bean(name = "secretOpenSearch")
    public SecretResponse secretOpenSearch() {
        Mono<SecretResponse> secretOpenSearchResponse= secretConfig().getSecret(secretOpenSearch, SecretResponse.class);
        return secretOpenSearchResponse.block();
    }

    @Bean(name = "secretGithub")
    public Mono<SecretGithubResponse> secretGithub() {
        return secretConfig().getSecret(secretGithub, SecretGithubResponse.class);
    }

    @Bean(name = "secretMetric")
    public SecretDBResponse secretMetric() {
        Mono<SecretDBResponse> secretDBResponseMono = secretConfig().getSecret(secretMetric, SecretDBResponse.class);
        return secretDBResponseMono.block();
    }

    @Bean(name = "AzureSecuritySecret")
    public AzureSecuritySecret azureSecurity() {
        Mono<AzureSecuritySecret> azureSecret = secretConfig().getSecret(azureSecurity, AzureSecuritySecret.class);
        return azureSecret.block();
    }

    @Bean(name = "secretMq")
    public SecretResponse secretMq() {
        Mono<SecretResponse> mq = secretConfig().getSecret(secretMq, SecretResponse.class);
        return mq.block();
    }
}
