package co.com.bancolombia.model.metric.gateways;

import co.com.bancolombia.model.metric.MetricEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MetricReository {
    Flux<MetricEntity> getAllMetrics();
    Flux<MetricEntity> getMetricById(int id);
    Mono<MetricEntity> createMetric(MetricEntity metricEntity);
}
