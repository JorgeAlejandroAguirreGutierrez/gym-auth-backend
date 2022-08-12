package co.com.bancolombia.model.projectimpact.gateways;

import co.com.bancolombia.model.projectimpact.ProjectImpact;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectImpactService {
    Flux<ProjectImpact> getAll();
    Mono<ProjectImpact> getById(int id);
}
