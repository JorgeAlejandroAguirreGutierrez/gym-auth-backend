package co.com.bancolombia.model.opensearchservice;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class GitHubMetricSource {

    private String repoCollaboratorsUrl;
    private String repoCommitsUrl;
    private String repoContributorsUrl;
    private Date repoCreated;
    private Date repoDateFrom;
    private Date repoDateTo;
    private String repoDefaultBranch;
    private String repoDescription;
    private String repoDownloadsUrl;
    private String repoEventsUrl;
    private Long repoForks;
    private Long repoForksCount;
    private Integer repoGroupDay;
    private Integer repoGroupMonth;
    private Integer repoGroupWeek;
    private Integer repoGroupYear;
    private Boolean repoHasDownloads;
    private Boolean repoHasIssue;
    private Boolean repoHasPages;
    private Boolean repoHasProjects;
    private Boolean repoHasWiki;
    private String repoHttpUrl;
    private String repoHttpsUrl;
    private String repoId;
    private String repoIssueCommentUrl;
    private String repoIssueEventsUrl;
    private String repoIssuesUrl;
    private String repoKey;
    private String repoLanguage;
    private String repoLicenseKey;
    private String repoLicenseName;
    private String repoLicenseNode;
    private String repoLicenseSPDXId;
    private String repoLicenseUrl;
    private String repoMilestonesUrl;
    private String repoName;
    private String repoNotificationsUrl;
    private Long repoOpenIssues;
    private Long repoOpenIssuesCount;
    private String repoOwner;
    private String repoOwnerId;
    private String repoOwnerNodeId;
    private String repoOwnerType;
    private String repoPullsUrl;
    private Date repoPushed;
    private Long repoSize;
    private Long repoStargazersCount;
    private String repoStargazersUrl;
    private String repoStatusesUrl;
    private Long repoSuscribersCount;
    private String repoSuscribersUrl;
    private String repoToken;
    private String repoTreesUrl;
    private Date repoUpdated;
    private String repoVisibility;
    private Long repoWatchers;
    private Long repoWarchersCount;
}
