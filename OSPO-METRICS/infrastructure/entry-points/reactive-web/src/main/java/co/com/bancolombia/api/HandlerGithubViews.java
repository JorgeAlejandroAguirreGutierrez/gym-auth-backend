package co.com.bancolombia.api;

import co.com.bancolombia.model.views.Views;
import co.com.bancolombia.usecase.views.ViewsUseCase;
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
public class HandlerGithubViews {
    private static final Logger logger = LoggerFactory.getLogger(HandlerGithubViews.class);
    private final ViewsUseCase viewsUseCase;
    public Mono<ServerResponse> getByRepo(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        viewsUseCase.getByRepoName(owner, name, 100), Views.class));
    }
}
