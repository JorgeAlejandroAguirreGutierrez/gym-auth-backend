package co.com.bancolombia.model.pull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Pull {
    private long id;
    private long number;
    private String title;
    private String state;
    private Boolean locked;
    private long userId;
    private String userLogin;
    private String userName;
    private String userEmail;
    private String userLocation;
    private String userAssociation;
    private Date dateCreated;
    private Date dateUpdated;
    private Date dateClosed;
    private Date dateMerged;
    private String mergeCommitSHA;
    private int daysOpen;
    private int daysClosed;
    private int daysMerged;
}
