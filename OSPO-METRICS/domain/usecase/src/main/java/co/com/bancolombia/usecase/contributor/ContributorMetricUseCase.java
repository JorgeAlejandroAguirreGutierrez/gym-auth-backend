package co.com.bancolombia.usecase.contributor;

import co.com.bancolombia.model.contributor.Contributor;
import co.com.bancolombia.model.contributor.gateways.ContributorMetricService;
import co.com.bancolombia.model.repo.Repo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ContributorMetricUseCase {
    private final ContributorMetricService contributorMetricService;
    public Mono<Void> putContributorMetric(Repo repo, Contributor contributor) {
        return contributorMetricService.putContributorMetric(repo, contributor);
    }
}
