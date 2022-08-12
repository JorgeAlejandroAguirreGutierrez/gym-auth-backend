package co.com.bancolombia.model.repo.gateways;

import co.com.bancolombia.model.clones.Clones;
import co.com.bancolombia.model.repo.Repo;
import reactor.core.publisher.Mono;

public interface RepoMetricService {
    Mono<Repo> getRepoMetric(Repo repo);
    Mono<Void> putRepoMetric(Repo clones);
}
