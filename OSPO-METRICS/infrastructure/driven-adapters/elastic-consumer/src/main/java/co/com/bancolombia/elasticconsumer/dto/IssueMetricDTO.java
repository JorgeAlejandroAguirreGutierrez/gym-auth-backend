package co.com.bancolombia.elasticconsumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class IssueMetricDTO {
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
    @JsonProperty("issue_id")
    private long issueId;
    @JsonProperty("issue_number")
    private long issueNumber;
    @JsonProperty("issue_title")
    private String issueTitle;
    @JsonProperty("issue_state")
    private String issueState;
    @JsonProperty("issue_user_id")
    private long issueUserId;
    @JsonProperty("issue_user_login")
    private String issueUserLogin;
    @JsonProperty("issue_user_name")
    private String issueUserName;
    @JsonProperty("issue_user_email")
    private String issueUserEmail;
    @JsonProperty("issue_user_location")
    private String issueUserLocation;
    @JsonProperty("issue_created")
    private String issueDateCreated;
    @JsonProperty("issue_updated")
    private String issueDateUpdated;
    @JsonProperty("issue_closed")
    private String issueDateClosed;
    @JsonProperty("issue_days_open")
    private int issueDaysOpen;
    @JsonProperty("issue_days_close")
    private int issueDaysClose;
}
