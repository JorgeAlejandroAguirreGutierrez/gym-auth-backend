package co.com.bancolombia.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GithubPullDTO {
    @JsonProperty("url")
    private String url;
    @JsonProperty("id")
    private long id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("diff_url")
    private String diffUrl;
    @JsonProperty("patch_url")
    private String patchUrl;
    @JsonProperty("issue_url")
    private String issueUrl;
    @JsonProperty("number")
    private long number;
    @JsonProperty("state")
    private String state;
    @JsonProperty("locked")
    private Boolean locked;
    @JsonProperty("title")
    private String title;
    @JsonProperty("user")
    private GithubUserDTO user;
    @JsonProperty("body")
    private String body;
    @JsonProperty("created_at")
    private Date dateCreated;
    @JsonProperty("updated_at")
    private Date dateUpdated;
    @JsonProperty("closed_at")
    private Date dateClosed;
    @JsonProperty("merged_at")
    private Date dateMerged;
    @JsonProperty("merge_commit_sha")
    private String mergeCommitSHA;
}
