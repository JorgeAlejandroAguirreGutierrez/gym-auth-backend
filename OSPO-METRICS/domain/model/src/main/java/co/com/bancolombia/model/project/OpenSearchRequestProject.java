package co.com.bancolombia.model.project;

import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.opensearchservice.Filter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpenSearchRequestProject {

    private String indexName;
    private Filter filter;
    private ProjectEntity projectEntity;
}
