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
public class GithubAppTokenDTO {
    @JsonProperty("token")
    private String token;
    @JsonProperty("expires_at")
    private Date dateExpires;
}
