package co.com.bancolombia.model.projecttype.gateways;

import co.com.bancolombia.model.projecttype.ProjectType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectTypeService {
    Flux<ProjectType> getAll();
    Mono<ProjectType> getById(int id);
}
