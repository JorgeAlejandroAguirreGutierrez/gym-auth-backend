package co.com.bancolombia.usecase.opensearch;

import co.com.bancolombia.model.meetchannel.OpenSearchRequestMeetChannel;
import co.com.bancolombia.model.meetchannelevent.OpenSearchRequestMeetChannelEvent;
import co.com.bancolombia.model.metric.OpenSearchRequestMetric;
import co.com.bancolombia.model.opensearchservice.OpenSearchRequest;
import co.com.bancolombia.model.opensearchservice.OpenSearchResponse;
import co.com.bancolombia.model.opensearchservice.gateways.OpenSearchService;
import co.com.bancolombia.model.project.OpenSearchRequestProject;
import co.com.bancolombia.model.projectdetail.OpenSearchRequestProjectDetail;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OpenSearchUseCase {

    private final OpenSearchService openSearchService;

    public Mono<OpenSearchResponse> consultIndexStructure(OpenSearchRequest request) {
        return openSearchService.consultIndexStructure(request);
    }

    public Mono<OpenSearchResponse> consultByIndexName(OpenSearchRequest request){
        return openSearchService.consultMetricByIndexName(request);
    }

    public Mono<OpenSearchResponse> consultByFilters(OpenSearchRequest request){
        return openSearchService.consultMetricByFilters(request);
    }

    public Mono<OpenSearchResponse> createMetricByIndex(OpenSearchRequest request){
        return openSearchService.createMetricByIndex(request);
    }

    public Mono<OpenSearchResponse> updateMetricByIndex(OpenSearchRequest request){
        return openSearchService.updateMetricByIndex(request);
    }

    public Mono<OpenSearchResponse> deleteMetricByName(OpenSearchRequest request) {
        return openSearchService.deleteMetricByName(request);
    }

    //metric from data base
    public Mono<OpenSearchResponse> createMetricByIndexMeetChannel(OpenSearchRequestMeetChannel request){
        return openSearchService.createMetricByIndexMeetChannel(request);
    }

    public Mono<OpenSearchResponse> createMetricByIndexMeetChannelEvent(OpenSearchRequestMeetChannelEvent request){
        return openSearchService.createMetricByIndexMeetChannelEvent(request);
    }

    public Mono<OpenSearchResponse> createMetricByIndexMetric(OpenSearchRequestMetric request){
        return openSearchService.createMetricByIndexMetric(request);
    }
    public Mono<OpenSearchResponse> createMetricByIndexProject(OpenSearchRequestProject request){
        return openSearchService.createMetricByIndexProject(request);
    }
    public Mono<OpenSearchResponse> createMetricByIndexProjectDetail(OpenSearchRequestProjectDetail request){
        return openSearchService.createMetricByIndexProjectDetail(request);
    }

}
