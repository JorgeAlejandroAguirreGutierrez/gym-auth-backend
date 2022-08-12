package co.com.bancolombia.model.pull.gateways;

import co.com.bancolombia.model.pull.Pull;
import co.com.bancolombia.model.repo.Repo;
import reactor.core.publisher.Mono;

public interface PullMetricService {
    Mono<Void> putPullMetric(Repo repo, Pull pull);
}
