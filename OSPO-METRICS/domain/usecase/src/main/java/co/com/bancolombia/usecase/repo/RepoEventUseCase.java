package co.com.bancolombia.usecase.repo;

import co.com.bancolombia.model.repo.gateways.RepoMetricService;
import co.com.bancolombia.model.repo.gateways.RepoEventService;
import co.com.bancolombia.model.repo.gateways.RepoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RepoEventUseCase {
    private final RepoService repoService;
    private final RepoMetricService repoMetricService;
    private final RepoEventService repoEventService;
    public Mono<Void> emitEvents(String owner, int perPage) {
        return repoService.getByOrg(owner, perPage)
                .flatMap(repoMetricService::getRepoMetric)
                .flatMap(repo ->
                    repoEventService.emitRepoClones(repo)
                        .then(repoEventService.emitRepoCodeFrecuency(repo)
//                            .then(repoEventService.emitRepoCollaborator(repo)
//                                .then(repoEventService.emitRepoCommit(repo)
//                                    .then(repoEventService.emitRepoCommitActivity(repo)
//                                        .then(repoEventService.emitRepoContributor(repo))
//                                            .then(repoEventService.emitRepoIssues(repo)
//                                                .then(repoEventService.emitRepoPulls(repo)
//                                                    .then(repoEventService.emitRepoViews(repo)
//                                                        .then(repoEventService.emitRepo(repo))
//                                                    )
//                                                )
//                                            )
//                                        )
//                                    )
//                                )
                            )
                )
                .then();
    }
}
