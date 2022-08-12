package co.com.bancolombia.api;

import co.com.bancolombia.model.azuretoken.AzureToken;
import co.com.bancolombia.model.opensearchservice.HealthDummyResponse;
import co.com.bancolombia.model.opensearchservice.exception.HealthDummyErrorException;
import co.com.bancolombia.model.opensearchservice.exception.OpenSearchErrorException;
import co.com.bancolombia.model.opensearchservice.OpenSearchResponse;
import co.com.bancolombia.opensearchhelper.OpenSearchMapperHelper;
import co.com.bancolombia.usecase.azuretoken.AzureTokenUseCase;
import co.com.bancolombia.usecase.opensearch.OpenSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    @Autowired
    private OpenSearchMapperHelper helper;

    private final OpenSearchUseCase openSearch;

    private final AzureTokenUseCase azureService;

    public Mono<ServerResponse> consultIndexStructure(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("");
    }
    public Mono<ServerResponse> consultByIndexName(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(serverRequest.pathVariable("index"))
                            .flatMap(helper::getOpenSearchRequest)
                            .flatMap(openSearch::consultByIndexName)
                            .onErrorMap(e -> new OpenSearchErrorException(e.getMessage())),
                        OpenSearchResponse.class);
    }

    public Mono<ServerResponse> consultByFilters(ServerRequest sRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(sRequest.bodyToMono(String.class)
                        .flatMap(helper::getOpenSearchRequestMono)
                                .flatMap(openSearch::consultByFilters)
                                .onErrorMap(e -> new OpenSearchErrorException(e.getMessage())),
                        OpenSearchResponse.class);
    }

    public Mono<ServerResponse> createMetricByIndex(ServerRequest sRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(sRequest.bodyToMono(String.class)
                                .flatMap(helper::getOpenSearchRequestMono)
                                .flatMap(openSearch::createMetricByIndex)
                                .onErrorMap(e -> new OpenSearchErrorException(e.getMessage())),
                        OpenSearchResponse.class);
    }

    public Mono<ServerResponse> updateMetricByIndex(ServerRequest sRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(sRequest.bodyToMono(String.class)
                                .flatMap(helper::getOpenSearchRequestMono)
                                .flatMap(openSearch::updateMetricByIndex)
                                .onErrorMap(e -> new OpenSearchErrorException(e.getMessage())),
                        OpenSearchResponse.class);
    }

    public Mono<ServerResponse> deleteMetricByName(ServerRequest sRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(sRequest.bodyToMono(String.class)
                                .flatMap(helper::getOpenSearchRequestMono)
                                .flatMap(openSearch::deleteMetricByName)
                                .onErrorMap(e -> new OpenSearchErrorException(e.getMessage())),
                        OpenSearchResponse.class);
    }

    public Mono<ServerResponse> getAzureToken(ServerRequest sRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(azureService.getAzureToken(), AzureToken.class));
    }

    public Mono<ServerResponse> healthOspoMetrics(ServerRequest sRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(sRequest.bodyToMono(String.class)
                                .onErrorMap(e -> new HealthDummyErrorException(e.getMessage())),
                        HealthDummyResponse.class);
    }

}
