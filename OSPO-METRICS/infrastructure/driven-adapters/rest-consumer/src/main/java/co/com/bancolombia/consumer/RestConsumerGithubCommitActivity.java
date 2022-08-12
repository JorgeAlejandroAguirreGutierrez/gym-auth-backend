package co.com.bancolombia.consumer;

import co.com.bancolombia.consumer.dto.GithubCommitActivityDTO;
import co.com.bancolombia.consumer.dto.GithubRepoDTO;
import co.com.bancolombia.consumer.utils.GithubMapper;
import co.com.bancolombia.model.commitactivity.CommitActivity;
import co.com.bancolombia.model.commitactivity.gateways.CommitActivityService;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class RestConsumerGithubCommitActivity implements CommitActivityService {
    @Autowired
    @Qualifier("githubClient")
    protected WebClient client;
    @Autowired
    @Qualifier("githubToken")
    protected RestConsumerToken clientToken;
    @Override
    public Flux<CommitActivity> getByRepo(String owner, String name, int perPage) {
        return clientToken.getTokenValue("githubTokenCacheCommitActivity")
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
                                        .path(Enums.GithubUri.GITHUB_STATS_COMMIT_ACTIVITY.value)
                                        .build(repo.getOwnerLogin(), repo.getName()))
                                .headers(head -> head.setBearerAuth(token))
                                .exchangeToFlux(exc -> exc.bodyToFlux(GithubCommitActivityDTO.class))
                                .map(commitActivity -> GithubMapper.mapDataCommitActivity(repo, commitActivity))
                                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                        )
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
}
