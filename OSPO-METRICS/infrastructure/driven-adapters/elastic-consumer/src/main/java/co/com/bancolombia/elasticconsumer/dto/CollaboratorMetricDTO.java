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
public class CollaboratorMetricDTO {
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
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("user_node_id")
    private String userNodeId;
    @JsonProperty("user_login")
    private String userLogin;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("user_location")
    private String userLocation;
    @JsonProperty("user_association")
    private String userAssociation;
    @JsonProperty("user_type")
    private String userType;

}
