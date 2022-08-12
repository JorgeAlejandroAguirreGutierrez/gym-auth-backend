package co.com.bancolombia.consumer;

import co.com.bancolombia.consumer.dto.GithubRepoDTO;
import co.com.bancolombia.consumer.dto.GithubRepoSearchDTO;
import co.com.bancolombia.consumer.utils.GithubMapper;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.model.repo.gateways.RepoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
public class RestConsumerGithubRepo implements RepoService {
    private static final Logger logger = LoggerFactory.getLogger(RestConsumerGithubRepo.class);
    @Autowired
    @Qualifier("githubClient")
    protected WebClient client;
    @Autowired
    @Qualifier("githubToken")
    protected RestConsumerToken clientToken;
    @Override
    public Mono<Repo> getByName(String owner, String repo) {
        return clientToken.getTokenValue("githubTokenCacheRepo")
                .flatMap(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_REPOS_NAME.value)
                                .build(owner, repo))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToMono(exc -> exc.bodyToMono(GithubRepoDTO.class))
                        .map(GithubMapper::mapDataRepo)
                );
    }
    @Override
    public Flux<Repo> getByOwner(String owner, String type, int perPage) {
        AtomicInteger pageItems = new AtomicInteger(0);
        AtomicInteger pageCurrent = new AtomicInteger(1);

        // TODO: Create a best way to generate queryParams
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("type", type);
        queryParams.add("page", pageCurrent.toString());
        queryParams.add("per_page", String.valueOf(perPage));

        return fetchData(owner, queryParams)
                .expand(item -> {
                    pageItems.set(pageItems.get() + 1);
                    if (Objects.isNull(item)) {
                        return Mono.empty();
                    }
                    if (pageItems.get() == perPage) {
                        pageItems.set(0);
                        pageCurrent.set(pageCurrent.get() + 1);

                        queryParams.set("page", pageCurrent.toString());

                        return fetchData(owner, queryParams);
                    } else {
                        return Mono.empty();
                    }
                });
    }

    @Override
    public Flux<Repo> getByOrg(String org, int perPage) {
        AtomicInteger pageItems = new AtomicInteger(0);
        AtomicInteger pageCurrent = new AtomicInteger(1);

        // TODO: Create a best way to generate queryParams
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.add("q", "org:" + org);
        queryParams.add("page", pageCurrent.toString());
        queryParams.add("per_page", String.valueOf(perPage));

        return fetchDataOrg(queryParams)
                .expand(item -> {
                    pageItems.set(pageItems.get() + 1);
                    if (Objects.isNull(item)) {
                        return Mono.empty();
                    }
                    if (pageItems.get() == perPage) {
                        pageItems.set(0);
                        pageCurrent.set(pageCurrent.get() + 1);

                        queryParams.set("page", pageCurrent.toString());

                        return fetchDataOrg(queryParams);
                    } else {
                        return Mono.empty();
                    }
                });
    }
    private Flux<Repo> fetchData(String owner, MultiValueMap<String, String> queryParams) {
        return clientToken.getTokenValue("githubTokenCacheRepo")
                .flatMapMany(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_REPOS_OWNER.value)
                                .queryParams(queryParams)
                                .build(owner))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToFlux(exc -> exc.bodyToFlux(GithubRepoDTO.class))
                        .map(GithubMapper::mapDataRepo)
                );
    }
    private Flux<Repo> fetchDataOrg(MultiValueMap<String, String> queryParams) {
        return clientToken.getTokenValue("githubTokenCacheRepo")
                .flatMapMany(token ->  client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_REPOS_SEARCH.value)
                                .queryParams(queryParams)
                                .build())
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToMono(exc -> exc.bodyToMono(GithubRepoSearchDTO.class))
                        .flatMapMany(map -> Flux.fromArray(GithubMapper.mapDataRepoSearch(map)))
                );
    }
}
