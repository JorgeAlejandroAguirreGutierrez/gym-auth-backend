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
public class ViewsMetricDTO {
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
    private String dateWeek;
    @JsonProperty("date_from")
    private String dateFrom;
    @JsonProperty("date_to")
    private String dateTo;
    @JsonProperty("views_uniques")
    private long viewsUniques;
    @JsonProperty("views_count")
    private long viewsCount;
}
