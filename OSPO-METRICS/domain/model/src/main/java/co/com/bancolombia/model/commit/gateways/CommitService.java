package co.com.bancolombia.model.commit.gateways;

import co.com.bancolombia.model.commit.Commit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommitService {
    Mono<Commit> getBySha(String owner, String name, String sha);
    Flux<Commit> getByRepo(String owner, String name, int perPage);
    Flux<Commit> getByDate(String owner, String name, String since, int perPage);
}
