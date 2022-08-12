package co.com.bancolombia.secretconsumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AzureSecuritySecret {

    @JsonProperty("tokenUri")
    private String tokenUri;
    @JsonProperty("clientId")
    private String clientId;
    @JsonProperty("audience")
    private String audience;
    @JsonProperty("grantType")
    private String grantType;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("clientSecret")
    private String clientSecret;
    @JsonProperty("jwtIssuerIss")
    private String jwtIssuerIss;
    @JsonProperty("jwtIssuerUri")
    private String jwtIssuerUri;
}
