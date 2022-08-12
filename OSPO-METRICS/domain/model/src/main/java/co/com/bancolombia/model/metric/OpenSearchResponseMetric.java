package co.com.bancolombia.model.metric;

import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import co.com.bancolombia.model.opensearchservice.Filter;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class OpenSearchResponseMetric {

    private String indexName;
    private Filter filter;
    private List<MetricEntity> metricEntity;
}
