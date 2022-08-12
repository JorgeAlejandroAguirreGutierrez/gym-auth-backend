package co.com.bancolombia.consumer;

import co.com.bancolombia.consumer.dto.GithubCommitDTO;
import co.com.bancolombia.consumer.utils.GithubMapper;
import co.com.bancolombia.model.commit.Commit;
import co.com.bancolombia.model.commit.gateways.CommitService;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
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
public class RestConsumerGithubCommit implements CommitService {
    @Autowired
    @Qualifier("githubClient")
    protected WebClient client;
    @Autowired
    @Qualifier("githubToken")
    protected RestConsumerToken clientToken;
    @Override
    public Mono<Commit> getBySha(String owner, String name, String sha) {
        return clientToken.getTokenValue("githubTokenCacheCommit")
                .flatMap(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_COMMITS_SHA.value)
                                .build(owner, name, sha))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToMono(exc -> exc.bodyToMono(GithubCommitDTO.class))
                        .map(GithubMapper::mapDataCommitSHA)
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
    @Override
    public Flux<Commit> getByRepo(String owner, String name, int perPage) {
        AtomicInteger pageItems = new AtomicInteger(0);
        AtomicInteger pageCurrent = new AtomicInteger(1);

        // TODO: Create a best way to generate queryParams
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
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
                })
                .flatMap(commit -> this.fetchDataSHA(owner, name, commit));
    }
    @Override
    public Flux<Commit> getByDate(String owner, String name, String since, int perPage) {
        AtomicInteger pageItems = new AtomicInteger(0);
        AtomicInteger pageCurrent = new AtomicInteger(1);

        // TODO: Create a best way to generate queryParams
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("since", since);
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
    private Flux<Commit> fetchData(String owner, String name, MultiValueMap<String, String> queryParams) {
        return clientToken.getTokenValue("githubTokenCacheCommit")
                .flatMapMany(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_COMMITS.value)
                                .queryParams(queryParams)
                                .build(owner, name))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToFlux(exc -> exc.bodyToFlux(GithubCommitDTO.class))
                        .map(GithubMapper::mapDataCommit)
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
    private Mono<Commit> fetchDataSHA(String owner, String name, Commit commit) {
        return clientToken.getTokenValue("githubTokenCacheCommit")
                .flatMap(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_COMMITS_SHA.value)
                                .build(owner, name, commit.getSha()))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToMono(res -> res.bodyToMono(GithubCommitDTO.class))
                        .map(GithubMapper::mapDataCommitSHA)
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
}
