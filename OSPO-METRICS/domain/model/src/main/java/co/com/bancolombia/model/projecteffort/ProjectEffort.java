package co.com.bancolombia.model.projecteffort;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectEffort {
    private int id;
    private String name;
}
