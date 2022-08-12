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
public class GithubCommitFilesDTO {
    @JsonProperty("sha")
    private String sha;
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("status")
    private String status;
    @JsonProperty("additions")
    private int additions;
    @JsonProperty("deletions")
    private int deletions;
    @JsonProperty("changes")
    private int changes;
}
