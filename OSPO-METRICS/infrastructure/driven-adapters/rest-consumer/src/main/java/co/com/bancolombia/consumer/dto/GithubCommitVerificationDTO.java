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
public class GithubCommitVerificationDTO {
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("payload")
    private String payload;
}
