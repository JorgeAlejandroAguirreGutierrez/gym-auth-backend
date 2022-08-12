package co.com.bancolombia.model.commitactivity.gateways;

import co.com.bancolombia.model.commitactivity.CommitActivity;
import reactor.core.publisher.Flux;

public interface CommitActivityService {
    Flux<CommitActivity> getByRepo(String owner, String name, int perPage);
}
