package co.com.bancolombia.usecase.clones;

import co.com.bancolombia.model.clones.Clones;
import co.com.bancolombia.model.clones.gateways.ClonesService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ClonesUseCase {
    private final ClonesService clonesService;
    public Flux<Clones> getByRepoOwner(String owner, String type, int perPage) {
        return clonesService.getByRepoOwner(owner, type, perPage);
    }
    public Flux<Clones> getByRepoName(String owner, String name, int perPage) {
        return clonesService.getByRepoName(owner, name, perPage);
    }
}
