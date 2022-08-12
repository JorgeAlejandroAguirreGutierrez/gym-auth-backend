package co.com.bancolombia.model.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectRequest {
    private int id;
}
