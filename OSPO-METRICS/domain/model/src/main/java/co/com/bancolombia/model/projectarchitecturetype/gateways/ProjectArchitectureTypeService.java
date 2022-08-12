package co.com.bancolombia.model.projectarchitecturetype.gateways;

import co.com.bancolombia.model.projectarchitecturetype.ProjectArchitectureType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectArchitectureTypeService {
    Flux<ProjectArchitectureType> getAll();
    Mono<ProjectArchitectureType> getById(int id);
}
