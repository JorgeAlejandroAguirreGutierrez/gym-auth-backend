package co.com.bancolombia.model.metricarea.gateways;
import co.com.bancolombia.model.metricarea.MetricArea;
import reactor.core.publisher.Flux;
public interface MetricAreaService {
    Flux<MetricArea> getAll();
}
