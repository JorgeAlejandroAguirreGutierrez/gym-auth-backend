package co.com.bancolombia.model.projectstatus;
import lombok.Builder;
import lombok.Data;
@Data
@Builder(toBuilder = true)
public class ProjectStatus {
    private int id;
    private String name;
}
