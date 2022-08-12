package co.com.bancolombia.usecase.collaborator;

import co.com.bancolombia.model.collaborator.Collaborator;
import co.com.bancolombia.model.collaborator.gateways.CollaboratorService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CollaboratorUseCase {
    private final CollaboratorService collaboratorService;
    public Flux<Collaborator> getByRepo(String owner, String name, int perPage) {
        return collaboratorService.getByRepo(owner, name, perPage);
    }
}
