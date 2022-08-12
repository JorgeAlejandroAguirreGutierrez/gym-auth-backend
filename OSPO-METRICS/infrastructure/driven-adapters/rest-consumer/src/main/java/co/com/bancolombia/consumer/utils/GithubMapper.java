package co.com.bancolombia.consumer.utils;

import co.com.bancolombia.consumer.dto.*;
import co.com.bancolombia.model.clones.Clones;
import co.com.bancolombia.model.clones.ClonesData;
import co.com.bancolombia.model.codefrecuency.CodeFrecuency;
import co.com.bancolombia.model.codefrecuency.CodeFrecuencyData;
import co.com.bancolombia.model.collaborator.Collaborator;
import co.com.bancolombia.model.commit.Commit;
import co.com.bancolombia.model.commitactivity.CommitActivity;
import co.com.bancolombia.model.contributor.Contributor;
import co.com.bancolombia.model.issue.Issue;
import co.com.bancolombia.model.pull.Pull;
import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.views.Views;
import co.com.bancolombia.model.views.ViewsData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.*;

public class GithubMapper {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    public static Clones mapDataClones(Repo repo, GithubClonesDTO clonesDTO) {
        try {
            List<ClonesData> clonesData = new ArrayList<>();
            clonesDTO.getData().forEach(obj -> {
                clonesData.add(ClonesData.builder()
                        .id(repo.getOwnerId() + "." + repo.getId() + "." + (obj.getTimestamp().getTime() / 1000))
                        .key(dateFormat.format(obj.getTimestamp()))
                        .count(obj.getCount())
                        .uniques(obj.getUniques())
                        .date(obj.getTimestamp())
                        .dateFrom(obj.getDateFrom())
                        .dateTo(obj.getDateTo())
                        .build());
            });
            return Clones.builder()
                    .repo(repo)
                    .count(clonesDTO.getCount())
                    .uniques(clonesDTO.getUniques())
                    .data(clonesData)
                    .build();
        } catch (Exception ex) {
            return Clones.builder()
                    .repo(repo)
                    .count(0)
                    .uniques(0)
                    .data(new ArrayList<>())
                    .build();
        }
    }
    public static CodeFrecuency mapDataCodeFrecuency(Repo repo, String string) {
        try {
            JsonNode json = new ObjectMapper().readTree(string);

            List<JsonNode> resData = new ArrayList<>();
            json.forEach(obj -> resData.add(obj));

            List<CodeFrecuencyData> codeFrecuencyData = new ArrayList<>();
            resData.forEach(obj -> {
                Date dateFrom = new Date(obj.get(0).asLong() * 1000);
                Calendar dateTo = Calendar.getInstance();
                dateTo.setTime(dateFrom);
                dateTo.add(Calendar.DATE, 7);

                codeFrecuencyData.add(CodeFrecuencyData.builder()
                        .id(repo.getOwnerId() + "." + repo.getId() + "." + (dateFrom.getTime() / 1000))
                        .key(dateFormat.format(dateFrom))
                        .week(obj.get(0).asLong())
                        .dateFrom(dateFrom)
                        .dateTo(dateTo.getTime())
                        .additions(obj.get(1).asLong())
                        .deletions(obj.get(2).asLong())
                        .build());
            });

            return CodeFrecuency.builder()
                    .repo(repo)
                    .data(codeFrecuencyData)
                    .build();
        } catch (Exception e) {
            return CodeFrecuency.builder()
                    .repo(repo)
                    .data(new ArrayList<>())
                    .build();
        }
    }
    public static Collaborator mapDataCollaborator(GithubCollaboratorDTO collaboratorDTO) {
        return Collaborator.builder()
                .userId(collaboratorDTO.getId())
                .userLogin(collaboratorDTO.getLogin())
                .build();
    }
    public static Collaborator mapDataCollaboratorUser(GithubUserDTO userDTO) {
        return Collaborator.builder()
                .userId(userDTO.getId())
                .userNodeId(userDTO.getNodeId())
                .userLogin(userDTO.getLogin())
                .userName(userDTO.getName())
                .userEmail(userDTO.getEmail())
                .userLocation(userDTO.getLocation())
                .userType(userDTO.getType())
                .build();
    }
    public static Commit mapDataCommit(GithubCommitDTO commitDTO) {
        try {
            return Commit.builder()
                    .sha(commitDTO.getSha())
                    .userCommitAuthorName(commitDTO.getCommit().getAuthor().getName())
                    .userCommitAuthorEmail(commitDTO.getCommit().getAuthor().getEmail())
                    .dateCommitAuthorCreated(commitDTO.getCommit().getAuthor().getDate())
                    .userCommitCommitterName(commitDTO.getCommit().getCommitter().getName())
                    .userCommitCommitterEmail(commitDTO.getCommit().getCommitter().getEmail())
                    .dateCommitCommitterCreated(commitDTO.getCommit().getCommitter().getDate())
                    .dateCommitAuthorCreated(commitDTO.getCommit().getAuthor().getDate())
                    .userAuthorId(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getId() : 0)
                    .userAuthorLogin(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getLogin() : "")
                    .userAuthorName(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getName() : "")
                    .userAuthorEmail(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getEmail() : "")
                    .userAuthorLocation(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getLocation() : "")
                    .userAuthorType(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getType() : "")
                    .userCommitterId(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getId() : 0)
                    .userCommitterLogin(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getLogin() : "")
                    .userCommitterName(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getName() : "")
                    .userCommitterEmail(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getEmail() : "")
                    .userCommitterLocation(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getLocation() : "")
                    .userCommitterType(Objects.nonNull(commitDTO.getCommitter())? commitDTO.getCommitter().getType() : "")
                    .build();
        } catch (Exception ex) {
            return Commit.builder()
                    .sha("")
                    .build();
        }
    }
    public static Commit mapDataCommitSHA(GithubCommitDTO commitDTO) {
        try {
            return Commit.builder()
                    .sha(commitDTO.getSha())
                    .userCommitAuthorName(commitDTO.getCommit().getAuthor().getName())
                    .userCommitAuthorEmail(commitDTO.getCommit().getAuthor().getEmail())
                    .dateCommitAuthorCreated(commitDTO.getCommit().getAuthor().getDate())
                    .userCommitCommitterName(commitDTO.getCommit().getCommitter().getName())
                    .userCommitCommitterEmail(commitDTO.getCommit().getCommitter().getEmail())
                    .dateCommitCommitterCreated(commitDTO.getCommit().getCommitter().getDate())
                    .dateCommitAuthorCreated(commitDTO.getCommit().getAuthor().getDate())
                    .userAuthorId(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getId() : 0)
                    .userAuthorLogin(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getLogin() : "")
                    .userAuthorName(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getName() : "")
                    .userAuthorEmail(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getEmail() : "")
                    .userAuthorLocation(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getLocation() : "")
                    .userAuthorType(Objects.nonNull(commitDTO.getAuthor()) ? commitDTO.getAuthor().getType() : "")
                    .userCommitterId(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getId() : 0)
                    .userCommitterLogin(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getLogin() : "")
                    .userCommitterName(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getName() : "")
                    .userCommitterEmail(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getEmail() : "")
                    .userCommitterLocation(Objects.nonNull(commitDTO.getCommitter()) ? commitDTO.getCommitter().getLocation() : "")
                    .userCommitterType(Objects.nonNull(commitDTO.getCommitter())? commitDTO.getCommitter().getType() : "")
                    .statsAdditions(commitDTO.getStats().getAdditions())
                    .statsDeletions(commitDTO.getStats().getDeletions())
                    .statsTotal(commitDTO.getStats().getTotal())
                    .build();
        } catch (Exception ex) {
            return Commit.builder()
                    .sha("")
                    .build();
        }
    }
    public static CommitActivity mapDataCommitActivity(Repo repo, GithubCommitActivityDTO commitActivityDTO) {
        try {
            return CommitActivity.builder()
                    .id(repo.getOwnerId() + "." + repo.getId() + "." + (commitActivityDTO.getDateTo().getTime() / 1000))
                    .key(dateFormat.format(commitActivityDTO.getDateTo()))
                    .week(commitActivityDTO.getWeek())
                    .dateFrom(commitActivityDTO.getDateFrom())
                    .dateTo(commitActivityDTO.getDateTo())
                    .totalSunday(commitActivityDTO.getDays()[0])
                    .totalMonday(commitActivityDTO.getDays()[1])
                    .totalTuesday(commitActivityDTO.getDays()[2])
                    .totalWednesday(commitActivityDTO.getDays()[3])
                    .totalThursday(commitActivityDTO.getDays()[4])
                    .totalFriday(commitActivityDTO.getDays()[5])
                    .totalSaturday(commitActivityDTO.getDays()[6])
                    .total(commitActivityDTO.getTotal())
                    .build();
        } catch (Exception ex) {
            return CommitActivity.builder()
                    .id("")
                    .build();
        }
    }
    public static Contributor mapDataContributor(GithubContributorDTO githubContributorDTO) {
        return Contributor.builder()
                .userId(githubContributorDTO.getId())
                .userLogin(githubContributorDTO.getLogin())
                .userContributions(githubContributorDTO.getContributions())
                .build();
    }
    public static Contributor mapDataContributorUser(GithubUserDTO userDTO, Contributor contributor) {
        return Contributor.builder()
                .userId(userDTO.getId())
                .userNodeId(userDTO.getNodeId())
                .userLogin(userDTO.getLogin())
                .userName(userDTO.getName())
                .userEmail(userDTO.getEmail())
                .userLocation(userDTO.getLocation())
                .userType(userDTO.getType())
                .userContributions(contributor.getUserContributions())
                .build();
    }
    public static Issue mapDataIssue(GithubIssueDTO issueDTO) {
        try {
            return Issue.builder()
                    .id(issueDTO.getId())
                    .nodeId(issueDTO.getNodeId())
                    .number(issueDTO.getNumber())
                    .title(issueDTO.getTitle())
                    .userId(issueDTO.getUser().getId())
                    .userLogin(issueDTO.getUser().getLogin())
                    .userName(issueDTO.getUser().getName())
                    .userEmail(issueDTO.getUser().getEmail())
                    .userLocation(issueDTO.getUser().getLocation())
                    .userType(issueDTO.getUser().getType())
                    .dateCreated(issueDTO.getDateCreated())
                    .dateUpdated(issueDTO.getDateUpdated())
                    .dateClosed(Objects.nonNull(issueDTO.getDateClosed()) ? issueDTO.getDateClosed() : null)
                    .daysOpen(getIssueDiffDays(
                            "open",
                            issueDTO.getState(),
                            issueDTO.getDateCreated(),
                            issueDTO.getDateClosed()))
                    .daysClosed(getIssueDiffDays(
                            "closed",
                            issueDTO.getState(),
                            issueDTO.getDateCreated(),
                            issueDTO.getDateClosed()))
                    .state(issueDTO.getState())
                    .build();
        } catch (Exception ex) {
            return Issue.builder()
                    .id(0)
                    .build();
        }
    }
    public static Pull mapDataPull(GithubPullDTO githubPullDTO) {
        try {
            return Pull.builder()
                    .id(githubPullDTO.getId())
                    .number(githubPullDTO.getNumber())
                    .title(githubPullDTO.getTitle())
                    .state(githubPullDTO.getState())
                    .locked(githubPullDTO.getLocked())
                    .userId(githubPullDTO.getUser().getId())
                    .userLogin(githubPullDTO.getUser().getLogin())
                    .userName(githubPullDTO.getUser().getName())
                    .userEmail(githubPullDTO.getUser().getEmail())
                    .userLocation(githubPullDTO.getUser().getLocation())
                    .dateCreated(githubPullDTO.getDateCreated())
                    .dateUpdated(githubPullDTO.getDateUpdated())
                    .dateClosed(githubPullDTO.getDateClosed())
                    .dateMerged(githubPullDTO.getDateMerged())
                    .mergeCommitSHA(githubPullDTO.getMergeCommitSHA())
                    .build();
        } catch (Exception ex) {
            return Pull.builder()
                    .id(0)
                    .build();
        }
    }
    public static Repo mapDataRepo(GithubRepoDTO githubRepoDTO) {
        return Repo.builder()
                .id(githubRepoDTO.getId())
                .nodeId(githubRepoDTO.getNodeId())
                .name(githubRepoDTO.getName())
                .description(githubRepoDTO.getDescription())
                .ownerId(githubRepoDTO.getOwner().getId())
                .ownerNodeId(githubRepoDTO.getOwner().getNodeId())
                .ownerLogin(githubRepoDTO.getOwner().getLogin())
                .ownerType(githubRepoDTO.getOwner().getType())
                .ownerAvatarUrl(githubRepoDTO.getOwner().getAvatarUrl())
                .dateCreated(githubRepoDTO.getDateCreated())
                .dateUpdated(githubRepoDTO.getDateUpdated())
                .datePushed(githubRepoDTO.getDatePushed())
                .dateProcessed(githubRepoDTO.getDateCreated())
                .topics(githubRepoDTO.getTopics())
                .licenseKey(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getKey() : "")
                .licenseName(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getName() : "")
                .licenseSPDXId(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getSPDXId() : "")
                .licenseUrl(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getUrl() : "")
                .licenseNodeId(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getNodeId() : "")
                .language(githubRepoDTO.getLanguage())
                .size(githubRepoDTO.getSize())
                .hasIssues(githubRepoDTO.getHasIssues())
                .hasProjects(githubRepoDTO.getHasProjects())
                .hasDownloads(githubRepoDTO.getHasDownloads())
                .hasWiki(githubRepoDTO.getHasWiki())
                .hasPages(githubRepoDTO.getHasPages())
                .url(githubRepoDTO.getUrl())
                .urlHTML(githubRepoDTO.getUrlHTML())
                .urlCommits(githubRepoDTO.getUrlCommits())
                .urlCollaborators(githubRepoDTO.getUrlCollaborators())
                .urlContributors(githubRepoDTO.getUrlContributors())
                .urlDownloads(githubRepoDTO.getUrlDownloads())
                .urlEvents(githubRepoDTO.getUrlEvents())
                .urlIssues(githubRepoDTO.getUrlIssues())
                .urlIssueEvents(githubRepoDTO.getUrlIssueEvents())
                .urlIssueComments(githubRepoDTO.getUrlIssueComment())
                .urlPulls(githubRepoDTO.getUrlPulls())
                .urlNotifications(githubRepoDTO.getUrlNotifications())
                .urlMilestones(githubRepoDTO.getUrlMilestones())
                .urlStatuses(githubRepoDTO.getUrlStatuses())
                .urlTrees(githubRepoDTO.getUrlTrees())
                .urlStargazers(githubRepoDTO.getUrlStargazers())
                .stargazersCount(githubRepoDTO.getStargazersCount())
                .urlSubscribers(githubRepoDTO.getUrlSubscribers())
                .watchersCount(githubRepoDTO.getWatchersCount())
                .forksCount(githubRepoDTO.getForksCount())
                .openIssuesCount(githubRepoDTO.getOpenIssuesCount())
                .openIssues(githubRepoDTO.getOpenIssues())
                .forks(githubRepoDTO.getForks())
                .watchers(githubRepoDTO.getWatchers())
                .defaultBranch(githubRepoDTO.getDefaultBranch())
                .visibility(githubRepoDTO.getVisibility())
                .isPrivate(githubRepoDTO.getIsPrivate())
                .isFork(githubRepoDTO.getIsFork())
                .score(githubRepoDTO.getScore())
                .build();
    }
    public static Repo[] mapDataRepoSearch(GithubRepoSearchDTO githubRepoSearchDTO) {
        List<Repo> repoData = new ArrayList<>();
        githubRepoSearchDTO.getItems().forEach(githubRepoDTO -> {
            repoData.add(Repo.builder()
                    .id(githubRepoDTO.getId())
                    .nodeId(githubRepoDTO.getNodeId())
                    .name(githubRepoDTO.getName())
                    .description(githubRepoDTO.getDescription())
                    .ownerId(githubRepoDTO.getOwner().getId())
                    .ownerNodeId(githubRepoDTO.getOwner().getNodeId())
                    .ownerLogin(githubRepoDTO.getOwner().getLogin())
                    .ownerType(githubRepoDTO.getOwner().getType())
                    .ownerAvatarUrl(githubRepoDTO.getOwner().getAvatarUrl())
                    .dateCreated(githubRepoDTO.getDateCreated())
                    .dateUpdated(githubRepoDTO.getDateUpdated())
                    .datePushed(githubRepoDTO.getDatePushed())
                    .dateProcessed(githubRepoDTO.getDateCreated())
                    .topics(githubRepoDTO.getTopics())
                    .licenseKey(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getKey() : "")
                    .licenseName(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getName() : "")
                    .licenseSPDXId(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getSPDXId() : "")
                    .licenseUrl(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getUrl() : "")
                    .licenseNodeId(githubRepoDTO.getLicense() != null ? githubRepoDTO.getLicense().getNodeId() : "")
                    .language(githubRepoDTO.getLanguage())
                    .size(githubRepoDTO.getSize())
                    .hasIssues(githubRepoDTO.getHasIssues())
                    .hasProjects(githubRepoDTO.getHasProjects())
                    .hasDownloads(githubRepoDTO.getHasDownloads())
                    .hasWiki(githubRepoDTO.getHasWiki())
                    .hasPages(githubRepoDTO.getHasPages())
                    .url(githubRepoDTO.getUrl())
                    .urlHTML(githubRepoDTO.getUrlHTML())
                    .urlCommits(githubRepoDTO.getUrlCommits())
                    .urlCollaborators(githubRepoDTO.getUrlCollaborators())
                    .urlContributors(githubRepoDTO.getUrlContributors())
                    .urlDownloads(githubRepoDTO.getUrlDownloads())
                    .urlEvents(githubRepoDTO.getUrlEvents())
                    .urlIssues(githubRepoDTO.getUrlIssues())
                    .urlIssueEvents(githubRepoDTO.getUrlIssueEvents())
                    .urlIssueComments(githubRepoDTO.getUrlIssueComment())
                    .urlPulls(githubRepoDTO.getUrlPulls())
                    .urlNotifications(githubRepoDTO.getUrlNotifications())
                    .urlMilestones(githubRepoDTO.getUrlMilestones())
                    .urlStatuses(githubRepoDTO.getUrlStatuses())
                    .urlTrees(githubRepoDTO.getUrlTrees())
                    .urlStargazers(githubRepoDTO.getUrlStargazers())
                    .urlSubscribers(githubRepoDTO.getUrlSubscribers())
                    .stargazersCount(githubRepoDTO.getStargazersCount())
                    .watchersCount(githubRepoDTO.getWatchersCount())
                    .forksCount(githubRepoDTO.getForksCount())
                    .openIssuesCount(githubRepoDTO.getOpenIssuesCount())
                    .openIssues(githubRepoDTO.getOpenIssues())
                    .forks(githubRepoDTO.getForks())
                    .watchers(githubRepoDTO.getWatchers())
                    .defaultBranch(githubRepoDTO.getDefaultBranch())
                    .visibility(githubRepoDTO.getVisibility())
                    .isPrivate(githubRepoDTO.getIsPrivate())
                    .isFork(githubRepoDTO.getIsFork())
                    .score(githubRepoDTO.getScore())
                    .build());
        });
        Repo[] array = new Repo[repoData.size()];
        repoData.toArray(array);
        return array;
    }
    public static User mapDataUser(GithubUserDTO githubUserDTO) {
        return User.builder()
                .id(githubUserDTO.getId())
                .login(githubUserDTO.getLogin())
                .name(githubUserDTO.getName())
                .email(githubUserDTO.getEmail())
                .location(githubUserDTO.getLocation())
                .type(githubUserDTO.getType())
                .build();
    }
    public static Views mapDataViews(Repo repo, GithubViewsDTO viewsDTO) {
        try {
            List<ViewsData> viewsData = new ArrayList<>();
            viewsDTO.getData().forEach(obj -> {
                viewsData.add(ViewsData.builder()
                        .id(repo.getOwnerId() + "." + repo.getId() + "." + (obj.getTimestamp().getTime() / 1000))
                        .key(dateFormat.format(obj.getTimestamp()))
                        .uniques(obj.getUniques())
                        .count(obj.getCount())
                        .date(obj.getTimestamp())
                        .dateFrom(obj.getDateFrom())
                        .dateTo(obj.getDateTo())
                        .build());
            });
            return Views.builder()
                    .count(viewsDTO.getCount())
                    .uniques(viewsDTO.getUniques())
                    .data(viewsData)
                    .build();
        } catch (Exception ex) {
            return Views.builder()
                    .repo(repo)
                    .count(0)
                    .uniques(0)
                    .data(new ArrayList<>())
                    .build();
        }
    }
    public static int getIssueDiffDays(String stateReview, String state, Date dateCreated, Date dateClosed) {
        int diff = 0;
        if (stateReview.equals("open")) {
            long dateCreatedInMs = 0;
            long dateClosedInMs = 0;
            long timeDiff = 0;
            if (state.equals("open")) {
                dateCreatedInMs = dateCreated.getTime();
                dateClosedInMs = new Date(System.currentTimeMillis()).getTime();
            }
            if (state.equals("closed")) {
                dateCreatedInMs = dateCreated.getTime();
                dateClosedInMs = dateClosed.getTime();
            }
            if (dateCreatedInMs > dateClosedInMs) {
                timeDiff = dateCreatedInMs - dateClosedInMs;
            } else {
                timeDiff = dateClosedInMs - dateCreatedInMs;
            }
            diff = (int)(timeDiff / (1000 * 60 * 60 * 24));
        }

        if (stateReview.equals("closed")) {
            if (state.equals("closed")) {
                long dateCreatedInMs = dateCreated.getTime();
                long dateClosedInMs = dateClosed.getTime();
                long timeDiff = 0;

                if (dateCreatedInMs > dateClosedInMs) {
                    timeDiff = dateCreatedInMs - dateClosedInMs;
                } else {
                    timeDiff = dateClosedInMs - dateCreatedInMs;
                }
                diff = (int)(timeDiff / (1000 * 60 * 60 * 24));
            }
        }

        return diff;
    }
}
