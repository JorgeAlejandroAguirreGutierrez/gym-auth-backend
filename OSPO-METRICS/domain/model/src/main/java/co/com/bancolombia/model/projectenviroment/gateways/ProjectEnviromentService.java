package co.com.bancolombia.model.projectenviroment.gateways;
import co.com.bancolombia.model.projectenviroment.ProjectEnviroment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface ProjectEnviromentService {
    Flux<ProjectEnviroment> getAll();
    Mono<ProjectEnviroment> getById(int id);
}
