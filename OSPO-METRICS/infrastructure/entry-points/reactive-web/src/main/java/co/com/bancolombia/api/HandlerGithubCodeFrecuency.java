package co.com.bancolombia.api;

import co.com.bancolombia.model.codefrecuency.CodeFrecuency;
import co.com.bancolombia.usecase.codefrecuency.CodeFrecuencyUseCase;
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
public class HandlerGithubCodeFrecuency {
    private static final Logger logger = LoggerFactory.getLogger(HandlerGithubCodeFrecuency.class);
    private final CodeFrecuencyUseCase codeFrecuencyUseCase;
    public Mono<ServerResponse> getByRepo(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        codeFrecuencyUseCase.getByRepoName(owner, name, 100), CodeFrecuency.class));
    }
}
