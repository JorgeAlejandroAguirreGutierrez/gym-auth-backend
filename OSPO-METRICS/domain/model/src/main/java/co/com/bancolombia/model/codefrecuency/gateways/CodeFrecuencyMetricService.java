package co.com.bancolombia.model.codefrecuency.gateways;

import co.com.bancolombia.model.codefrecuency.CodeFrecuency;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CodeFrecuencyMetricService {
    Mono<Void> putMetric(CodeFrecuency codeFrecuency);
}
