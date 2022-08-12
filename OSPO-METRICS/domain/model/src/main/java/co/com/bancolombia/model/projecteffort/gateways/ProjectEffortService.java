package co.com.bancolombia.model.projecteffort.gateways;

import co.com.bancolombia.model.projecteffort.ProjectEffort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectEffortService {
    Flux<ProjectEffort> getAll();
    Mono<ProjectEffort> getById(int id);
}
