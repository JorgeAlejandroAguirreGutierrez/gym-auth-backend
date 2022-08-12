package co.com.bancolombia.model.projectdetail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectDetailRequest {
    private int id;
}
