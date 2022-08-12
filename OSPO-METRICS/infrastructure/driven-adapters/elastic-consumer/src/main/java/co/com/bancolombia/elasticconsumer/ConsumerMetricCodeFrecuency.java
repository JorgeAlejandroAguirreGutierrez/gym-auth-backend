package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.elasticconsumer.dto.CodeFrecuencyMetricDTO;
import co.com.bancolombia.model.codefrecuency.CodeFrecuency;
import co.com.bancolombia.model.codefrecuency.CodeFrecuencyData;
import co.com.bancolombia.model.codefrecuency.gateways.CodeFrecuencyMetricService;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.repo.Repo;
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
public class ConsumerMetricCodeFrecuency implements CodeFrecuencyMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricCodeFrecuency.class);
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Override
    public Mono<Void> putMetric(CodeFrecuency codeFrecuency) {
        return Mono.just(codeFrecuency).flatMap(code ->
                Flux.fromIterable(code.getData())
                        .flatMap(codeData -> client
                                .method(HttpMethod.PUT)
                                .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                                        .build(Enums.GithubIndex.TrafficCodeFrequency.value, codeData.getId()))
                                .bodyValue(getMetricCodeFrecuency(code.getRepo(), codeData))
                                .exchange()
                                .flatMap(res -> {
                                    HttpStatus httpStatus = res.statusCode();
                                    if (httpStatus.is2xxSuccessful()) {
                                        logger.info("CodeFrecuency posted successfully");
                                    } else {
                                        logger.info("CodeFrecuency posted failed. Status=" + httpStatus);
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
    private CodeFrecuencyMetricDTO getMetricCodeFrecuency(Repo repo, CodeFrecuencyData codeFrecuencyData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return CodeFrecuencyMetricDTO
                .builder()
                .id(codeFrecuencyData.getId())
                .key(codeFrecuencyData.getKey())
                .repoId(String.valueOf(repo.getId()))
                .repoName(repo.getName())
                .repoOwnerId(repo.getOwnerId())
                .repoOwnerLogin(repo.getOwnerLogin())
                .dateWeek(dateFormat.format(codeFrecuencyData.getWeek()))
                .dateFrom(dateFormat.format(codeFrecuencyData.getDateFrom()))
                .dateTo(dateFormat.format(codeFrecuencyData.getDateTo()))
                .additions(codeFrecuencyData.getAdditions())
                .deletions(codeFrecuencyData.getDeletions())
                .build();
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Result CodeFrecuency {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
