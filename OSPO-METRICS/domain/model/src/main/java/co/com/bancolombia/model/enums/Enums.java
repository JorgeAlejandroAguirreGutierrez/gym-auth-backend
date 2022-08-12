package co.com.bancolombia.model.enums;

public class Enums {
//        Traffics("open_github_repo_traffic"),
//        Languages("open_github_repo_languages"),
//        Contributions("open_github_repo_metrics_contributions"),
    public enum GithubIndex {
        TrafficClones("open_github_repo_metrics_clones"),
        TrafficCodeFrequency("open_github_repo_metrics_code_frecuency"),
        Collaborators("open_github_repo_metrics_collaborators"),
        Commits("open_github_repo_commits"),
        CommitsActivity("open_github_repo_metrics_commits_activity"),
        Contributors("open_github_repo_contributors"),
        Issues("open_github_repo_issues"),
        Pulls("open_github_repo_pulls"),
        Repository("open_github_repo"),
        TrafficViews("open_github_repo_metrics_views");
        public final String value;
        GithubIndex(String value){
            this.value = value;
        }
    }
    public enum GithubUri {
        GITHUB_APP_TOKEN("/app/installations/{token}/access_tokens"),
        GITHUB_REPOS_OWNER("/orgs/{owner}/repos"),
        GITHUB_REPOS_SEARCH("/search/repositories"),
        GITHUB_REPOS_NAME("/repos/{owner}/{repo}"),
        GITHUB_USERS("/users/{login}"),
        GITHUB_CONTRIBUTORS("/repos/{owner}/{name}/contributors"),
        GITHUB_COLLABORATORS("/repos/{owner}/{name}/collaborators"),
        GITHUB_COMMITS("/repos/{owner}/{name}/commits"),
        GITHUB_COMMITS_SHA("/repos/{owner}/{name}/commits/{sha}"),
        GITHUB_ISSUES("repos/{owner}/{name}/issues"),
        GITHUB_ISSUES_NUMBER("repos/{owner}/{name}/issues/{number}"),
        GITHUB_PULLS("/repos/{owner}/{name}/pulls"),
        GITHUB_PULLS_NUMBER("/repos/{owner}/{name}/pulls/{number}"),
        GITHUB_TRAFFIC_CLONES("/repos/{owner}/{name}/traffic/clones"),
        GITHUB_TRAFFIC_VIEWS("/repos/{owner}/{name}/traffic/views"),
        GITHUB_STATS_CODE_FRECUENCY("/repos/{owner}/{name}/stats/code_frequency"),
        GITHUB_STATS_COMMIT_ACTIVITY("/repos/{owner}/{name}/stats/commit_activity");
        public final String value;
        GithubUri(String value){
            this.value = value;
        }
    }
}
