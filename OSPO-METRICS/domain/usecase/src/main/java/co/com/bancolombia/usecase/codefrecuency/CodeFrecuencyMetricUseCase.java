package co.com.bancolombia.usecase.codefrecuency;

import co.com.bancolombia.model.codefrecuency.CodeFrecuency;
import co.com.bancolombia.model.codefrecuency.gateways.CodeFrecuencyMetricService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class CodeFrecuencyMetricUseCase {
    private final CodeFrecuencyMetricService codeFrecuencyMetricService;
    public Mono<Void> putMetric(CodeFrecuency codeFrecuency) {
        return codeFrecuencyMetricService.putMetric(codeFrecuency);
    }
}
