package co.com.bancolombia.model.contributor.gateways;

import co.com.bancolombia.model.contributor.Contributor;
import reactor.core.publisher.Flux;

public interface ContributorService {
    Flux<Contributor> getByRepo(String owner, String name, int perPage);
}
