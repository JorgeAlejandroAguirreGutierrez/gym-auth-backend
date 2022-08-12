package co.com.bancolombia.consumer;

import co.com.bancolombia.consumer.dto.GithubUserDTO;
import co.com.bancolombia.consumer.utils.GithubMapper;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RestConsumerGithubUser implements UserService {
    @Autowired
    @Qualifier("githubClient")
    protected WebClient client;
    @Autowired
    @Qualifier("githubToken")
    protected RestConsumerToken clientToken;
    @Override
    public Mono<User> getByLogin(String login) {
        return fetchData(login);
    }
    private Mono<User> fetchData(String login) {
        return clientToken.getTokenValue("githubTokenCacheUser")
                .flatMap(token -> client
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path(Enums.GithubUri.GITHUB_USERS.value)
                                .build(login))
                        .headers(head -> head.setBearerAuth(token))
                        .exchangeToMono(exc -> exc.bodyToMono(GithubUserDTO.class))
                        .map(GithubMapper::mapDataUser)
                        .onErrorMap((ex) -> new Failure(ex.getMessage()))
                )
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
}
