package co.com.bancolombia.usecase.contributor;

import co.com.bancolombia.model.contributor.Contributor;
import co.com.bancolombia.model.contributor.gateways.ContributorService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ContributorUseCase {
    private final ContributorService contributorService;
    public Flux<Contributor> getByRepo(String owner, String name, int perPage) {
        return contributorService.getByRepo(owner, name, perPage);
    }
}
