package co.com.bancolombia.api;

import co.com.bancolombia.model.pull.Pull;
import co.com.bancolombia.usecase.pull.PullUseCase;
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
public class HandlerGithubPull {
    private static final Logger logger = LoggerFactory.getLogger(HandlerGithubPull.class);
    private final PullUseCase pullUseCase;
    public Mono<ServerResponse> getByNumber(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        String number = serverRequest.pathVariable("number");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        pullUseCase.getByNumber(owner, name, number), Pull.class));
    }
    public Mono<ServerResponse> getByRepo(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        pullUseCase.getByRepo(owner, name, 100), Pull.class));
    }
}
