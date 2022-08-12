package co.com.bancolombia.model.projectstatus.gateways;
import co.com.bancolombia.model.projectstatus.ProjectStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface ProjectStatusService {
    Flux<ProjectStatus> getAll();
    Mono<ProjectStatus> getById(int id);
}
