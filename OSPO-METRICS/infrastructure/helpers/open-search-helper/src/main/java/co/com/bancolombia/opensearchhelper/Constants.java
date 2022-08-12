package co.com.bancolombia.opensearchhelper;

import co.com.bancolombia.model.projectstatus.ProjectStatus;

import java.util.Date;

public class Constants {

    public static final String HITS_FIELD = "hits";
    public static final String MATCH_PHRASE_PREFIX = "match_phrase_prefix";

    public static final String REPO_COLLABORATORS_URL = "repo_collaborators_url";
    public static final String REPO_COMMITS_URL = "repo_commits_url";
    public static final String REPO_CONTRIBUTORS_URL = "repo_contributors_url";
    public static final String REPO_CREATED = "repo_created";
    public static final String REPO_DATE_FROM = "repo_date_from";
    public static final String REPO_DATE_TO = "repo_date_to";
    public static final String REPO_DEFAULT_BRANCH = "repo_default_branch";
    public static final String REPO_DESCRIPTION = "repo_description";
    public static final String REPO_DOWNLOADS_URL = "repo_downloads_url";
    public static final String REPO_EVENTS_URL = "repo_events_url";
    public static final String REPO_FORKS = "repo_forks";
    public static final String REPO_FORKS_COUNT = "repo_forks_count";
    public static final String REPO_GROUP_DAY = "repo_group_day";
    public static final String REPO_GROUP_MONTH = "repo_group_month";
    public static final String REPO_GROUP_WEEK = "repo_group_week";
    public static final String REPO_GROUP_YEAR = "repo_group_year";
    public static final String REPO_HAS_DOWNLOADS = "repo_has_downloads";
    public static final String REPO_HAS_ISSUES = "repo_has_issues";
    public static final String REPO_HAS_PAGES = "repo_has_pages";
    public static final String REPO_HAS_PROJECTS = "repo_has_projects";
    public static final String REPO_HAS_WIKI = "repo_has_wiki";
    public static final String REPO_HTTP_URL = "repo_http_url";
    public static final String REPO_HTTPS_URL = "repo_https_url";
    public static final String REPO_ID = "repo_id";
    public static final String REPO_ISSUE_COMMENT_URL = "repo_issue_comment_url";
    public static final String REPO_ISSUE_EVENTS_URL = "repo_issue_events_url";
    public static final String REPO_ISSUES_URL = "repo_issues_url";
    public static final String REPO_KEY = "repo_key";
    public static final String REPO_LANGUAGE = "repo_language";
    public static final String REPO_LICENSE_KEY = "repo_license_key";
    public static final String REPO_LICENSE_NAME = "repo_license_name";
    public static final String REPO_LICENSE_NODE = "repo_license_node";
    public static final String REPO_LICENSE_SPDX_ID = "repo_license_spdx_id";
    public static final String REPO_LICENSE_URL = "repo_license_url";
    public static final String REPO_MILESTONES_URL = "repo_milestones_url";
    public static final String REPO_NAME = "repo_name";
    public static final String REPO_NOTIFICATIONS_URL = "repo_notifications_url";
    public static final String REPO_OPEN_ISSUES = "repo_open_issues";
    public static final String REPO_OPEN_ISSUES_COUNT = "repo_open_issues_count";
    public static final String REPO_OWNER = "repo_owner";
    public static final String REPO_OWNER_ID = "repo_owner_id";
    public static final String REPO_OWNER_NODE_ID = "repo_owner_node_id";
    public static final String REPO_OWNER_TYPE = "repo_owner_type";
    public static final String REPO_PULLS_URL = "repo_pulls_url";
    public static final String REPO_PUSHED = "repo_pushed";
    public static final String REPO_SIZE = "repo_size";
    public static final String REPO_STARGAZERS_COUNT = "repo_stargazers_count";
    public static final String REPO_STARGAZERS_URL = "repo_stargazers_url";
    public static final String REPO_STATUSES_URL = "repo_statuses_url";
    public static final String REPO_SUSCRIBERS_COUNT = "repo_subscribers_count";
    public static final String REPO_SUSCRIBERS_URL = "repo_subscribers_url";
    public static final String REPO_TOKEN = "repo_token";
    public static final String REPO_TREES_URL = "repo_trees_url";
    public static final String REPO_UPDATED = "repo_updated";
    public static final String REPO_VISIBILITY = "repo_visibility";
    public static final String REPO_WATCHERS =  "repo_watchers";
    public static final String REPO_WATCHERS_COUNT = "repo_watchers_count";

