package co.com.bancolombia.model.views.gateways;

import co.com.bancolombia.model.views.Views;
import reactor.core.publisher.Mono;

public interface ViewsMetricService {
    Mono<Void> putViewsMetric(Views views);
}
