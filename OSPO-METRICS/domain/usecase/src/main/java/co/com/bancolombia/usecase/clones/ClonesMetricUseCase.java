package co.com.bancolombia.usecase.clones;

import co.com.bancolombia.model.clones.Clones;
import co.com.bancolombia.model.clones.gateways.ClonesMetricService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ClonesMetricUseCase {
    private final ClonesMetricService clonesMetricService;
    public Mono<Void> putMetric(Clones clones) {
        return clonesMetricService.putMetric(clones);
    }
}
