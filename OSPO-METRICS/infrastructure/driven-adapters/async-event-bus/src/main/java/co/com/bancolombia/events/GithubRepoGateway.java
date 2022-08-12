package co.com.bancolombia.events;

import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.model.repo.gateways.RepoEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GithubRepoGateway implements RepoEventService {
    private final ReactiveDirectAsyncGateway reactiveGateway;
    @Override
    public Mono<Void> emitRepoClones(Repo repo) {
        return reactiveGateway.runRemoteJobClones(repo);
    }
    @Override
    public Mono<Void> emitRepoCodeFrecuency(Repo repo) {
        return reactiveGateway.runRemoteJobCodeFrecuency(repo);
    }
    @Override
    public Mono<Void> emitRepoCollaborator(Repo repo) {
        return reactiveGateway.runRemoteJobCollaborator(repo);
    }
    @Override
    public Mono<Void> emitRepoCommit(Repo repo) {
        return reactiveGateway.runRemoteJobCommit(repo);
    }
    @Override
    public Mono<Void> emitRepoCommitActivity(Repo repo) {
        return reactiveGateway.runRemoteJobCommitActivity(repo);
    }
    @Override
    public Mono<Void> emitRepoContributor(Repo repo) {
        return reactiveGateway.runRemoteJobContributor(repo);
    }
    @Override
    public Mono<Void> emitRepoIssues(Repo repo) {
        return reactiveGateway.runRemoteJobIssues(repo);
    }
    @Override
    public Mono<Void> emitRepoPulls(Repo repo) {
        return reactiveGateway.runRemoteJobPulls(repo);
    }
    @Override
    public Mono<Void> emitRepo(Repo repo) {
        return reactiveGateway.runRemoteJobRepo(repo);
    }
    @Override
    public Mono<Void> emitRepoViews(Repo repo) {
        return reactiveGateway.runRemoteJobViews(repo);
    }
}
