package co.com.bancolombia.opensearchhelper;

import co.com.bancolombia.model.meetchannel.OpenSearchRequestMeetChannel;
import co.com.bancolombia.model.meetchannelevent.OpenSearchRequestMeetChannelEvent;
import co.com.bancolombia.model.metric.OpenSearchRequestMetric;
import co.com.bancolombia.model.opensearchservice.*;
import co.com.bancolombia.model.project.OpenSearchRequestProject;
import co.com.bancolombia.model.projectdetail.OpenSearchRequestProjectDetail;
import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
import co.com.bancolombia.opensearchhelper.dto.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class OpenSearchMapperHelper {

    private static final Logger logger = LoggerFactory.getLogger(OpenSearchMapperHelper.class);

    @Autowired
    private ObjectMapper objectMapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public Mono<OpenSearchRequest> getOpenSearchRequest(String indexName) {
        logger.debug("IndexName: " + indexName);
        return Mono.just(
                OpenSearchRequest.builder()
                        .indexName(indexName)
                        .build());
    }
    public Mono<OpenSearchRequest> getOpenSearchRequestMono(String jsonValue) {
        try {
            logger.debug("Request: " + jsonValue);
            JsonNode json = objectMapper.readTree(jsonValue);
            return Mono.just(
                    OpenSearchRequest.builder()
                            .indexName(json.get("indexName").asText())
                            .filter(buildFilterObject(json))
                            .metric(buildGitHubMetric(json))
                            .build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getOpenSearchCreateRequestMono(OpenSearchRequest request) {
        ObjectNode create = buildDocObject(request);
        return create.toString();
    }

    public String getOpenSearchUpdateRequestMono(OpenSearchRequest request) {
        ObjectNode update = objectMapper.createObjectNode();
        ObjectNode docInfo = buildDocObject(request);
        return update.putPOJO("doc", docInfo).toString();
    }

    public Mono<OpenSearchResponse> getOpenSearchResponseMono(String index, String jsonRes) {
        try {
            logger.info(jsonRes);
            JsonNode json = objectMapper.readTree(jsonRes);
            return Mono.just(getConsultOpenSearchResponse(index, json));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getQueryToConsult(OpenSearchRequest request) {
        logger.info("Building query to OpenSearch...");
        ObjectNode query = objectMapper.createObjectNode();
        ObjectNode matchPhrasePrefix = objectMapper.createObjectNode();
        return query.putPOJO("query", createQueryByField(
                request.getFilter().getKey(), request.getFilter().getValue())).toString();
    }

    private OpenSearchResponse getConsultOpenSearchResponse(String index, JsonNode json) {
        logger.info("Processing response: " + json.toString());
        JsonNode indexInfo = json.get(Constants.HITS_FIELD);
        ArrayNode metrics = (ArrayNode) indexInfo.get(Constants.HITS_FIELD);

        List<GitHubMetric> resData = new ArrayList<>();
        metrics.forEach(obj -> resData.add(convertJsonToGitHubMetric(obj)));

        return OpenSearchResponse.builder()
                .indexName(index)
                .data(resData)
                .build();
    }

    private Filter buildFilterObject(JsonNode json) {
        Optional<JsonNode> filter = Optional.ofNullable(json.get("filter"));
         if (filter.isPresent()) {
             logger.info("Building filter: " + filter.get().toString());
             return Filter.builder()
                     .key(filter.get().get("key").asText())
                     .value(filter.get().get("value").asText())
                     .build();
         } else {
             return null;
         }
    }

    private GitHubMetric convertJsonToGitHubMetric(JsonNode json) {
        logger.info("Processing response: " + json.toString());
        JsonNode source = json.get("_source");
        return GitHubMetric.builder()
                .metricId(json.get("_id").asText())
                .source(GitHubMetricSource.builder()
                        .repoCollaboratorsUrl(getStringFromJsonNode(source.get(Constants.REPO_COLLABORATORS_URL)))
                        .repoCommitsUrl(getStringFromJsonNode(source.get(Constants.REPO_COMMITS_URL)))
                        .repoContributorsUrl(getStringFromJsonNode(source.get(Constants.REPO_CONTRIBUTORS_URL)))
                        .repoCreated(parseStringToDate(source.get(Constants.REPO_CREATED)))
                        .repoDateFrom(parseStringToDate(source.get(Constants.REPO_DATE_FROM)))
                        .repoDateTo(parseStringToDate(source.get(Constants.REPO_DATE_TO)))
                        .repoDefaultBranch(getStringFromJsonNode(source.get(Constants.REPO_DEFAULT_BRANCH)))
                        .repoDescription(getStringFromJsonNode(source.get(Constants.REPO_DESCRIPTION)))
                        .repoDownloadsUrl(getStringFromJsonNode(source.get(Constants.REPO_DOWNLOADS_URL)))
                        .repoEventsUrl(getStringFromJsonNode(source.get(Constants.REPO_EVENTS_URL)))
                        .repoForks(getLongFromJsonNode(source.get(Constants.REPO_FORKS)))
                        .repoForksCount(getLongFromJsonNode(source.get(Constants.REPO_FORKS_COUNT)))
                        .repoGroupDay(getIntegerFromJsonNode(source.get(Constants.REPO_GROUP_DAY)))
                        .repoGroupMonth(getIntegerFromJsonNode(source.get(Constants.REPO_GROUP_MONTH)))
                        .repoGroupWeek(getIntegerFromJsonNode(source.get(Constants.REPO_GROUP_WEEK)))
                        .repoGroupYear(getIntegerFromJsonNode(source.get(Constants.REPO_GROUP_YEAR)))
                        .repoHasDownloads(getBooleanFromJsonNode(source.get(Constants.REPO_HAS_DOWNLOADS)))
                        .repoHasIssue(getBooleanFromJsonNode(source.get(Constants.REPO_HAS_ISSUES)))
                        .repoHasPages(getBooleanFromJsonNode(source.get(Constants.REPO_HAS_PAGES)))
                        .repoHasProjects(getBooleanFromJsonNode(source.get(Constants.REPO_HAS_PROJECTS)))
                        .repoHasWiki(getBooleanFromJsonNode(source.get(Constants.REPO_HAS_WIKI)))
                        .repoHttpUrl(getStringFromJsonNode(source.get(Constants.REPO_HTTP_URL)))
                        .repoHttpsUrl(getStringFromJsonNode(source.get(Constants.REPO_HTTPS_URL)))
                        .repoId(getStringFromJsonNode(source.get(Constants.REPO_ID)))
                        .repoIssueCommentUrl(getStringFromJsonNode(source.get(Constants.REPO_ISSUE_COMMENT_URL)))
                        .repoIssueEventsUrl(getStringFromJsonNode(source.get(Constants.REPO_ISSUE_EVENTS_URL)))
                        .repoIssuesUrl(getStringFromJsonNode(source.get(Constants.REPO_ISSUES_URL)))
                        .repoKey(getStringFromJsonNode(source.get(Constants.REPO_KEY)))
                        .repoLanguage(getStringFromJsonNode(source.get(Constants.REPO_LANGUAGE)))
                        .repoLicenseKey(getStringFromJsonNode(source.get(Constants.REPO_LICENSE_KEY)))
                        .repoLicenseName(getStringFromJsonNode(source.get(Constants.REPO_LICENSE_NAME)))
                        .repoLicenseNode(getStringFromJsonNode(source.get(Constants.REPO_LICENSE_NODE)))
                        .repoLicenseSPDXId(getStringFromJsonNode(source.get(Constants.REPO_LICENSE_SPDX_ID)))
                        .repoLicenseUrl(getStringFromJsonNode(source.get(Constants.REPO_LICENSE_URL)))
                        .repoMilestonesUrl(getStringFromJsonNode(source.get(Constants.REPO_MILESTONES_URL)))
                        .repoName(getStringFromJsonNode(source.get(Constants.REPO_NAME)))
                        .repoNotificationsUrl(getStringFromJsonNode(source.get(Constants.REPO_NOTIFICATIONS_URL)))
                        .repoOpenIssues(getLongFromJsonNode(source.get(Constants.REPO_OPEN_ISSUES)))
                        .repoOpenIssuesCount(getLongFromJsonNode(source.get(Constants.REPO_OPEN_ISSUES_COUNT)))
                        .repoOwner(getStringFromJsonNode(source.get(Constants.REPO_OWNER)))
                        .repoOwnerId(getStringFromJsonNode(source.get(Constants.REPO_OWNER_ID)))
                        .repoOwnerNodeId(getStringFromJsonNode(source.get(Constants.REPO_OWNER_NODE_ID)))
                        .repoOwnerType(getStringFromJsonNode(source.get(Constants.REPO_OWNER_TYPE)))
                        .repoPullsUrl(getStringFromJsonNode(source.get(Constants.REPO_PULLS_URL)))
                        .repoPushed(parseStringToDate(source.get(Constants.REPO_PUSHED)))
                        .repoSize(getLongFromJsonNode(source.get(Constants.REPO_SIZE)))
                        .repoStargazersCount(getLongFromJsonNode(source.get(Constants.REPO_STARGAZERS_COUNT)))
                        .repoStargazersUrl(getStringFromJsonNode(source.get(Constants.REPO_STARGAZERS_URL)))
                        .repoStatusesUrl(getStringFromJsonNode(source.get(Constants.REPO_STATUSES_URL)))
                        .repoSuscribersCount(getLongFromJsonNode(source.get(Constants.REPO_SUSCRIBERS_COUNT)))
                        .repoSuscribersUrl(getStringFromJsonNode(source.get(Constants.REPO_SUSCRIBERS_URL)))
                        .repoToken(getStringFromJsonNode(source.get(Constants.REPO_TOKEN)))
                        .repoTreesUrl(getStringFromJsonNode(source.get(Constants.REPO_TREES_URL)))
                        .repoUpdated(parseStringToDate(source.get(Constants.REPO_UPDATED)))
                        .repoVisibility(getStringFromJsonNode(source.get(Constants.REPO_VISIBILITY)))
                        .repoWatchers(getLongFromJsonNode(source.get(Constants.REPO_WATCHERS)))
                        .repoWarchersCount(getLongFromJsonNode(source.get(Constants.REPO_WATCHERS_COUNT)))
                        .build())
                .build();
    }

    private ObjectNode buildDocObject(OpenSearchRequest request) {
        ObjectNode docInfo = objectMapper.createObjectNode();
        docInfo.put(Constants.REPO_COLLABORATORS_URL, request.getMetric().getSource().getRepoCollaboratorsUrl());
        docInfo.put(Constants.REPO_COMMITS_URL, request.getMetric().getSource().getRepoCommitsUrl());
        docInfo.put(Constants.REPO_CONTRIBUTORS_URL, request.getMetric().getSource().getRepoContributorsUrl());
        docInfo.put(Constants.REPO_CREATED, sdf.format(request.getMetric().getSource().getRepoCreated()));
        docInfo.put(Constants.REPO_DATE_FROM, sdf.format(request.getMetric().getSource().getRepoDateFrom()));
        docInfo.put(Constants.REPO_DATE_TO, sdf.format(request.getMetric().getSource().getRepoDateTo()));
        docInfo.put(Constants.REPO_DEFAULT_BRANCH, request.getMetric().getSource().getRepoDefaultBranch());
        docInfo.put(Constants.REPO_DESCRIPTION, request.getMetric().getSource().getRepoDescription());
        docInfo.put(Constants.REPO_DOWNLOADS_URL, request.getMetric().getSource().getRepoDownloadsUrl());
        docInfo.put(Constants.REPO_EVENTS_URL, request.getMetric().getSource().getRepoEventsUrl());
        docInfo.put(Constants.REPO_FORKS, request.getMetric().getSource().getRepoForks());
        docInfo.put(Constants.REPO_FORKS_COUNT, request.getMetric().getSource().getRepoForksCount());
        docInfo.put(Constants.REPO_GROUP_DAY, request.getMetric().getSource().getRepoGroupDay());
        docInfo.put(Constants.REPO_GROUP_MONTH, request.getMetric().getSource().getRepoGroupMonth());
        docInfo.put(Constants.REPO_GROUP_WEEK, request.getMetric().getSource().getRepoGroupWeek());
        docInfo.put(Constants.REPO_GROUP_YEAR, request.getMetric().getSource().getRepoGroupYear());
        docInfo.put(Constants.REPO_HAS_DOWNLOADS, request.getMetric().getSource().getRepoHasDownloads());
        docInfo.put(Constants.REPO_HAS_ISSUES, request.getMetric().getSource().getRepoHasIssue());
        docInfo.put(Constants.REPO_HAS_PAGES, request.getMetric().getSource().getRepoHasPages());
        docInfo.put(Constants.REPO_HAS_PROJECTS, request.getMetric().getSource().getRepoHasProjects());
        docInfo.put(Constants.REPO_HAS_WIKI, request.getMetric().getSource().getRepoHasWiki());
        docInfo.put(Constants.REPO_HTTP_URL, request.getMetric().getSource().getRepoHttpUrl());
        docInfo.put(Constants.REPO_HTTPS_URL, request.getMetric().getSource().getRepoHttpsUrl());
        docInfo.put(Constants.REPO_ID, request.getMetric().getSource().getRepoId());
        docInfo.put(Constants.REPO_ISSUE_COMMENT_URL, request.getMetric().getSource().getRepoIssueCommentUrl());
        docInfo.put(Constants.REPO_ISSUE_EVENTS_URL, request.getMetric().getSource().getRepoIssueEventsUrl());
        docInfo.put(Constants.REPO_ISSUES_URL, request.getMetric().getSource().getRepoIssuesUrl());
        docInfo.put(Constants.REPO_KEY, request.getMetric().getSource().getRepoKey());
        docInfo.put(Constants.REPO_LANGUAGE, request.getMetric().getSource().getRepoLanguage());
        docInfo.put(Constants.REPO_LICENSE_KEY, request.getMetric().getSource().getRepoLicenseKey());
        docInfo.put(Constants.REPO_LICENSE_NAME, request.getMetric().getSource().getRepoLicenseName());
        docInfo.put(Constants.REPO_LICENSE_NODE, request.getMetric().getSource().getRepoLicenseNode());
        docInfo.put(Constants.REPO_LICENSE_SPDX_ID, request.getMetric().getSource().getRepoLicenseSPDXId());
        docInfo.put(Constants.REPO_LICENSE_URL, request.getMetric().getSource().getRepoLicenseUrl());
        docInfo.put(Constants.REPO_MILESTONES_URL, request.getMetric().getSource().getRepoMilestonesUrl());
        docInfo.put(Constants.REPO_NAME, request.getMetric().getSource().getRepoName());
        docInfo.put(Constants.REPO_NOTIFICATIONS_URL, request.getMetric().getSource().getRepoNotificationsUrl());
        docInfo.put(Constants.REPO_OPEN_ISSUES, request.getMetric().getSource().getRepoOpenIssues());
        docInfo.put(Constants.REPO_OPEN_ISSUES_COUNT, request.getMetric().getSource().getRepoOpenIssuesCount());
        docInfo.put(Constants.REPO_OWNER, request.getMetric().getSource().getRepoOwner());
        docInfo.put(Constants.REPO_OWNER_ID, request.getMetric().getSource().getRepoOwnerId());
        docInfo.put(Constants.REPO_OWNER_NODE_ID, request.getMetric().getSource().getRepoOwnerNodeId());
        docInfo.put(Constants.REPO_OWNER_TYPE, request.getMetric().getSource().getRepoOwnerType());
        docInfo.put(Constants.REPO_PULLS_URL, request.getMetric().getSource().getRepoPullsUrl());
        docInfo.put(Constants.REPO_PUSHED, sdf.format(request.getMetric().getSource().getRepoPushed()));
        docInfo.put(Constants.REPO_SIZE, request.getMetric().getSource().getRepoSize());
        docInfo.put(Constants.REPO_STARGAZERS_COUNT, request.getMetric().getSource().getRepoStargazersCount());
        docInfo.put(Constants.REPO_STARGAZERS_URL, request.getMetric().getSource().getRepoStargazersUrl());
        docInfo.put(Constants.REPO_STATUSES_URL, request.getMetric().getSource().getRepoStatusesUrl());
        docInfo.put(Constants.REPO_SUSCRIBERS_COUNT, request.getMetric().getSource().getRepoSuscribersCount());
        docInfo.put(Constants.REPO_SUSCRIBERS_URL, request.getMetric().getSource().getRepoSuscribersUrl());
        docInfo.put(Constants.REPO_TOKEN, request.getMetric().getSource().getRepoToken());
        docInfo.put(Constants.REPO_TREES_URL, request.getMetric().getSource().getRepoTreesUrl());
        docInfo.put(Constants.REPO_UPDATED, sdf.format(request.getMetric().getSource().getRepoUpdated()));
        docInfo.put(Constants.REPO_VISIBILITY, request.getMetric().getSource().getRepoVisibility());
        docInfo.put(Constants.REPO_WATCHERS, request.getMetric().getSource().getRepoWatchers());
        docInfo.put(Constants.REPO_WATCHERS_COUNT, request.getMetric().getSource().getRepoWarchersCount());
        return docInfo;
    }

    private GitHubMetric buildGitHubMetric(JsonNode json) {
        logger.info("Building GitHubMetric: " + json.toString());
        Optional<JsonNode> metric = Optional.ofNullable(json.get("metric"));
        if(metric.isPresent()) {
            Optional<JsonNode> source = Optional.ofNullable(metric.get().get("source"));
            if (source.isPresent()) {
                return GitHubMetric.builder()
                        .metricId(metric.get().get(Constants.OBJ_METRIC_ID) != null ?
                                metric.get().get(Constants.OBJ_METRIC_ID).asText():null)
                        .source(GitHubMetricSource.builder()
                                .repoCollaboratorsUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_COLLABORATORS_URL)))
                                .repoCommitsUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_COMMITS_URL)))
                                .repoContributorsUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_CONTRIBUTORS_URL)))
                                .repoCreated(parseStringToDate(source.get().get(Constants.OBJ_CREATED)))
                                .repoDateFrom(parseStringToDate(source.get().get(Constants.OBJ_DATE_FROM)))
                                .repoDateTo(parseStringToDate(source.get().get(Constants.OBJ_DATE_TO)))
                                .repoDefaultBranch(getStringFromJsonNode(source.get().get(Constants.OBJ_DEFAULT_BRANCH)))
                                .repoDescription(getStringFromJsonNode(source.get().get(Constants.OBJ_DESCRIPTION)))
                                .repoDownloadsUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_DOWNLOADS_URL)))
                                .repoEventsUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_EVENTS_URL)))
                                .repoForks(getLongFromJsonNode(source.get().get(Constants.OBJ_FORKS)))
                                .repoForksCount(getLongFromJsonNode(source.get().get(Constants.OBJ_FORKS_COUNT)))
                                .repoGroupDay(getIntegerFromJsonNode(source.get().get(Constants.OBJ_GROUP_DAY)))
                                .repoGroupMonth(getIntegerFromJsonNode(source.get().get(Constants.OBJ_GROUP_MONTH)))
                                .repoGroupWeek(getIntegerFromJsonNode(source.get().get(Constants.OBJ_GROUP_WEEK)))
                                .repoGroupYear(getIntegerFromJsonNode(source.get().get(Constants.OBJ_GROUP_YEAR)))
                                .repoHasDownloads(getBooleanFromJsonNode(source.get().get(Constants.OBJ_HAS_DOWNLOADS)))
                                .repoHasIssue(getBooleanFromJsonNode(source.get().get(Constants.OBJ_HAS_ISSUES)))
                                .repoHasPages(getBooleanFromJsonNode(source.get().get(Constants.OBJ_HAS_PAGES)))
                                .repoHasProjects(getBooleanFromJsonNode(source.get().get(Constants.OBJ_HAS_PROJECTS)))
                                .repoHasWiki(getBooleanFromJsonNode(source.get().get(Constants.OBJ_HAS_WIKI)))
                                .repoHttpUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_HTTP_URL)))
                                .repoHttpsUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_HTTPS_URL)))
                                .repoId(getStringFromJsonNode(source.get().get(Constants.OBJ_ID)))
                                .repoIssueCommentUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_ISSUE_COMMENT_URL)))
                                .repoIssueEventsUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_ISSUE_EVENTS_URL)))
                                .repoIssuesUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_ISSUES_URL)))
                                .repoKey(getStringFromJsonNode(source.get().get(Constants.OBJ_KEY)))
                                .repoLanguage(getStringFromJsonNode(source.get().get(Constants.OBJ_LANGUAGE)))
                                .repoLicenseKey(getStringFromJsonNode(source.get().get(Constants.OBJ_LICENSE_KEY)))
                                .repoLicenseName(getStringFromJsonNode(source.get().get(Constants.OBJ_LICENSE_NAME)))
                                .repoLicenseNode(getStringFromJsonNode(source.get().get(Constants.OBJ_LICENSE_NODE)))
                                .repoLicenseSPDXId(getStringFromJsonNode(source.get().get(Constants.OBJ_LICENSE_SPDX_ID)))
                                .repoLicenseUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_LICENSE_URL)))
                                .repoMilestonesUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_MILESTONES_URL)))
                                .repoName(getStringFromJsonNode(source.get().get(Constants.OBJ_NAME)))
                                .repoNotificationsUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_NOTIFICATIONS_URL)))
                                .repoOpenIssues(getLongFromJsonNode(source.get().get(Constants.OBJ_OPEN_ISSUES)))
                                .repoOpenIssuesCount(getLongFromJsonNode(source.get().get(Constants.OBJ_OPEN_ISSUES_COUNT)))
                                .repoOwner(getStringFromJsonNode(source.get().get(Constants.OBJ_OWNER)))
                                .repoOwnerId(getStringFromJsonNode(source.get().get(Constants.OBJ_OWNER_ID)))
                                .repoOwnerNodeId(getStringFromJsonNode(source.get().get(Constants.OBJ_OWNER_NODE_ID)))
                                .repoOwnerType(getStringFromJsonNode(source.get().get(Constants.OBJ_OWNER_TYPE)))
                                .repoPullsUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_PULLS_URL)))
                                .repoPushed(parseStringToDate(source.get().get(Constants.OBJ_PUSHED)))
                                .repoSize(getLongFromJsonNode(source.get().get(Constants.OBJ_SIZE)))
                                .repoStargazersCount(getLongFromJsonNode(source.get().get(Constants.OBJ_STARGAZERS_COUNT)))
                                .repoStargazersUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_STARGAZERS_URL)))
                                .repoStatusesUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_STATUSES_URL)))
                                .repoSuscribersCount(getLongFromJsonNode(source.get().get(Constants.OBJ_SUSCRIBERS_COUNT)))
                                .repoSuscribersUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_SUSCRIBERS_URL)))
                                .repoToken(getStringFromJsonNode(source.get().get(Constants.OBJ_TOKEN)))
                                .repoTreesUrl(getStringFromJsonNode(source.get().get(Constants.OBJ_TREES_URL)))
                                .repoUpdated(parseStringToDate(source.get().get(Constants.OBJ_UPDATED)))
                                .repoVisibility(getStringFromJsonNode(source.get().get(Constants.OBJ_VISIBILITY)))
                                .repoWatchers(getLongFromJsonNode(source.get().get(Constants.OBJ_WATCHERS)))
                                .repoWarchersCount(getLongFromJsonNode(source.get().get(Constants.OBJ_WATCHERS_COUNT)))
                                .build())
                        .build();
            } else {
                return GitHubMetric.builder()
                        .metricId(metric.get().get("metricId").asText())
                        .build();
            }
        } else {
            return null;
        }
    }

    private JsonNode createQueryByField(String key, String value) {
        ObjectNode filterBy = objectMapper.createObjectNode();
        ObjectNode filter = objectMapper.createObjectNode();
        switch (key){
            case Constants.OBJ_OWNER:
                filter.put(Constants.REPO_OWNER, value);
                break;
            case Constants.OBJ_VISIBILITY:
                filter.put(Constants.REPO_VISIBILITY, value);
                break;
            default:
                filter.put(Constants.REPO_NAME, value);
                break;
        }
        return filterBy.putPOJO(Constants.MATCH_PHRASE_PREFIX, filter);
    }

    private Date parseStringToDate(JsonNode data) {
        Date result = new Date();
        try {
            if(data != null && !data.asText().isBlank()) {
                result = sdf.parse(data.asText());
            }
        } catch (ParseException ex) {
            logger.error("Información invalida para convertir en Date");
        }
        return result;
    }

    private String getStringFromJsonNode(JsonNode node) {
        Optional<JsonNode> jNode = Optional.ofNullable(node);
        if(jNode.isPresent()) {
            return jNode.get().asText();
        } else {
            return "";
        }
    }

    private Long getLongFromJsonNode(JsonNode node) {
        Optional<JsonNode> jNode = Optional.ofNullable(node);
        if(jNode.isPresent()) {
            return jNode.get().asLong();
        } else {
            return 0l;
        }
    }

    private Integer getIntegerFromJsonNode(JsonNode node) {
        Optional<JsonNode> jNode = Optional.ofNullable(node);
        if(jNode.isPresent()) {
            return jNode.get().asInt();
        } else {
            return 0;
        }
    }

    private Boolean getBooleanFromJsonNode(JsonNode node) {
        Optional<JsonNode> jNode = Optional.ofNullable(node);
        if(jNode.isPresent()) {
            return jNode.get().asBoolean();
        } else {
            return Boolean.FALSE;
        }
    }

    public MeetChannelEntityDTO getOpenSearchCreateRequestMeetChannelMono(OpenSearchRequestMeetChannel request) {
        //get timestamp with format: yyyy-MM-dd'T'HH:mm:ss
        String timestamp = sdf.format(new Date());

        MeetChannelEntityDTO meetChannelEntityDTO = MeetChannelEntityDTO.builder()
                .timestamp(timestamp)
                .id(request.getMetricMeetChannel().getId())
                .nameEvent(request.getMetricMeetChannel().getEventname())
                .name(request.getMetricMeetChannel().getName())
                .about(request.getMetricMeetChannel().getAbout())
                .location(request.getMetricMeetChannel().getLocation())
                .members(request.getMetricMeetChannel().getMembers())
                .build();

        return meetChannelEntityDTO;
    }

    public MeetChannelEventEntityDTO getOpenSearchCreateRequestMeetChannelEventMono(OpenSearchRequestMeetChannelEvent request) {
        //get timestamp with format: yyyy-MM-dd'T'HH:mm:ss
        String timestamp = sdf.format(new Date());

        MeetChannelEventEntityDTO meetChannelEventEntityDTO = MeetChannelEventEntityDTO.builder()
                .timestamp(timestamp)
                .id(request.getMeetChannelEventEntity().getId())
                .name(request.getMeetChannelEventEntity().getName())
                .meetschannelid(request.getMeetChannelEventEntity().getMeetchannelid())
                .meetschannelname(request.getMeetChannelEventEntity().getMeetschannelname())
                .detail(request.getMeetChannelEventEntity().getDetail())
                .date(request.getMeetChannelEventEntity().getDate())
                .urlyoutube(request.getMeetChannelEventEntity().getUrlyoutube())
                .urltwitch(request.getMeetChannelEventEntity().getUrltwitch())
                .assistants(request.getMeetChannelEventEntity().getAssistants())
                .state(request.getMeetChannelEventEntity().getState())
                .build();

        return meetChannelEventEntityDTO;
    }

    public MetricEntityDTO getOpenSearchCreateRequestMetric(OpenSearchRequestMetric request) {
        //get timestamp with format: yyyy-MM-dd'T'HH:mm:ss
        String timestamp = sdf.format(new Date());

        MetricEntityDTO metricEntityDTO = MetricEntityDTO.builder()
                .timestamp(timestamp)
                .id(request.getMetricEntity().getId())
                .name(request.getMetricEntity().getName())
                .metricarea(request.getMetricEntity().getArea())
                .year(request.getMetricEntity().getYear())
                .goal(request.getMetricEntity().getGoal())
                .compliance(request.getMetricEntity().getCompliance())
                .measure(request.getMetricEntity().getMeasure())
                .metricpercentage(request.getMetricEntity().getCompliance() / request.getMetricEntity().getGoal() * 100)
                .build();

        return metricEntityDTO;
    }

    public ProjectEntityDTO getOpenSearchCreateRequestProject(OpenSearchRequestProject request) {
        //get timestamp with format: yyyy-MM-dd'T'HH:mm:ss
        String timestamp = sdf.format(new Date());

        ProjectEntityDTO projectEntityDTO = ProjectEntityDTO.builder()
                .timestamp(timestamp)
                .id(request.getProjectEntity().getId())
                .awscode(request.getProjectEntity().getAwscode())
                .nameProjectLicensed(request.getProjectEntity().getNameprojectlicensed())
                .nameProjectOSPO(request.getProjectEntity().getNameprojectospo())
                .personLeadOSPO(request.getProjectEntity().getPersonleadospo())
                .nameEVC(request.getProjectEntity().getNameevc())
                .personLeadEVC(request.getProjectEntity().getPersonleadevc())
                .firstContactDate(request.getProjectEntity().getFirstcontactdate())
                .costLicensedUSD(request.getProjectEntity().getCostlicensedusd())
                .costLicensedCOP(request.getProjectEntity().getCostlicensedcop())
                .costOSPOUSD(request.getProjectEntity().getCostospousd())
                .costOSPOCOP(request.getProjectEntity().getCostospocop())
                .costSaving(request.getProjectEntity().getCostsaving())
                .year(request.getProjectEntity().getYear())
                .migrationStart(request.getProjectEntity().getMigrationstart())
                .migrationEnd(request.getProjectEntity().getMigrationend())
                .projectArchitectureType(request.getProjectEntity().getProjectarchitecturetype())
                .projectType(request.getProjectEntity().getProjecttype())
                .projectImpact(request.getProjectEntity().getProjectimpact())
                .projectEffort(request.getProjectEntity().getProjecteffort())
                .projectPriority(request.getProjectEntity().getProjectpriority())
                .comments(request.getProjectEntity().getComments())
                .projectStatus(request.getProjectEntity().getProjectstatus())
                .evcParticipants(request.getProjectEntity().getEvcParticipants())
                .serviceType(request.getProjectEntity().getServicetype())
                .savingCostUSD(request.getProjectEntity().getSavingcostusd())
                .build();


        return projectEntityDTO;
    }

    public ProjectDetailDTO getOpenSearchCreateRequestProjectDetail(OpenSearchRequestProjectDetail request) {
        //get timestamp with format: yyyy-MM-dd'T'HH:mm:ss
        String timestamp = sdf.format(new Date());

        ProjectDetailDTO projectDetailEntityDTO = ProjectDetailDTO.builder()
                .timestamp(timestamp)
                .id(request.getProjectDetailEntity().getId())
                .costlicensedusd(request.getProjectDetailEntity().getCostlicensedusd())
                .costlicensedcop(request.getProjectDetailEntity().getCostlicensedcop())
                .costospousd(request.getProjectDetailEntity().getCostospousd())
                .costospocop(request.getProjectDetailEntity().getCostospocop())
                .costsaving(request.getProjectDetailEntity().getCostsaving())
                .projectsubtypelicensed(request.getProjectDetailEntity().getProjectsubtypelicensed())
                .projectsubtypeospo(request.getProjectDetailEntity().getProjectsubtypeospo())
                .quantitymigrated(request.getProjectDetailEntity().getQuantitymigrated())
                .quantityobjective(request.getProjectDetailEntity().getQuantityobjective())
                .comments(request.getProjectDetailEntity().getComments())
                .projectstatus(request.getProjectDetailEntity().getProjectstatus())
                .project(request.getProjectDetailEntity().getProject())
                .projectenviroment(request.getProjectDetailEntity().getProjectenviroment())
                .projecttypelicensed(request.getProjectDetailEntity().getProjecttypelicensed())
                .projecttypeospo(request.getProjectDetailEntity().getProjecttypeospo())
                .projectevc(request.getProjectDetailEntity().getEvc())
                .costsavingcop(request.getProjectDetailEntity().getCostsavingcop())
                .build();

        return projectDetailEntityDTO;
    }

}

