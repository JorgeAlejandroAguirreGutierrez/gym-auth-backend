package co.com.bancolombia.usecase.commitactivity;

import co.com.bancolombia.model.commitactivity.CommitActivity;
import co.com.bancolombia.model.commitactivity.gateways.CommitActivityService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CommitActivityUseCase {
    private final CommitActivityService commitActivityService;
    public Flux<CommitActivity> getByRepo(String owner, String name, int perPage) {
        return commitActivityService.getByRepo(owner, name, perPage);
    }
}
