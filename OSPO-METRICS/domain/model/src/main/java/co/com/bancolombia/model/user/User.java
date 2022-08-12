package co.com.bancolombia.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private long id;
    private String nodeId;
    private String login;
    private String name;
    private String email;
    private String location;
    private String type;
}
