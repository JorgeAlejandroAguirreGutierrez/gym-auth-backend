package co.com.bancolombia.model.opensearchservice;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class GitHubMetric {

    private String metricId;
    private GitHubMetricSource source;
}
