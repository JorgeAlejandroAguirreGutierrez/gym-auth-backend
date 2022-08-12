package co.com.bancolombia.model.commit.gateways;

import co.com.bancolombia.model.commit.Commit;
import co.com.bancolombia.model.repo.Repo;
import reactor.core.publisher.Mono;

public interface CommitMetricService {
    Mono<Void> putCommitMetric(Repo repo, Commit commit);
}
