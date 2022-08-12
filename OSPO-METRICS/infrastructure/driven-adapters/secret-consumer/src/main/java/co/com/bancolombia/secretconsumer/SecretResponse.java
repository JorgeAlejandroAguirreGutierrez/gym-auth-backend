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
public class SecretResponse {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

}
