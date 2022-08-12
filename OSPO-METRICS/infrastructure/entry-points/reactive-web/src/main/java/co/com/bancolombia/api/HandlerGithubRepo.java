package co.com.bancolombia.api;

import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.usecase.repo.RepoEventUseCase;
import co.com.bancolombia.usecase.repo.RepoUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
@RequiredArgsConstructor
public class HandlerGithubRepo {
    private static final Logger logger = LoggerFactory.getLogger(HandlerGithubRepo.class);
    private final RepoUseCase repoUseCase;
    private final RepoEventUseCase repoEventUseCase;
    public Mono<ServerResponse> getByName(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        repoUseCase.getByName(owner, name), Repo.class));
    }
    public Mono<ServerResponse> getByOwner(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        repoUseCase.getByOwner(owner, "all", 100), Repo.class));
    }
    public Mono<ServerResponse> getByOrg(ServerRequest serverRequest) {
        String org = serverRequest.pathVariable("org");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        repoUseCase.getByOrg(org, 100), Repo.class));
    }
    public Mono<ServerResponse> doRegisterEventRepo(ServerRequest serverRequest) {
        String org = serverRequest.pathVariable("org");
        return repoEventUseCase.emitEvents(org, 100)
                .doOnError(e -> ServerResponse.badRequest().build())
                .flatMap(s -> ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }
}