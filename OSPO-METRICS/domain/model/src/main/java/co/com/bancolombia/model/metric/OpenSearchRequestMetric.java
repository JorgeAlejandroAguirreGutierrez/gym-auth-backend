package co.com.bancolombia.model.metric;

import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import co.com.bancolombia.model.opensearchservice.Filter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpenSearchRequestMetric {

    private String indexName;
    private Filter filter;
    private MetricEntity metricEntity;
}
