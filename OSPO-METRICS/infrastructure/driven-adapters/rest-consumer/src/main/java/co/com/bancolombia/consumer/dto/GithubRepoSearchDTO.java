package co.com.bancolombia.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GithubRepoSearchDTO {
    @JsonProperty("total_count")
    private int total;
    @JsonProperty("incomplete_results")
    private Boolean incompleteResults;
    @JsonProperty("items")
    private List<GithubRepoDTO> items;
}
