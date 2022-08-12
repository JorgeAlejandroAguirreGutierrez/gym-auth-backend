package co.com.bancolombia.model.collaborator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Collaborator {
    private long userId;
    private String userNodeId;
    private String userLogin;
    private String userName;
    private String userEmail;
    private String userLocation;
    private String userAssociation;
    private String userType;
}
