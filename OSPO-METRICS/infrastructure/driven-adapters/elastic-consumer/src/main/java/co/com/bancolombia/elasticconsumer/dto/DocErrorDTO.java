package co.com.bancolombia.elasticconsumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DocErrorDTO {
    @JsonProperty("root_cause")
    private DocErrorDTO[] rootCause;
    @JsonProperty("type")
    private String type;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("index")
    private String index;
    @JsonProperty("resource.id")
    private String resourceId;
    @JsonProperty("resource.type")
    private String resourceType;
    @JsonProperty("index_uuid")
    private String UUID;
}
