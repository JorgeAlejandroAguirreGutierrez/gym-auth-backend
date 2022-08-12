package co.com.bancolombia.api;

import co.com.bancolombia.model.issue.Issue;
import co.com.bancolombia.usecase.issue.IssueUseCase;
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
public class HandlerGithubIssue {
    private static final Logger logger = LoggerFactory.getLogger(HandlerGithubIssue.class);
    private final IssueUseCase issueUseCase;
    public Mono<ServerResponse> getByNumber(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        String number = serverRequest.pathVariable("number");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        issueUseCase.getByNumber(owner, name, number), Issue.class));
    }
    public Mono<ServerResponse> getByRepo(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        issueUseCase.getByRepo(owner, name, 100), Issue.class));
    }
    public Mono<ServerResponse> getByDate(ServerRequest serverRequest) {
        String owner = serverRequest.pathVariable("owner");
        String name = serverRequest.pathVariable("name");
        String since = String.valueOf(serverRequest.queryParam("since"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(
                        issueUseCase.getByDate(owner, name, since, 100), Issue.class));
    }
}
