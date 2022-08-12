package co.com.bancolombia.model.azuretoken.gateways;

import co.com.bancolombia.model.azuretoken.AzureToken;
import reactor.core.publisher.Mono;

public interface AzureTokenService {

    Mono<AzureToken> getAzureToken();
}
