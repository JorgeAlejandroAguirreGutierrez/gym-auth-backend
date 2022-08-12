package co.com.bancolombia.usecase.metricsFromDb;

import co.com.bancolombia.model.project.ProjectEntity;
import co.com.bancolombia.model.project.gateways.ProjectRepository;
import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
import co.com.bancolombia.model.projectdetail.gateways.ProjectDetailRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProjectDetailUseCase {

    private final ProjectDetailRepository projectDetailRepository;

    public Flux<ProjectDetailEntity> getProjectDetailAllOrById(int id){

        //if the request not contain the id then get all records. Another way get entity by id.
        if(id == 0){
            return projectDetailRepository.getAllProjectsDetail().map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }
        else{
            return projectDetailRepository.getProjectDetailById(id).map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }
    }

    public Mono<ProjectDetailEntity> createProjectDetail(ProjectDetailEntity projectDetailEntity){
        return projectDetailRepository.createProjectDetail(projectDetailEntity).map(x->{
            x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
            return x;
        });
    }

}
