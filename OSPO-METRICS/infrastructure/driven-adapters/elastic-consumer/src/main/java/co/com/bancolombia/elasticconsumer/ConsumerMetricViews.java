package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.elasticconsumer.dto.ViewsMetricDTO;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.model.views.Views;
import co.com.bancolombia.model.views.ViewsData;
import co.com.bancolombia.model.views.gateways.ViewsMetricService;
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
public class ConsumerMetricViews implements ViewsMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricViews.class);
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Override
    public Mono<Void> putViewsMetric(Views views) {
        return Mono.just(views).flatMap(view ->
            Flux.fromIterable(view.getData())
                    .flatMap(viewData -> client
                            .method(HttpMethod.PUT)
                            .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                                .build(Enums.GithubIndex.TrafficViews.value, viewData.getId()))
                            .bodyValue(getMetricViews(view.getRepo(), viewData))
                                .exchange()
                                .flatMap(res -> {
                                    HttpStatus httpStatus = res.statusCode();
                                    if (httpStatus.is2xxSuccessful()) {
                                        logger.info("Views posted successfully");
                                    } else {
                                        logger.info("Views posted failed. Status=" + httpStatus);
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
    private ViewsMetricDTO getMetricViews(Repo repo, ViewsData viewsData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return ViewsMetricDTO
                .builder()
                .id(viewsData.getId())
                .key(viewsData.getKey())
                .repoId(String.valueOf(repo.getId()))
                .repoName(repo.getName())
                .repoOwnerId(repo.getOwnerId())
                .repoOwnerLogin(repo.getOwnerLogin())
                .viewsCount(viewsData.getCount())
                .viewsUniques(viewsData.getUniques())
                .dateWeek(dateFormat.format(viewsData.getDate()))
                .dateFrom(dateFormat.format(viewsData.getDateFrom()))
                .dateTo(dateFormat.format(viewsData.getDateTo()))
                .build();
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Result Views {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
