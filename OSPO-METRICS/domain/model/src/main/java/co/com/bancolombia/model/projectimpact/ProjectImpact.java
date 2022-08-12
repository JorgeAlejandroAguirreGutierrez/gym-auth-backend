package co.com.bancolombia.model.projectimpact;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectImpact {
    private int id;
    private String name;
}
