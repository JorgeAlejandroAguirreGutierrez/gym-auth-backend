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
public class DocHitsDTO {
    @JsonProperty("total")
    private JsonNode total;
    @JsonProperty("max_score")
    private long max_score;
    @JsonProperty("hits")
    private DocHitsDataDTO[] hits;
}
