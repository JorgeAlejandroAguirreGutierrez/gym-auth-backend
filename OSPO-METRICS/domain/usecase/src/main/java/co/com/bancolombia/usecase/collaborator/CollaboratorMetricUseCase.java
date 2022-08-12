package co.com.bancolombia.usecase.collaborator;

import co.com.bancolombia.model.collaborator.Collaborator;
import co.com.bancolombia.model.collaborator.gateways.CollaboratorMetricService;
import co.com.bancolombia.model.repo.Repo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CollaboratorMetricUseCase {
    private final CollaboratorMetricService collaboratorMetricService;
    public Mono<Void> putCollaboratorMetric(Repo repo, Collaborator collaborator) {
        return collaboratorMetricService.putCollaboratorMetric(repo, collaborator);
    }
}
