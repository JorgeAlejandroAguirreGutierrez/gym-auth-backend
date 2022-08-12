package co.com.bancolombia.model.projectsubtype.gateways;

import co.com.bancolombia.model.projectsubtype.ProjectSubType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectSubTypeService {
    Flux<ProjectSubType> getAll();
    Mono<ProjectSubType> getById(int id);
}
