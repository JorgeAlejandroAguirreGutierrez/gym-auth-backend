package co.com.bancolombia.model.pull.gateways;

import co.com.bancolombia.model.pull.Pull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PullService {
    Mono<Pull> getByNumber(String owner, String name, String number);
    Flux<Pull> getByRepo(String owner, String name, int perPage);
}
