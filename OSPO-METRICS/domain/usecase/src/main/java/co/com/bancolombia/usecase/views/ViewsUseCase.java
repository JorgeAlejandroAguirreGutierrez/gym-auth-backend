package co.com.bancolombia.usecase.views;

import co.com.bancolombia.model.views.Views;
import co.com.bancolombia.model.views.gateways.ViewsService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ViewsUseCase {
    private final ViewsService viewsService;
    public Flux<Views> getByRepoName(String owner, String name, int perPage) {
        return viewsService.getByRepoName(owner, name, perPage);
    }
}
