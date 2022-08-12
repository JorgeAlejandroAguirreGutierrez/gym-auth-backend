package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.elasticconsumer.dto.CommitActivityMetricDTO;
import co.com.bancolombia.model.contributor.Contributor;
import co.com.bancolombia.model.contributor.gateways.ContributorMetricService;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.pull.Pull;
import co.com.bancolombia.model.pull.gateways.PullMetricService;
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
public class ConsumerMetricPull implements PullMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricPull.class);
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Override
    public Mono<Void> putPullMetric(Repo repo, Pull pull) {
        return client
                .method(HttpMethod.PUT)
                .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                        .build(Enums.GithubIndex.Pulls.value,
                                repo.getOwnerId() + "." + repo.getId() + "." + pull.getId()))
                .bodyValue(getMetricPull(repo, pull))
                .exchange()
                .flatMap(res -> {
                    HttpStatus httpStatus = res.statusCode();
                    if (httpStatus.is2xxSuccessful()) {
                        logger.info("Pull posted successfully");
                    } else {
                        logger.info("Pull posted failed. Status=" + httpStatus);
                    }
                    return res.bodyToMono(String.class);
                })
                .map(this::mapDataResult)
                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                .then();
    }
    private CommitActivityMetricDTO getMetricPull(Repo repo, Pull pull) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return CommitActivityMetricDTO
                .builder()
                .id(repo.getOwnerId() + "." + repo.getId() + "." + pull.getId())
                .key(dateFormat.format(pull.getDateCreated()))
                .repoId(String.valueOf(repo.getId()))
                .repoName(repo.getName())
                .repoOwnerId(repo.getOwnerId())
                .repoOwnerLogin(repo.getOwnerLogin())
                .build();
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Result Pull {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
