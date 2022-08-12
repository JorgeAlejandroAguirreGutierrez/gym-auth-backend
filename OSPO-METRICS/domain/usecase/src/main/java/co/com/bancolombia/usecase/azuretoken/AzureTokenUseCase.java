package co.com.bancolombia.usecase.azuretoken;

import co.com.bancolombia.model.azuretoken.AzureToken;
import co.com.bancolombia.model.azuretoken.gateways.AzureTokenService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AzureTokenUseCase {

    private final AzureTokenService azureTokenService;

    public Mono<AzureToken> getAzureToken() {
        return azureTokenService.getAzureToken();
    }
}
