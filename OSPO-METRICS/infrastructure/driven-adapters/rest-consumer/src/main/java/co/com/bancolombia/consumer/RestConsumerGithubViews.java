package co.com.bancolombia.consumer;

import co.com.bancolombia.consumer.dto.GithubRepoDTO;
import co.com.bancolombia.consumer.dto.GithubViewsDTO;
import co.com.bancolombia.consumer.utils.GithubMapper;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.model.views.Views;
import co.com.bancolombia.model.views.gateways.ViewsService;
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
public class RestConsumerGithubViews implements ViewsService {
    @Autowired
    @Qualifier("githubClient")
    protected WebClient client;
    @Autowired
    @Qualifier("githubToken")
    protected RestConsumerToken clientToken;
    @Override
    public Flux<Views> getByRepoOwner(String owner, String type, int perPage) {
        AtomicInteger pageItems = new AtomicInteger(0);
        AtomicInteger pageCurrent = new AtomicInteger(1);

        // TODO: Create a best way to generate queryParams
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("type", type);
        queryParams.add("page", pageCurrent.toString());
        queryParams.add("per_page", String.valueOf(perPage));

        return clientToken.getTokenValue("githubTokenCacheViews")
                .flatMapMany(token -> fetchDataRepo(token, owner, queryParams)
                        .expand(item -> {
                            pageItems.set(pageItems.get() + 1);
                            if (Objects.isNull(item)) {
                                return Mono.empty();
                            }
                            if (pageItems.get() == perPage) {
                                pageItems.set(0);
                                pageCurrent.set(pageCurrent.get() + 1);

                                queryParams.set("page", pageCurrent.toString());

                                return fetchDataRepo(token, owner, queryParams);
                            } else {
                                return Mono.empty();
                            }
                        })
                        .flatMap(repo -> client
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                        .path(Enums.GithubUri.GITHUB_TRAFFIC_VIEWS.value)
                                        .build(repo.getOwnerLogin(), repo.getName()))
                                .headers(head -> head.setBearerAuth(token))
                                .exchangeToFlux(exc -> exc.bodyToFlux(GithubViewsDTO.class))
                                .map(view -> GithubMapper.mapDataViews(repo, view))
                                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                        )
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
    @Override
    public Flux<Views> getByRepoName(String owner, String name, int perPage) {
        return clientToken.getTokenValue("githubTokenCacheViews")
                .flatMapMany(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_REPOS_NAME.value)
                                .build(owner, name))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToMono(exc -> exc.bodyToMono(GithubRepoDTO.class))
                        .map(GithubMapper::mapDataRepo)
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                        .flatMapMany(repo -> client
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                        .path(Enums.GithubUri.GITHUB_TRAFFIC_VIEWS.value)
                                        .build(owner, name))
                                .headers(head -> head.setBearerAuth(token))
                                .exchangeToFlux(exc -> exc.bodyToFlux(GithubViewsDTO.class))
                                .map(view -> GithubMapper.mapDataViews(repo, view)))
                                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
    private Flux<Repo> fetchDataRepo(String token, String owner, MultiValueMap<String, String> queryParams) {
        return client
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(Enums.GithubUri.GITHUB_REPOS_OWNER.value)
                        .queryParams(queryParams)
                        .build(owner))
                .headers(head -> head.setBearerAuth(token))
                .exchangeToFlux(exc -> exc.bodyToFlux(GithubRepoDTO.class))
                .map(GithubMapper::mapDataRepo)
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
}
