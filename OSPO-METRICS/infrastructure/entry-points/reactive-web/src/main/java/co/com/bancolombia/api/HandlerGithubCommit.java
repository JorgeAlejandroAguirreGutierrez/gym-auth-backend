package co.com.bancolombia.api;

import co.com.bancolombia.model.commit.Commit;
import co.com.bancolombia.usecase.commit.CommitUseCase;
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
public class HandlerGithubCommit {
    private static final Logger logger = LoggerFactory.getLogger(HandlerGithubCommit.class);
    private final CommitUseCase commitUseCase;
    public Mono<ServerResponse> getBySha(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        String sha = serverRequest.pathVariable("sha");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        commitUseCase.getBySha(owner, name, sha), Commit.class));
    }
    public Mono<ServerResponse> getByRepo(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        commitUseCase.getByRepo(owner, name, 100), Commit.class));
    }
    public Mono<ServerResponse> getByDate(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        String since = String.valueOf(serverRequest.queryParam("since"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        commitUseCase.getByDate(owner, name, since, 100), Commit.class));
    }
}
