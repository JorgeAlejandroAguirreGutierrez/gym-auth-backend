package co.com.bancolombia.model.opensearchservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpenSearchRequest {

    private String indexName;
    private Filter filter;
    private GitHubMetric metric;
}
