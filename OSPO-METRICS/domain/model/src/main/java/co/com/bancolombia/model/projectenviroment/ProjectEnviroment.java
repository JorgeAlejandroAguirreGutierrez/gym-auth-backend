package co.com.bancolombia.model.projectenviroment;
import lombok.Builder;
import lombok.Data;
@Data
@Builder(toBuilder = true)
public class ProjectEnviroment {
    private int id;
    private String name;
}
