package co.com.bancolombia.model.issue.gateways;

import co.com.bancolombia.model.issue.Issue;
import co.com.bancolombia.model.repo.Repo;
import reactor.core.publisher.Mono;

public interface IssueMetricService {
    Mono<Void> putIssueMetric(Repo repo, Issue issue);
}
