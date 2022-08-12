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
public class GithubCommitDetailDTO {
    @JsonProperty("url")
    private String url;
    @JsonProperty("author")
    private GithubCommitUserDTO author;
    @JsonProperty("committer")
    private GithubCommitUserDTO committer;
    @JsonProperty("message")
    private String message;
    @JsonProperty("tree")
    private GithubCommitTreeDTO tree;
    @JsonProperty("comment_count")
    private int commentCount;
    @JsonProperty("verification")
    private GithubCommitVerificationDTO verification;
}
