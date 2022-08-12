package co.com.bancolombia.usecase.commitactivity;

import co.com.bancolombia.model.commitactivity.CommitActivity;
import co.com.bancolombia.model.commitactivity.gateways.CommitActivityMetricService;
import co.com.bancolombia.model.repo.Repo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CommitActivityMetricUseCase {
    private final CommitActivityMetricService commitActivityMetricService;
    public Mono<Void> putCommitActivityMetric(Repo repo, CommitActivity commitActivity) {
        return commitActivityMetricService.putCommitActivityMetric(repo, commitActivity);
    }
}
