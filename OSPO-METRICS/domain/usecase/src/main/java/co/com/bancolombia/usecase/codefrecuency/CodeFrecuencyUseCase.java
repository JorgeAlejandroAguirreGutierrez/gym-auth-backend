package co.com.bancolombia.usecase.codefrecuency;

import co.com.bancolombia.model.codefrecuency.CodeFrecuency;
import co.com.bancolombia.model.codefrecuency.gateways.CodeFrecuencyService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CodeFrecuencyUseCase {
    private final CodeFrecuencyService codeFrecuencyService;
    public Flux<CodeFrecuency> getByRepoName(String owner, String name, int perPage) {
        return codeFrecuencyService.getByRepoName(owner, name, perPage);
    }
}
