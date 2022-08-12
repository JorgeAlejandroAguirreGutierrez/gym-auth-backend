package co.com.bancolombia.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GithubClonesDTO {
    @JsonProperty("count")
    private long count;
    @JsonProperty("uniques")
    private long uniques;
    @JsonProperty("clones")
    private List<GithubClonesDataDTO> data;
}
