package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.elasticconsumer.dto.CommitActivityMetricDTO;
import co.com.bancolombia.model.commitactivity.CommitActivity;
import co.com.bancolombia.model.commitactivity.gateways.CommitActivityMetricService;
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
public class ConsumerMetricCommitActivity implements CommitActivityMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricCommitActivity.class);
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Override
    public Mono<Void> putCommitActivityMetric(Repo repo, CommitActivity commitActivity) {
        return client
                .method(HttpMethod.PUT)
                .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                        .build(Enums.GithubIndex.CommitsActivity.value,
                                repo.getOwnerId() + "." + repo.getId() + "." + commitActivity.getId()))
                .bodyValue(getMetricCollaborator(repo, commitActivity))
                .exchange()
                .flatMap(res -> {
                    HttpStatus httpStatus = res.statusCode();
                    if (httpStatus.is2xxSuccessful()) {
                        logger.info("CommitActivity posted successfully");
                    } else {
                        logger.info("CommitActivity posted failed. Status=" + httpStatus);
                    }
                    return res.bodyToMono(String.class);
                })
                .map(this::mapDataResult)
                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                .then();
    }
    private CommitActivityMetricDTO getMetricCollaborator(Repo repo, CommitActivity commitActivity) {
        return CommitActivityMetricDTO
                .builder()
                .id(commitActivity.getId())
                .key(commitActivity.getKey())
                .repoId(String.valueOf(repo.getId()))
                .repoName(repo.getName())
                .repoOwnerId(repo.getOwnerId())
                .repoOwnerLogin(repo.getOwnerLogin())
                .week(commitActivity.getWeek())
                .dateFrom(commitActivity.getDateFrom())
                .dateTo(commitActivity.getDateTo())
                .totalSunday(commitActivity.getTotalSunday())
                .totalMonday(commitActivity.getTotalMonday())
                .totalTuesday(commitActivity.getTotalTuesday())
                .totalWednesday(commitActivity.getTotalWednesday())
                .totalThursday(commitActivity.getTotalThursday())
                .totalFriday(commitActivity.getTotalFriday())
                .totalSaturday(commitActivity.getTotalSaturday())
                .total(commitActivity.getTotal())
                .build();
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Result CommitActivity {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
