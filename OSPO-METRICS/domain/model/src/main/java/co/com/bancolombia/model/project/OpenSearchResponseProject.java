package co.com.bancolombia.model.project;

import co.com.bancolombia.model.opensearchservice.Filter;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class OpenSearchResponseProject {

    private String indexName;
    private Filter filter;
    private List<ProjectEntity> projectEntity;
}
