package co.com.bancolombia.elasticconsumer.dto;

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
public class CommitMetricDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("repo_key")
    private String key;
    @JsonProperty("repo_id")
    private String repoId;
    @JsonProperty("repo_name")
    private String repoName;
    @JsonProperty("repo_owner_id")
    private long repoOwnerId;
    @JsonProperty("repo_owner_login")
    private String repoOwnerLogin;
    @JsonProperty("commit_sha")
    private String sha;

    @JsonProperty("commit_author_login")
    private String userCommitAuthorLogin;
    @JsonProperty("commit_author_name")
    private String userCommitAuthorName;
    @JsonProperty("commit_author_email")
    private String userCommitAuthorEmail;
    @JsonProperty("commit_author_location")
    private String userCommitAuthorLocation;
    @JsonProperty("commit_author_date")
    private String dateCommitAuthorCreated;

    @JsonProperty("commit_committer_login")
    private String userCommitCommitterLogin;
    @JsonProperty("commit_committer_name")
    private String userCommitCommitterName;
    @JsonProperty("commit_committer_email")
    private String userCommitCommitterEmail;
    @JsonProperty("commit_committer_location")
    private String userCommitCommitterLocation;


    @JsonProperty("commit_stats_total")
    private int statsTotal;
    @JsonProperty("commit_stats_additions")
    private int statsAdditions;
    @JsonProperty("commit_stats_deletions")
    private int statsDeletions;
    @JsonProperty("commit_files_added")
    private int filesAdded;
    @JsonProperty("commit_files_modified")
    private int filesModified;
    @JsonProperty("commit_files_renamed")
    private int filesRenamed;
    @JsonProperty("commit_files_removed")
    private int filesRemoved;
}
