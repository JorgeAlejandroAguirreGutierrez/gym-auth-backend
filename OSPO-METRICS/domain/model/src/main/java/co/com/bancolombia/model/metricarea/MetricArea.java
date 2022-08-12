package co.com.bancolombia.model.metricarea;
import lombok.Builder;
import lombok.Data;
@Data
@Builder(toBuilder = true)
public class MetricArea {
    private int id;
    private String name;
}
