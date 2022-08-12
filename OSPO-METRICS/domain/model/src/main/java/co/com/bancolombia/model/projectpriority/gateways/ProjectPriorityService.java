package co.com.bancolombia.model.projectpriority.gateways;

import co.com.bancolombia.model.projectpriority.ProjectPriority;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectPriorityService {
    Flux<ProjectPriority> getAll();
    Mono<ProjectPriority> getById(int id);
}
