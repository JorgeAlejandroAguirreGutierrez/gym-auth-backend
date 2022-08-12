package co.com.bancolombia.usecase.commit;

import co.com.bancolombia.model.commit.Commit;
import co.com.bancolombia.model.commit.gateways.CommitMetricService;
import co.com.bancolombia.model.repo.Repo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CommitMetricUseCase {
    private final CommitMetricService commitMetricService;
    public Mono<Void> putCommitMetric(Repo repo, Commit commit) {
        return commitMetricService.putCommitMetric(repo, commit);
    }
}
