package co.com.bancolombia.usecase.issue;

import co.com.bancolombia.model.issue.Issue;
import co.com.bancolombia.model.issue.gateways.IssueService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class IssueUseCase {
    private final IssueService issueService;
    public Mono<Issue> getByNumber(String owner, String name, String number) {
        return issueService.getByNumber(owner, name, number);
    }
    public Flux<Issue> getByRepo(String owner, String name, int perPage) {
        return issueService.getByRepo(owner, name, perPage);
    }
    public Flux<Issue> getByDate(String owner, String name, String since, int perPage) {
        return issueService.getByDate(owner, name, since, perPage);
    }
}
