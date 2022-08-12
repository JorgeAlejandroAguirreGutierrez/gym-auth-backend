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
public class GithubCommitDTO {
    @JsonProperty("url")
    private String url;
    @JsonProperty("sha")
    private String sha;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("comments_url")
    private String commentsUrl;
    @JsonProperty("commit")
    private GithubCommitDetailDTO commit;
    @JsonProperty("author")
    private GithubUserDTO author;
    @JsonProperty("committer")
    private GithubUserDTO committer;
    @JsonProperty("parents")
    private GithubCommitParentDTO[] parents;
    @JsonProperty("stats")
    private GithubCommitStatsDTO stats;
    @JsonProperty("files")
    private GithubCommitFilesDTO[] files;
}
