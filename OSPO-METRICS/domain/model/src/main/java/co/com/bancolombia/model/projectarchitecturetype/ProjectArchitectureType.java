package co.com.bancolombia.model.projectarchitecturetype;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectArchitectureType {
    private int id;
    private String name;
}
