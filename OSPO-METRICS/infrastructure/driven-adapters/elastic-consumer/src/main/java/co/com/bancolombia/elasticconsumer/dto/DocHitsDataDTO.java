package co.com.bancolombia.elasticconsumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DocHitsDataDTO {
    @JsonProperty("_index")
    private String index;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("_score")
    private long score;
    @JsonProperty("_source")
    private JsonNode source;
}
