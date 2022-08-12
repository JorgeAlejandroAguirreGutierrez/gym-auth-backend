package co.com.bancolombia.model.project.gateways;

import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.project.ProjectEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectRepository {
    Flux<ProjectEntity> getAllProjects();
    Flux<ProjectEntity> getProjectById(int id);
    Mono<ProjectEntity> createProject(ProjectEntity projectEntity);
}
