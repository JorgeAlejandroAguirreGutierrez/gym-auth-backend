package co.com.bancolombia.consumer.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GithubRepoDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("private")
    private Boolean isPrivate;
    @JsonProperty("owner")
    private GithubOwnerDTO owner;
    @JsonProperty("html_url")
    private String urlHTML;
    @JsonProperty("description")
    private String description;
    @JsonProperty("fork")
    private Boolean isFork;
    @JsonProperty("url")
    private String url;
    @JsonProperty("forks_url")
    private String urlForks;
    @JsonProperty("keys_url")
    private String urlKeys;
    @JsonProperty("collaborators_url")
    private String urlCollaborators;
    @JsonProperty("teams_url")
    private String urlTeams;
    @JsonProperty("hooks_url")
    private String urlHooks;
    @JsonProperty("issue_events_url")
    private String urlIssueEvents;
    @JsonProperty("events_url")
    private String urlEvents;
    @JsonProperty("assignees_url")
    private String urlAssignees;
    @JsonProperty("branches_url")
    private String urlBranches;
    @JsonProperty("tags_url")
    private String urlTags;
    @JsonProperty("blobs_url")
    private String urlBlobs;
    @JsonProperty("git_tags_url")
    private String urlGitTags;
    @JsonProperty("git_refs_url")
    private String urlGitRefs;
    @JsonProperty("trees_url")
    private String urlTrees;
    @JsonProperty("statuses_url")
    private String urlStatuses;
    @JsonProperty("languages_url")
    private String urlLanguages;
    @JsonProperty("stargazers_url")
    private String urlStargazers;
    @JsonProperty("contributors_url")
    private String urlContributors;
    @JsonProperty("subscribers_url")
    private String urlSubscribers;
    @JsonProperty("subscription_url")
    private String urlSubscription;
    @JsonProperty("commits_url")
    private String urlCommits;
    @JsonProperty("git_commits_url")
    private String urlGitCommits;
    @JsonProperty("comments_url")
    private String urlComments;
    @JsonProperty("issue_comment_url")
    private String urlIssueComment;
    @JsonProperty("contents_url")
    private String urlContents;
    @JsonProperty("compare_url")
    private String urlCompare;
    @JsonProperty("merges_url")
    private String urlMerges;
    @JsonProperty("archive_url")
    private String urlArchive;
    @JsonProperty("downloads_url")
    private String urlDownloads;
    @JsonProperty("issues_url")
    private String urlIssues;
    @JsonProperty("pulls_url")
    private String urlPulls;
    @JsonProperty("milestones_url")
    private String urlMilestones;
    @JsonProperty("notifications_url")
    private String urlNotifications;
    @JsonProperty("labels_url")
    private String urlLabels;
    @JsonProperty("releases_url")
    private String urlReleases;
    @JsonProperty("deployments_url")
    private String urlDeployments;
    @JsonProperty("created_at")
    private Date dateCreated;
    @JsonProperty("updated_at")
    private Date dateUpdated;
    @JsonProperty("pushed_at")
    private Date datePushed;
    @JsonProperty("git_url")
    private String urlGit;
    @JsonProperty("ssh_url")
    private String urlSSH;
    @JsonProperty("clone_url")
    private String urlClone;
    @JsonProperty("svn_url")
    private String urlSVN;
    @JsonProperty("homepage")
    private String homePage;
    @JsonProperty("size")
    private long size;
    @JsonProperty("stargazers_count")
    private long stargazersCount;
    @JsonProperty("watchers_count")
    private long watchersCount;
    @JsonProperty("language")
    private String language;
    @JsonProperty("has_issues")
    private Boolean hasIssues;
    @JsonProperty("has_projects")
    private Boolean hasProjects;
    @JsonProperty("has_downloads")
    private Boolean hasDownloads;
    @JsonProperty("has_wiki")
    private Boolean hasWiki;
    @JsonProperty("has_pages")
    private Boolean hasPages;
    @JsonProperty("forks_count")
    private long forksCount;
    @JsonProperty("mirror_url")
    private String mirrorUrl;
    @JsonProperty("archived")
    private Boolean isArchived;
    @JsonProperty("disabled")
    private Boolean isDisabled;
    @JsonProperty("open_issues_count")
    private long openIssuesCount;
    @JsonProperty("license")
    private GithubLicenseDTO License;
    @JsonProperty("allow_forking")
    private Boolean allowForking;
    @JsonProperty("is_template")
    private Boolean isTemplate;
    @JsonProperty("topics")
    private String[] topics;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("forks")
    private long forks;
    @JsonProperty("open_issues")
    private long openIssues;
    @JsonProperty("watchers")
    private long watchers;
    @JsonProperty("default_branch")
    private String defaultBranch;
    @JsonProperty("score")
    private long score;
}
