package co.com.bancolombia.model.commit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Commit {
    private String sha;
    private String userCommitAuthorName;
    private String userCommitAuthorEmail;
    private Date dateCommitAuthorCreated;
    private String userCommitCommitterName;
    private String userCommitCommitterEmail;
    private Date dateCommitCommitterCreated;
    private long userAuthorId;
    private String userAuthorLogin;
    private String userAuthorName;
    private String userAuthorEmail;
    private String userAuthorLocation;
    private String userAuthorType;
    private long userCommitterId;
    private String userCommitterLogin;
    private String userCommitterName;
    private String userCommitterEmail;
    private String userCommitterLocation;
    private String userCommitterType;
    private int statsTotal;
    private int statsAdditions;
    private int statsDeletions;
    private int filesAdded;
    private int filesModified;
    private int filesRenamed;
    private int filesRemoved;
}
