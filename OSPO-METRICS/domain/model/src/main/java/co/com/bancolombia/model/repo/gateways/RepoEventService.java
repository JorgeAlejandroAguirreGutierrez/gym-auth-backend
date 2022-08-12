package co.com.bancolombia.model.repo.gateways;

import co.com.bancolombia.model.repo.Repo;
import reactor.core.publisher.Mono;

public interface RepoEventService {
    Mono<Void> emitRepo(Repo repo);
    Mono<Void> emitRepoClones(Repo repo);
    Mono<Void> emitRepoCodeFrecuency(Repo repo);
    Mono<Void> emitRepoCollaborator(Repo repo);
    Mono<Void> emitRepoCommit(Repo repo);
    Mono<Void> emitRepoCommitActivity(Repo repo);
    Mono<Void> emitRepoContributor(Repo repo);
    Mono<Void> emitRepoIssues(Repo repo);
    Mono<Void> emitRepoPulls(Repo repo);
    Mono<Void> emitRepoViews(Repo repo);
}
