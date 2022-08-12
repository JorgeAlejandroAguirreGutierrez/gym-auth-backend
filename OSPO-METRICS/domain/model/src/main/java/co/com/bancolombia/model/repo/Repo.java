package co.com.bancolombia.model.repo;

import co.com.bancolombia.model.clones.Clones;
import co.com.bancolombia.model.codefrecuency.CodeFrecuency;
import co.com.bancolombia.model.collaborator.Collaborator;
import co.com.bancolombia.model.commit.Commit;
import co.com.bancolombia.model.commitactivity.CommitActivity;
import co.com.bancolombia.model.contributor.Contributor;
import co.com.bancolombia.model.issue.Issue;
import co.com.bancolombia.model.pull.Pull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Repo {
    private long id;
    private String nodeId;
    private String name;
    private String description;
    private long ownerId;
    private String ownerNodeId;
    private String ownerLogin;
    private String ownerType;
    private String ownerAvatarUrl;
    private Date dateCreated;
    private Date dateUpdated;
    private Date datePushed;
    private Date dateProcessed;
    private Date dateFrom;
    private Date dateTo;
    private String[] topics;
    private String licenseKey;
    private String licenseName;
    private String licenseSPDXId;
    private String licenseUrl;
    private String licenseNodeId;
    private String language;
    private long size;
    private Boolean hasIssues;
    private Boolean hasProjects;
    private Boolean hasDownloads;
    private Boolean hasWiki;
    private Boolean hasPages;
    private String url;
    private String urlHTML;
    private String urlCommits;
    private String urlCollaborators;
    private String urlContributors;
    private String urlDownloads;
    private String urlEvents;
    private String urlIssues;
    private String urlIssueEvents;
    private String urlIssueComments;
    private String urlPulls;
    private String urlNotifications;
    private String urlMilestones;
    private String urlStatuses;
    private String urlTrees;
    private String urlStargazers;
    private String urlSubscribers;
    private long stargazersCount;
    private long watchersCount;
    private long forksCount;
    private long openIssuesCount;
    private long openIssues;
    private long forks;
    private long watchers;
    private String defaultBranch;
    private String visibility;
    private Boolean isPrivate;
    private Boolean isFork;
    private long score;
}
