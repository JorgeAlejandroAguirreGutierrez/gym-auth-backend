package co.com.bancolombia.usecase.commit;

import co.com.bancolombia.model.commit.Commit;
import co.com.bancolombia.model.commit.gateways.CommitService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CommitUseCase {
    private final CommitService commitService;
    public Mono<Commit> getBySha(String owner, String name, String sha) {
        return commitService.getBySha(owner, name, sha);
    }
    public Flux<Commit> getByRepo(String owner, String name, int perPage) {
        return commitService.getByRepo(owner, name, perPage);
    }
    public Flux<Commit> getByDate(String owner, String name, String since, int perPage) {
        return commitService.getByDate(owner, name, since, perPage);
    }
}
