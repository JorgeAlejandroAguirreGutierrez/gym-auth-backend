package co.com.bancolombia.model.collaborator.gateways;

import co.com.bancolombia.model.collaborator.Collaborator;
import co.com.bancolombia.model.repo.Repo;
import reactor.core.publisher.Mono;

public interface CollaboratorMetricService {
    Mono<Void> putCollaboratorMetric(Repo repo, Collaborator collaborator);
}
