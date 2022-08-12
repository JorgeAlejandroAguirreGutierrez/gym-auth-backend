package co.com.bancolombia.consumer;

import co.com.bancolombia.consumer.dto.GithubContributorDTO;
import co.com.bancolombia.consumer.dto.GithubUserDTO;
import co.com.bancolombia.consumer.utils.GithubMapper;
import co.com.bancolombia.model.contributor.Contributor;
import co.com.bancolombia.model.contributor.gateways.ContributorService;
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
public class RestConsumerGithubContributor implements ContributorService {
    @Autowired
    @Qualifier("githubClient")
    protected WebClient client;
    @Autowired
    @Qualifier("githubToken")
    protected RestConsumerToken clientToken;
    @Override
    public Flux<Contributor> getByRepo(String owner, String name, int perPage) {
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
                .flatMap(this::fetchDataUser);
    }
    private Flux<Contributor> fetchData(String owner, String name, MultiValueMap<String, String> queryParams) {
        return clientToken.getTokenValue("githubTokenCacheContributor")
                .flatMapMany(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_CONTRIBUTORS.value)
                                .queryParams(queryParams)
                                .build(owner, name))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToFlux(exc -> exc.bodyToFlux(GithubContributorDTO.class))
                        .map(GithubMapper::mapDataContributor)
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }

    private Mono<Contributor> fetchDataUser(Contributor contributor) {
        return clientToken.getTokenValue("githubTokenCacheContributor")
                .flatMap(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_USERS.value)
                                .build(contributor.getUserLogin()))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToMono(exc -> exc.bodyToMono(GithubUserDTO.class))
                        .map(user -> GithubMapper.mapDataContributorUser(user, contributor))
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
}
