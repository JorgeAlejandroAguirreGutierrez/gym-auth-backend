package co.com.bancolombia.model.projectdetail.gateways;

import co.com.bancolombia.model.project.ProjectEntity;
import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectDetailRepository {

    Flux<ProjectDetailEntity> getAllProjectsDetail();
    Flux<ProjectDetailEntity> getProjectDetailById(int id);
    Mono<ProjectDetailEntity> createProjectDetail(ProjectDetailEntity projectDetailEntity);
}
