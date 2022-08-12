package co.com.bancolombia.elasticconsumer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OpenSearchOpsErrorResponse {

    @JsonProperty("type")
    private String type;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("index")
    private String index;
    @JsonProperty("shard")
    private String shard;
    @JsonProperty("index_uuid")
    private String indexUUID;
}
