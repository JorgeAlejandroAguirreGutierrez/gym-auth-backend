package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.elasticconsumer.dto.CommitActivityMetricDTO;
import co.com.bancolombia.model.contributor.Contributor;
import co.com.bancolombia.model.contributor.gateways.ContributorMetricService;
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

@Service
@RequiredArgsConstructor
public class ConsumerMetricContributor implements ContributorMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricContributor.class);
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Override
    public Mono<Void> putContributorMetric(Repo repo, Contributor contributor) {
        return client
                .method(HttpMethod.PUT)
                .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                        .build(Enums.GithubIndex.Contributors.value,
                                repo.getOwnerId() + "." + repo.getId() + "." + contributor.getUserId()))
                .bodyValue(getMetricCollaborator(repo, contributor))
                .exchange()
                .flatMap(res -> {
                    HttpStatus httpStatus = res.statusCode();
                    if (httpStatus.is2xxSuccessful()) {
                        logger.info("Contributor posted successfully");
                    } else {
                        logger.info("Contributor posted failed. Status=" + httpStatus);
                    }
                    return res.bodyToMono(String.class);
                })
                .map(this::mapDataResult)
                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                .then();
    }
    private CommitActivityMetricDTO getMetricCollaborator(Repo repo, Contributor contributor) {
        return CommitActivityMetricDTO
                .builder()
                .id(repo.getOwnerId() + "." + repo.getId() + "." + contributor.getUserId())
                .key("")
                .repoId(String.valueOf(repo.getId()))
                .repoName(repo.getName())
                .repoOwnerId(repo.getOwnerId())
                .repoOwnerLogin(repo.getOwnerLogin())
                .build();
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Result Contributor {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
