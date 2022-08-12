package co.com.bancolombia.model.commitactivity.gateways;

import co.com.bancolombia.model.commitactivity.CommitActivity;
import co.com.bancolombia.model.repo.Repo;
import reactor.core.publisher.Mono;

public interface CommitActivityMetricService {
    Mono<Void> putCommitActivityMetric(Repo repo, CommitActivity commitActivity);
}
