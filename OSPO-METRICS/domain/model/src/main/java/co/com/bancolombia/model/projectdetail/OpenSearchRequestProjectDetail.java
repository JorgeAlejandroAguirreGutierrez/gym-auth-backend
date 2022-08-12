package co.com.bancolombia.model.projectdetail;

import co.com.bancolombia.model.opensearchservice.Filter;
import co.com.bancolombia.model.project.ProjectEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpenSearchRequestProjectDetail {

    private String indexName;
    private Filter filter;
    private ProjectDetailEntity projectDetailEntity;
}
