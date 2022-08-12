package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.elasticconsumer.dto.ClonesMetricDTO;
import co.com.bancolombia.model.clones.Clones;
import co.com.bancolombia.model.clones.ClonesData;
import co.com.bancolombia.model.clones.gateways.ClonesMetricService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class ConsumerMetricClones implements ClonesMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricClones.class);
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Override
    public Mono<Void> putMetric(Clones clones) {
        return Mono.just(clones).flatMap(clone ->
                Flux.fromIterable(clone.getData())
                        .flatMap(cloneData -> client
                                .method(HttpMethod.PUT)
                                .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                                        .build(Enums.GithubIndex.TrafficClones.value, cloneData.getId()))
                                .bodyValue(getMetricClones(clone.getRepo(), cloneData))
                                .exchange()
                                .flatMap(res -> {
                                    HttpStatus httpStatus = res.statusCode();
                                    if (httpStatus.is2xxSuccessful()) {
                                        logger.info("Clones posted successfully");
                                    } else {
                                        logger.info("Clones posted failed. Status=" + httpStatus);
                                    }
                                    return res.bodyToMono(String.class);
                                })
                                .map(this::mapDataResult)
                                .onErrorMap((ex) -> new Failure(ex.getMessage())))
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                        .collectList()
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                .then();
    }
    private ClonesMetricDTO getMetricClones(Repo repo, ClonesData clonesData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return ClonesMetricDTO
                .builder()
                .id(clonesData.getId())
                .key(clonesData.getKey())
                .repoId(String.valueOf(repo.getId()))
                .repoName(repo.getName())
                .repoOwnerId(repo.getOwnerId())
                .repoOwnerLogin(repo.getOwnerLogin())
                .clonesCount(clonesData.getCount())
                .clonesUniques(clonesData.getUniques())
                .dateWeek(dateFormat.format(clonesData.getDate()))
                .dateFrom(dateFormat.format(clonesData.getDateFrom()))
                .dateTo(dateFormat.format(clonesData.getDateTo()))
                .build();
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Result Clones {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