    public static final String OBJ_METRIC_ID = "metricId";
    public static final String OBJ_COLLABORATORS_URL = "repoCollaboratorsUrl";
    public static final String OBJ_COMMITS_URL = "repoCommitsUrl";
    public static final String OBJ_CONTRIBUTORS_URL = "repoContributorsUrl";
    public static final String OBJ_CREATED = "repoCreated";
    public static final String OBJ_DATE_FROM = "repoDateFrom";
    public static final String OBJ_DATE_TO = "repoDateTo";
    public static final String OBJ_DEFAULT_BRANCH = "repoDefaultBranch";
    public static final String OBJ_DESCRIPTION = "repoDescription";
    public static final String OBJ_DOWNLOADS_URL = "repoDownloadsUrl";
    public static final String OBJ_EVENTS_URL = "repoEventsUrl";
    public static final String OBJ_FORKS = "repoForks";
    public static final String OBJ_FORKS_COUNT = "repoForksCount";
    public static final String OBJ_GROUP_DAY = "repoGroupDay";
    public static final String OBJ_GROUP_MONTH = "repoGroupMonth";
    public static final String OBJ_GROUP_WEEK = "repoGroupWeek";
    public static final String OBJ_GROUP_YEAR = "repoGroupYear";
    public static final String OBJ_HAS_DOWNLOADS = "repoHasDownloads";
    public static final String OBJ_HAS_ISSUES = "repoHasIssue";
    public static final String OBJ_HAS_PAGES = "repoHasPages";
    public static final String OBJ_HAS_PROJECTS = "repoHasProjects";
    public static final String OBJ_HAS_WIKI = "repoHasWiki";
    public static final String OBJ_HTTP_URL = "repoHttpUrl";
    public static final String OBJ_HTTPS_URL = "repoHttpsUrl";
    public static final String OBJ_ID = "repoId";
    public static final String OBJ_ISSUE_COMMENT_URL = "repoIssueCommentUrl";
    public static final String OBJ_ISSUE_EVENTS_URL = "repoIssueEventsUrl";
    public static final String OBJ_ISSUES_URL = "repoIssuesUrl";
    public static final String OBJ_KEY = "repoKey";
    public static final String OBJ_LANGUAGE = "repoLanguage";
    public static final String OBJ_LICENSE_KEY = "repoLicenseKey";
    public static final String OBJ_LICENSE_NAME = "repoLicenseName";
    public static final String OBJ_LICENSE_NODE = "repoLicenseNode";
    public static final String OBJ_LICENSE_SPDX_ID = "repoLicenseSPDXId";
    public static final String OBJ_LICENSE_URL = "repoLicenseUrl";
    public static final String OBJ_MILESTONES_URL = "repoMilestonesUrl";
    public static final String OBJ_NAME = "repoName";
    public static final String OBJ_NOTIFICATIONS_URL = "repoNotificationsUrl";
    public static final String OBJ_OPEN_ISSUES = "repoOpenIssues";
    public static final String OBJ_OPEN_ISSUES_COUNT = "repoOpenIssuesCount";
    public static final String OBJ_OWNER = "repoOwner";
    public static final String OBJ_OWNER_ID = "repoOwnerId";
    public static final String OBJ_OWNER_NODE_ID = "repoOwnerNodeId";
    public static final String OBJ_OWNER_TYPE = "repoOwnerType";
    public static final String OBJ_PULLS_URL = "repoPullsUrl";
    public static final String OBJ_PUSHED = "repoPushed";
    public static final String OBJ_SIZE = "repoSize";
    public static final String OBJ_STARGAZERS_COUNT = "repoStargazersCount";
    public static final String OBJ_STARGAZERS_URL = "repoStargazersUrl";
    public static final String OBJ_STATUSES_URL = "repoStatusesUrl";
    public static final String OBJ_SUSCRIBERS_COUNT = "repoSuscribersCount";
    public static final String OBJ_SUSCRIBERS_URL = "repoSuscribersUrl";
    public static final String OBJ_TOKEN = "repoToken";
    public static final String OBJ_TREES_URL = "repoTreesUrl";
    public static final String OBJ_UPDATED = "repoUpdated";
    public static final String OBJ_VISIBILITY = "repoVisibility";
    public static final String OBJ_WATCHERS =  "repoWatchers";
    public static final String OBJ_WATCHERS_COUNT = "repoWarchersCount";


    //time-name field grafana
    public static final String TIME_STAMP = "metric_key";

    //name index field grafana
    public static final String MEET_CHANNEL_INDEX = "open_metrics_meets_channels";
    public static final String MEET_CHANNEL_EVENT_INDEX = "open_metrics_meets_channels_events";
    public static final String METRIC_INDEX = "open_metrics";
    public static final String PROJECT_INDEX = "open_metrics_project";
    public static final String PROJECT_DETAIL_INDEX_ = "open_metrics_project_detail";


    //generic messages
    public static final String MSG_GENERIC_NO_ENCONTRO_DATOS_CON_EL_ID_ESPECIFICADO = "No se encontro datos con el id especificado";
    public static final String MSG_GENERIC_ID_YA_EXITE_O_ID_INCORRECTO = "El id especificado ya existe o el id especificado es incorrecto. No puede ser cero.";

}
