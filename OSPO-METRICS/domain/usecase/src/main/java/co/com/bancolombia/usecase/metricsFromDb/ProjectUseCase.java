package co.com.bancolombia.usecase.metricsFromDb;

import co.com.bancolombia.model.project.ProjectEntity;
import co.com.bancolombia.model.project.gateways.ProjectRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProjectUseCase {

    private final ProjectRepository projectRepository;

    public Flux<ProjectEntity> getProjectAllOrById(int id){

        //if the request not contain the id then get all records. Another way get entity by id.
        if(id == 0){
            return projectRepository.getAllProjects().map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }
        else{
            return projectRepository.getProjectById(id).map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }
    }

    public Mono<ProjectEntity> createProject(ProjectEntity projectEntity){
        return projectRepository.createProject(projectEntity).map(x->{
            x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
            return x;
        });
    }

}
