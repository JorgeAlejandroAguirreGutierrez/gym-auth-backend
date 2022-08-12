package co.com.bancolombia.model.opensearchservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class IndexInfo {

    private Long creationDate;
    private Integer numberOfShards;
    private Integer numberOfReplicas;
    private String uuid;
}
