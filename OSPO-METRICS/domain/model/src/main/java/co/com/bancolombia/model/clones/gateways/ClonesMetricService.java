package co.com.bancolombia.model.clones.gateways;

import co.com.bancolombia.model.clones.Clones;
import reactor.core.publisher.Mono;

public interface ClonesMetricService {
    Mono<Void> putMetric(Clones clones);
}
