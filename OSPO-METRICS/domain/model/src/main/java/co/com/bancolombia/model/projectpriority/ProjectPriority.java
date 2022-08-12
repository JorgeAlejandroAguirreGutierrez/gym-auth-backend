package co.com.bancolombia.model.projectpriority;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectPriority {
    private int id;
    private String name;
}
