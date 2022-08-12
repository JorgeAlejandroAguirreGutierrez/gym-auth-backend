package co.com.bancolombia.model.repo.gateways;

import co.com.bancolombia.model.repo.Repo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepoService {
    Mono<Repo> getByName(String owner, String name);
    Flux<Repo> getByOwner(String owner, String type, int perPage);
    Flux<Repo> getByOrg(String org, int perPage);
}
