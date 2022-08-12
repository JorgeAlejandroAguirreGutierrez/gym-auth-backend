package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.model.meetchannel.OpenSearchRequestMeetChannel;
import co.com.bancolombia.model.meetchannelevent.OpenSearchRequestMeetChannelEvent;
import co.com.bancolombia.model.metric.OpenSearchRequestMetric;
import co.com.bancolombia.model.opensearchservice.exception.OpenSearchErrorException;
import co.com.bancolombia.model.opensearchservice.OpenSearchRequest;
import co.com.bancolombia.model.opensearchservice.OpenSearchResponse;
import co.com.bancolombia.model.opensearchservice.gateways.OpenSearchService;
import co.com.bancolombia.model.project.OpenSearchRequestProject;
import co.com.bancolombia.model.projectdetail.OpenSearchRequestProjectDetail;
import co.com.bancolombia.opensearchhelper.OpenSearchMapperHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OpenSearchRestConsumer implements OpenSearchService {

    private final Logger logger = LoggerFactory.getLogger(OpenSearchRestConsumer.class);

    private final String OPENSEARCH_SEARCH_INDEX = "/{index}/_search";
    private final String OPENSEARCH_CREATE_INDEX = "/{index}/_doc";

    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;

    @Autowired
    private OpenSearchMapperHelper helper;

    @Override
    public Mono<OpenSearchResponse> consultIndexStructure(OpenSearchRequest request) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/{index}")
                        .build(request.getIndexName()))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(res -> Mono.just(OpenSearchResponse.builder().data(null).build()))
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    @Override
    public Mono<OpenSearchResponse> consultMetricByIndexName(OpenSearchRequest request) {
        logger.debug("Indice a consultar: " + request.getIndexName());
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(OPENSEARCH_SEARCH_INDEX)
                        .build(request.getIndexName()))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(json -> helper.getOpenSearchResponseMono(request.getIndexName(), json))
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    @Override
    public Mono<OpenSearchResponse> consultMetricByFilters(OpenSearchRequest request) {
        logger.debug("Indice a consultar: " + request.getIndexName());
        logger.debug("Query: " + helper.getQueryToConsult(request));
        return client.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(OPENSEARCH_SEARCH_INDEX)
                        .build(request.getIndexName()))
                .bodyValue(helper.getQueryToConsult(request))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(json -> helper.getOpenSearchResponseMono(request.getIndexName(), json))
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    @Override
    public Mono<OpenSearchResponse> createMetricByIndex(OpenSearchRequest request) {
        logger.info("Creating Metric by Index...");
        return client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(OPENSEARCH_CREATE_INDEX)
                        .build(request.getIndexName()))
                .bodyValue(helper.getOpenSearchCreateRequestMono(request))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(OpenSearchErrorException::new))
                .bodyToMono(OpenSearchOpsOkResponse.class)
                .flatMap(res -> {
                    return Mono.just(OpenSearchResponse.builder()
                            .operationStatus("200")
                            .operationResult(res.getResult())
                            .indexName(res.getIndex())
                            .build());
                })
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    @Override
    public Mono<OpenSearchResponse> updateMetricByIndex(OpenSearchRequest request) {
        logger.info("Updating Metric by Index...");
        return client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/{index}/_doc/{metric}/_update")
                        .build(request.getIndexName(), request.getMetric().getMetricId()))
                .bodyValue(helper.getOpenSearchUpdateRequestMono(request))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(OpenSearchErrorException::new))
                .bodyToMono(OpenSearchOpsOkResponse.class)
                .flatMap(res -> {
                    return Mono.just(OpenSearchResponse.builder()
                            .operationStatus("200")
                            .operationResult(res.getResult())
                            .indexName(res.getIndex())
                            .build());
                })
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    @Override
    public Mono<OpenSearchResponse> deleteMetricByName(OpenSearchRequest request) {
        logger.info("Deleting Metric by Index...");
        return client.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/{index}/_doc/{metric}")
                        .build(request.getIndexName(), request.getMetric().getMetricId()))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(OpenSearchErrorException::new))
                .bodyToMono(OpenSearchOpsOkResponse.class)
                .flatMap(res -> {
                    return Mono.just(OpenSearchResponse.builder()
                            .operationStatus("200")
                            .operationResult(res.getResult())
                            .indexName(res.getIndex())
                            .build());
                })
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    /***
     * Create metric from data base
     * @param request
     * @return
     */
    @Override
    public Mono<OpenSearchResponse> createMetricByIndexMeetChannel(OpenSearchRequestMeetChannel request) {
        logger.info("Creating Metric by Index MeetChannel...");
        return client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(OPENSEARCH_CREATE_INDEX)
                        .build(request.getIndexName()))
                .bodyValue(helper.getOpenSearchCreateRequestMeetChannelMono(request))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(OpenSearchErrorException::new))
                .bodyToMono(OpenSearchOpsOkResponse.class)
                .flatMap(res -> {
                    return Mono.just(OpenSearchResponse.builder()
                            .operationStatus("200")
                            .operationResult(res.getResult())
                            .indexName(res.getIndex())
                            .build());
                })
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    /***
     * Create metric from data base
     * @param request
     * @return
     */
    @Override
    public Mono<OpenSearchResponse> createMetricByIndexMeetChannelEvent(OpenSearchRequestMeetChannelEvent request) {
        logger.info("Creating Metric by Index MeetChannelEvent...");
        return client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(OPENSEARCH_CREATE_INDEX)
                        .build(request.getIndexName()))
                .bodyValue(helper.getOpenSearchCreateRequestMeetChannelEventMono(request))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(OpenSearchErrorException::new))
                .bodyToMono(OpenSearchOpsOkResponse.class)
                .flatMap(res -> {
                    return Mono.just(OpenSearchResponse.builder()
                            .operationStatus("200")
                            .operationResult(res.getResult())
                            .indexName(res.getIndex())
                            .build());
                })
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    /***
     * Create metric from data base
     * @param request
     * @return
     */
    @Override
    public Mono<OpenSearchResponse> createMetricByIndexMetric(OpenSearchRequestMetric request) {
        logger.info("Creating Metric by Index Metric from database...");
        return client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(OPENSEARCH_CREATE_INDEX)
                        .build(request.getIndexName()))
                .bodyValue(helper.getOpenSearchCreateRequestMetric(request))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(OpenSearchErrorException::new))
                .bodyToMono(OpenSearchOpsOkResponse.class)
                .flatMap(res -> {
                    return Mono.just(OpenSearchResponse.builder()
                            .operationStatus("200")
                            .operationResult(res.getResult())
                            .indexName(res.getIndex())
                            .build());
                })
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    /***
     * Create project from data base
     * @param request
     * @return
     */
    @Override
    public Mono<OpenSearchResponse> createMetricByIndexProject(OpenSearchRequestProject request) {
        logger.info("Creating Metric by Index project from database...");
        return client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(OPENSEARCH_CREATE_INDEX)
                        .build(request.getIndexName()))
                .bodyValue(helper.getOpenSearchCreateRequestProject(request))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(OpenSearchErrorException::new))
                .bodyToMono(OpenSearchOpsOkResponse.class)
                .flatMap(res -> {
                    return Mono.just(OpenSearchResponse.builder()
                            .operationStatus("200")
                            .operationResult(res.getResult())
                            .indexName(res.getIndex())
                            .build());
                })
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

    /***
     * Create project from data base
     * @param request
     * @return
     */
    @Override
    public Mono<OpenSearchResponse> createMetricByIndexProjectDetail(OpenSearchRequestProjectDetail request) {
        logger.info("Creating Metric by Index project detail from database...");
        return client.post()
                .uri(uriBuilder -> uriBuilder
                        .path(OPENSEARCH_CREATE_INDEX)
                        .build(request.getIndexName()))
                .bodyValue(helper.getOpenSearchCreateRequestProjectDetail(request))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(OpenSearchErrorException::new))
                .bodyToMono(OpenSearchOpsOkResponse.class)
                .flatMap(res -> {
                    return Mono.just(OpenSearchResponse.builder()
                            .operationStatus("200")
                            .operationResult(res.getResult())
                            .indexName(res.getIndex())
                            .build());
                })
                .onErrorMap((ex) -> new OpenSearchErrorException(ex.getMessage()));
    }

}
