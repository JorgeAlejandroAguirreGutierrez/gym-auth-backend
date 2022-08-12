package co.com.bancolombia.model.views.gateways;

import co.com.bancolombia.model.views.Views;
import reactor.core.publisher.Flux;

public interface ViewsService {
    Flux<Views> getByRepoOwner(String owner, String type, int perPage);
    Flux<Views> getByRepoName(String owner, String name, int perPage);
}
