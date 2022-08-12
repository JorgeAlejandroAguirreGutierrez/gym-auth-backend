package co.com.bancolombia.model.projectsubtype;
import co.com.bancolombia.model.projecttype.ProjectType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectSubType {
    private int id;
    private ProjectType projectType;
    private String name;
}
