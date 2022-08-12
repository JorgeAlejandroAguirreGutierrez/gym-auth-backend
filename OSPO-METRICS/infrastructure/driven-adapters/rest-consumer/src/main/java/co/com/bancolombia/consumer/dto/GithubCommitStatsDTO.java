package co.com.bancolombia.consumer.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GithubCommitStatsDTO {
    @JsonProperty("total")
    private int total;
    @JsonProperty("additions")
    private int additions;
    @JsonProperty("deletions")
    private int deletions;
}
