package co.com.bancolombia.consumer;

import co.com.bancolombia.consumer.dto.GithubPullDTO;
import co.com.bancolombia.consumer.utils.GithubMapper;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.pull.Pull;
import co.com.bancolombia.model.pull.gateways.PullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RestConsumerGithubPull implements PullService {
    @Autowired
    @Qualifier("githubClient")
    protected WebClient client;
    @Autowired
    @Qualifier("githubToken")
    protected RestConsumerToken clientToken;
    @Override
    public Mono<Pull> getByNumber(String owner, String name, String number) {
        return clientToken.getTokenValue("githubTokenCachePull")
                .flatMap(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_PULLS_NUMBER.value)
                                .build(owner, name, number))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToMono(exc -> exc.bodyToMono(GithubPullDTO.class))
                        .map(GithubMapper::mapDataPull)
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
    @Override
    public Flux<Pull> getByRepo(String owner, String name, int perPage) {
        AtomicInteger pageItems = new AtomicInteger(0);
        AtomicInteger pageCurrent = new AtomicInteger(1);

        // TODO: Create a best way to generate queryParams
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("state", "all");
        queryParams.add("sort", "created");
        queryParams.add("direction", "asc");
        queryParams.add("page", pageCurrent.toString());
        queryParams.add("per_page", String.valueOf(perPage));

        return fetchData(owner, name, queryParams)
                .expand(item -> {
                    pageItems.set(pageItems.get() + 1);
                    if (Objects.isNull(item)) {
                        return Mono.empty();
                    } else {
                        if (pageItems.get() == perPage) {
                            pageItems.set(0);
                            pageCurrent.set(pageCurrent.get() + 1);

                            queryParams.set("page", pageCurrent.toString());

                            return fetchData(owner, name, queryParams);
                        } else {
                            return Mono.empty();
                        }
                    }
                });
    }
    private Flux<Pull> fetchData(String owner, String name, MultiValueMap<String, String> queryParams) {
        return clientToken.getTokenValue("githubTokenCachePull")
                .flatMapMany(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_PULLS.value)
                                .queryParams(queryParams)
                                .build(owner, name))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToFlux(exc -> exc.bodyToFlux(GithubPullDTO.class))
                        .map(GithubMapper::mapDataPull)
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
}
