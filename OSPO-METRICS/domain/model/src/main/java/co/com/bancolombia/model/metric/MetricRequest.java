package co.com.bancolombia.model.metric;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MetricRequest {

    private int id;
}
