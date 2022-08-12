package co.com.bancolombia.model.projectdetail;

import co.com.bancolombia.model.opensearchservice.Filter;
import co.com.bancolombia.model.project.ProjectEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class OpenSearchResponseProjectDetail {

    private String indexName;
    private Filter filter;
    private List<ProjectDetailEntity> projectDetailEntity;
}
