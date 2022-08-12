package co.com.bancolombia.usecase.pull;

import co.com.bancolombia.model.pull.Pull;
import co.com.bancolombia.model.pull.gateways.PullMetricService;
import co.com.bancolombia.model.repo.Repo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PullMetricUseCase {
    private final PullMetricService pullMetricService;
    public Mono<Void> putPullMetric(Repo repo, Pull pull) {
        return pullMetricService.putPullMetric(repo, pull);
    }
}
