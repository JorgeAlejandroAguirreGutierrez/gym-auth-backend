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
public class GithubIssueDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("number")
    private long number;
    @JsonProperty("title")
    private String title;
    @JsonProperty("user")
    private GithubUserDTO user;
    @JsonProperty("created_at")
    private Date dateCreated;
    @JsonProperty("updated_at")
    private Date dateUpdated;
    @JsonProperty("closed_at")
    private Date dateClosed;
    @JsonProperty("state")
    private String state;
}
