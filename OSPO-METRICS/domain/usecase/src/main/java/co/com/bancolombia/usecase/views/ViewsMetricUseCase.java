package co.com.bancolombia.usecase.views;

import co.com.bancolombia.model.views.Views;
import co.com.bancolombia.model.views.gateways.ViewsMetricService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ViewsMetricUseCase {
    private final ViewsMetricService viewsMetricService;
    public Mono<Void> putViewsMetric(Views views) {
        return viewsMetricService.putViewsMetric(views);
    }
}
