package co.com.bancolombia.model.collaborator.gateways;

import co.com.bancolombia.model.collaborator.Collaborator;
import reactor.core.publisher.Flux;

public interface CollaboratorService {
    Flux<Collaborator> getByRepo(String owner, String name, int perPage);
}
