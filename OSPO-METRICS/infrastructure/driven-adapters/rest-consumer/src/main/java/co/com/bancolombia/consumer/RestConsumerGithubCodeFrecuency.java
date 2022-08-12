package co.com.bancolombia.consumer;

import co.com.bancolombia.consumer.dto.GithubRepoDTO;
import co.com.bancolombia.consumer.utils.GithubMapper;
import co.com.bancolombia.model.codefrecuency.CodeFrecuency;
import co.com.bancolombia.model.codefrecuency.gateways.CodeFrecuencyService;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class RestConsumerGithubCodeFrecuency implements CodeFrecuencyService {
    @Autowired
    @Qualifier("githubClient")
    protected WebClient client;
    @Autowired
    @Qualifier("githubToken")
    protected RestConsumerToken clientToken;
    @Override
    public Flux<CodeFrecuency> getByRepoName(String owner, String name, int perPage) {
        return clientToken.getTokenValue("githubTokenCacheCodeFrecuency")
                .flatMapMany(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_REPOS_NAME.value)
                                .build(owner, name))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToMono(res -> res.bodyToMono(GithubRepoDTO.class))
                        .map(GithubMapper::mapDataRepo)
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                        .flatMapMany(repo -> client
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                        .path(Enums.GithubUri.GITHUB_STATS_CODE_FRECUENCY.value)
                                        .build(repo.getOwnerLogin(), repo.getName()))
                                .headers(head -> head.setBearerAuth(token))
                                .exchangeToFlux(exc -> exc.bodyToFlux(String.class))
                                .map(codeFreq -> GithubMapper.mapDataCodeFrecuency(repo, codeFreq))
                                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                        )
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
}
