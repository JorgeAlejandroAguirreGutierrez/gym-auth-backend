package co.com.bancolombia.model.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Issue {
    private long id;
    private String nodeId;
    private long number;
    private String title;
    private long userId;
    private String userLogin;
    private String userName;
    private String userEmail;
    private String userLocation;
    private String userType;
    private Date dateCreated;
    private Date dateUpdated;
    private Date dateClosed;
    private int daysOpen;
    private int daysClosed;
    private String state;
}
