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
public class GithubAppDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("owner")
    private GithubOwnerDTO owner;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("external_url")
    private String externalUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("created_at")
    private Date dateCreated;
    @JsonProperty("updated_at")
    private Date dateUpdated;
}
