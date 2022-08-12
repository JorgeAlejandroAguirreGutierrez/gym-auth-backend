package co.com.bancolombia.usecase.repo;

import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.model.repo.gateways.RepoMetricService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RepoMetricUseCase {
    private final RepoMetricService repoMetricService;
    public Mono<Repo> getRepoMetric(Repo repo) {
        return repoMetricService.getRepoMetric(repo);
    }
    public Mono<Void> putRepoMetric(Repo repo) {
        return repoMetricService.putRepoMetric(repo);
    }
}
