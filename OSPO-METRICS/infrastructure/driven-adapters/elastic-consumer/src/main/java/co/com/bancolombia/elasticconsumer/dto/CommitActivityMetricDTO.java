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
public class CommitActivityMetricDTO {
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
    @JsonProperty("date_week")
    private long week;
    @JsonProperty("date_from")
    private Date dateFrom;
    @JsonProperty("date_to")
    private Date dateTo;
    @JsonProperty("total_sunday")
    private long totalSunday;
    @JsonProperty("total_monday")
    private long totalMonday;
    @JsonProperty("total_tuesday")
    private long totalTuesday;
    @JsonProperty("total_wednesday")
    private long totalWednesday;
    @JsonProperty("total_thursday")
    private long totalThursday;
    @JsonProperty("total_friday")
    private long totalFriday;
    @JsonProperty("total_saturday")
    private long totalSaturday;
    @JsonProperty("total")
    private long total;
}
