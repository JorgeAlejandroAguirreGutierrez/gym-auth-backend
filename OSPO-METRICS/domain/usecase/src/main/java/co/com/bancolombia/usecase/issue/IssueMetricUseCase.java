package co.com.bancolombia.usecase.issue;

import co.com.bancolombia.model.issue.Issue;
import co.com.bancolombia.model.issue.gateways.IssueMetricService;
import co.com.bancolombia.model.repo.Repo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class IssueMetricUseCase {
    private final IssueMetricService issueMetricService;
    public Mono<Void> putIssueMetric(Repo repo, Issue issue) {
        return issueMetricService.putIssueMetric(repo, issue);
    }
}
