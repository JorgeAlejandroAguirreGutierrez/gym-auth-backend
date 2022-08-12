package co.com.bancolombia.model.codefrecuency.gateways;

import co.com.bancolombia.model.codefrecuency.CodeFrecuency;
import reactor.core.publisher.Flux;

public interface CodeFrecuencyService {
    Flux<CodeFrecuency> getByRepoName(String owner, String name, int perPage);
}
