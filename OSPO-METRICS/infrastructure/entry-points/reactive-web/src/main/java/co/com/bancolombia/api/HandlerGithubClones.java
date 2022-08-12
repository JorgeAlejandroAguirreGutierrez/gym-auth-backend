package co.com.bancolombia.api;

import co.com.bancolombia.model.clones.Clones;
import co.com.bancolombia.usecase.clones.ClonesUseCase;
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
public class HandlerGithubClones {
    private static final Logger logger = LoggerFactory.getLogger(HandlerGithubClones.class);
    private final ClonesUseCase clonesUseCase;
    public Mono<ServerResponse> getByRepoOwner(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        clonesUseCase.getByRepoOwner(owner,  "all", 100), Clones.class));
    }
    public Mono<ServerResponse> getByRepoName(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        clonesUseCase.getByRepoName(owner, name, 100), Clones.class));
    }
}
