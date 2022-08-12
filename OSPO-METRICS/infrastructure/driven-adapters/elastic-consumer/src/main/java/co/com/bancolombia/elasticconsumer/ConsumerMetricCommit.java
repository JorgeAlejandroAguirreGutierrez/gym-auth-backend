package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.elasticconsumer.dto.CommitMetricDTO;
import co.com.bancolombia.model.commit.Commit;
import co.com.bancolombia.model.commit.gateways.CommitMetricService;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.repo.Repo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class ConsumerMetricCommit implements CommitMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricCommit.class);
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Override
    public Mono<Void> putCommitMetric(Repo repo, Commit commit) {
        return client
                .method(HttpMethod.PUT)
                .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                        .build(Enums.GithubIndex.Commits.value,
                                repo.getOwnerId() + "." + repo.getId() + "." + commit.getSha()))
                .bodyValue(getMetricCollaborator(repo, commit))
                .exchange()
                .flatMap(res -> {
                    HttpStatus httpStatus = res.statusCode();
                    if (httpStatus.is2xxSuccessful()) {
                        logger.info("Commit posted successfully");
                    } else {
                        logger.info("Commit posted failed. Status=" + httpStatus);
                    }
                    return res.bodyToMono(String.class);
                })
                .map(this::mapDataResult)
                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                .then();
    }
    private CommitMetricDTO getMetricCollaborator(Repo repo, Commit commit) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return CommitMetricDTO
                .builder()
                .id(repo.getOwnerId() + "." + repo.getId() + "." + commit.getSha())
                .key(dateFormat.format(commit.getDateCommitAuthorCreated()))
                .repoId(String.valueOf(repo.getId()))
                .repoName(repo.getName())
                .repoOwnerId(repo.getOwnerId())
                .repoOwnerLogin(repo.getOwnerLogin())
                .sha(commit.getSha())
                .userCommitAuthorLogin(commit.getUserAuthorLogin())
                .userCommitAuthorName(commit.getUserAuthorName())
                .userCommitAuthorEmail(commit.getUserAuthorEmail())
                .userCommitAuthorLocation(commit.getUserAuthorLocation())
                .dateCommitAuthorCreated(dateFormat.format(commit.getDateCommitAuthorCreated()))
                .userCommitCommitterLogin(commit.getUserCommitterLogin())
                .userCommitCommitterName(commit.getUserCommitterName())
                .userCommitCommitterEmail(commit.getUserCommitterEmail())
                .userCommitCommitterLocation(commit.getUserCommitterLocation())
                .statsTotal(commit.getStatsTotal())
                .statsAdditions(commit.getStatsAdditions())
                .statsDeletions(commit.getStatsDeletions())
                .filesAdded(commit.getFilesAdded())
                .filesModified(commit.getFilesModified())
                .filesRenamed(commit.getFilesRenamed())
                .filesRemoved(commit.getFilesRemoved())
                .build();
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Result Commit {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
