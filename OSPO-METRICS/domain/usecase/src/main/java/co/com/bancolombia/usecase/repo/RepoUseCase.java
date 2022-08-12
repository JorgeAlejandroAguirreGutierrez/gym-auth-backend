package co.com.bancolombia.usecase.repo;

import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.model.repo.gateways.RepoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RepoUseCase {
    private final RepoService repoService;
    public Mono<Repo> getByName(String owner, String name) {
        return repoService.getByName(owner, name);
    }
    public Flux<Repo> getByOwner(String owner, String type, int perPage) {
        return repoService.getByOwner(owner, type, perPage);
    }
    public Flux<Repo> getByOrg(String org, int perPage) {
        return repoService.getByOrg(org, perPage);
    }
}
