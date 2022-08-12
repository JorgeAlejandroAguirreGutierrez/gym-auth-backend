package co.com.bancolombia.usecase.pull;

import co.com.bancolombia.model.pull.Pull;
import co.com.bancolombia.model.pull.gateways.PullService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PullUseCase {
    private final PullService pullService;
    public Mono<Pull> getByNumber(String owner, String name, String number) {
        return pullService.getByNumber(owner, name, number);
    }
    public Flux<Pull> getByRepo(String owner, String name, int perPage) {
        return pullService.getByRepo(owner, name, perPage);
    }
}
