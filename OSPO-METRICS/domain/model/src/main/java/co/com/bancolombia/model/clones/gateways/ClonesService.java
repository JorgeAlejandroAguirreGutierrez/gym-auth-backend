package co.com.bancolombia.model.clones.gateways;

import co.com.bancolombia.model.clones.Clones;
import reactor.core.publisher.Flux;

public interface ClonesService {
    Flux<Clones> getByRepoOwner(String owner, String type, int perPage);
    Flux<Clones> getByRepoName(String owner, String name, int perPage);
}
