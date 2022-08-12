package co.com.bancolombia.opensearchhelper.dto;
import co.com.bancolombia.opensearchhelper.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MetricEntityDTO {

    @JsonProperty(Constants.TIME_STAMP)
    private String timestamp;

    @JsonProperty("metric_id")
    private int id;

    @JsonProperty("metric_name")
    private String name;

    @JsonProperty("metric_area")
    private String metricarea;

    @JsonProperty("metric_year")
    private int year;

    @JsonProperty("metric_goal")
    private long goal;

    @JsonProperty("metric_compliance")
    private long compliance;

    @JsonProperty("metric_measure")
    private String measure;

    @JsonProperty("metric_percentage")
    private Long metricpercentage;

}

