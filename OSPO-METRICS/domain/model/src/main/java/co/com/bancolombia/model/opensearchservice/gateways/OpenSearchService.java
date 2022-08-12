package co.com.bancolombia.model.opensearchservice.gateways;

import co.com.bancolombia.model.meetchannel.OpenSearchRequestMeetChannel;
import co.com.bancolombia.model.meetchannelevent.OpenSearchRequestMeetChannelEvent;
import co.com.bancolombia.model.metric.OpenSearchRequestMetric;
import co.com.bancolombia.model.opensearchservice.OpenSearchRequest;
import co.com.bancolombia.model.opensearchservice.OpenSearchResponse;
import co.com.bancolombia.model.project.OpenSearchRequestProject;
import co.com.bancolombia.model.projectdetail.OpenSearchRequestProjectDetail;
import reactor.core.publisher.Mono;

public interface OpenSearchService {

    Mono<OpenSearchResponse> consultIndexStructure(OpenSearchRequest request);
    Mono<OpenSearchResponse> consultMetricByIndexName(OpenSearchRequest request);

    Mono<OpenSearchResponse> consultMetricByFilters(OpenSearchRequest request);

    Mono<OpenSearchResponse> createMetricByIndex(OpenSearchRequest request);

    Mono<OpenSearchResponse> updateMetricByIndex(OpenSearchRequest request);

    Mono<OpenSearchResponse> deleteMetricByName(OpenSearchRequest request);

    Mono<OpenSearchResponse> createMetricByIndexMeetChannel(OpenSearchRequestMeetChannel request);

    Mono<OpenSearchResponse> createMetricByIndexMeetChannelEvent(OpenSearchRequestMeetChannelEvent request);

    Mono<OpenSearchResponse> createMetricByIndexMetric(OpenSearchRequestMetric request);

    Mono<OpenSearchResponse> createMetricByIndexProject(OpenSearchRequestProject request);

    Mono<OpenSearchResponse> createMetricByIndexProjectDetail(OpenSearchRequestProjectDetail request);

}
