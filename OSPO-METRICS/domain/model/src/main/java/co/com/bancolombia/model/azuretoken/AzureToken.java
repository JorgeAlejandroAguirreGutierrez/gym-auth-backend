package co.com.bancolombia.model.azuretoken;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AzureToken {

    private String tokenType;
    private Integer expiresIn;
    private Integer extExpiresIn;
    private String accessToken;
}
