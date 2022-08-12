package co.com.bancolombia.elasticconsumer;


import co.com.bancolombia.elasticconsumer.dto.IssueMetricDTO;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.issue.Issue;
import co.com.bancolombia.model.issue.gateways.IssueMetricService;
import co.com.bancolombia.model.repo.Repo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsumerMetricIssue implements IssueMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricIssue.class);
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public Mono<Void> putIssueMetric(Repo repo, Issue issue) {
        return client
                .method(HttpMethod.PUT)
                .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                        .build(Enums.GithubIndex.Issues.value,
                                repo.getOwnerId() + "." + repo.getId() + "." + issue.getId()))
                .bodyValue(getMetricIssue(repo, issue))
                .exchangeToMono(exc -> exc.bodyToMono(String.class))
                .map(this::mapDataResult)
                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                .then();
    }
    private IssueMetricDTO getMetricIssue(Repo repo, Issue issue) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return IssueMetricDTO
                .builder()
                .id(repo.getOwnerId() + "." + repo.getId() + "." + issue.getId())
                .key(dateFormat.format(issue.getDateCreated()))
                .repoId(String.valueOf(repo.getId()))
                .repoName(repo.getName())
                .repoOwnerId(repo.getOwnerId())
                .issueId(issue.getId())
                .issueNumber(issue.getNumber())
                .issueTitle(issue.getTitle())
                .issueState(issue.getState())
                .issueUserId(issue.getUserId())
                .issueUserLogin(issue.getUserLogin())
                .issueUserName(issue.getUserName())
                .issueUserEmail(issue.getUserEmail())
                .issueUserLocation(issue.getUserLocation())
                .issueDateCreated(dateFormat.format(issue.getDateCreated()))
                .issueDateUpdated(dateFormat.format(issue.getDateUpdated()))
                .issueDateClosed(Objects.nonNull(issue.getDateClosed()) ? dateFormat.format(issue.getDateClosed()) : null)
                .issueDaysOpen(issue.getDaysOpen())
                .issueDaysClose(issue.getDaysClosed())
                .build();
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Result Issue {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
