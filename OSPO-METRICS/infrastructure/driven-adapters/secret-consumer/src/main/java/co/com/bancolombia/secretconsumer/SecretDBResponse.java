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
public class SecretDBResponse {
    @JsonProperty("password")
    private String password;
    @JsonProperty("dbname")
    private String dbname;
    @JsonProperty("port")
    private String port;
    @JsonProperty("dbInstanceIdentifier")
    private String dbInstanceIdentifier;
    @JsonProperty("host")
    private String host;
    @JsonProperty("username")
    private String username;
}
