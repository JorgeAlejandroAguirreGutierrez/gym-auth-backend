package co.com.bancolombia.model.contributor.gateways;

import co.com.bancolombia.model.contributor.Contributor;
import co.com.bancolombia.model.repo.Repo;
import reactor.core.publisher.Mono;

public interface ContributorMetricService {
    Mono<Void> putContributorMetric(Repo repo, Contributor contributor);
}
