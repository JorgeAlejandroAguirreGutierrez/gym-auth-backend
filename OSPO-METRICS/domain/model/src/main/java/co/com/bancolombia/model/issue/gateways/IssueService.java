package co.com.bancolombia.model.issue.gateways;

import co.com.bancolombia.model.issue.Issue;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IssueService {
    Mono<Issue> getByNumber(String owner, String name, String number);
    Flux<Issue> getByRepo(String owner, String name, int perPage);
    Flux<Issue> getByDate(String owner, String name, String since, int perPage);
}
