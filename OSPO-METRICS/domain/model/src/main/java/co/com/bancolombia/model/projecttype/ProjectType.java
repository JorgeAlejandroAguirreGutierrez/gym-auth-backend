package co.com.bancolombia.model.projecttype;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectType {
    private int id;
    private String name;
}
