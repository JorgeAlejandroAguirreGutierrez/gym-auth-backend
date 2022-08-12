package co.com.bancolombia.model.metric;
import lombok.Builder;
import lombok.Data;
@Data
@Builder(toBuilder = true)
public class MetricEntity {
    private String messageError;
    private int id;
    private String area;
    private String name;
    private int year;
    private long goal;
    private long compliance;
    private String measure;

}

