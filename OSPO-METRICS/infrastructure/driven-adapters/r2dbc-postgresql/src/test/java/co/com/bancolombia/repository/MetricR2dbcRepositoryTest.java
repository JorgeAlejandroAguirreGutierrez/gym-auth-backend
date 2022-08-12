package co.com.bancolombia.repository;

import co.com.bancolombia.model.metric.MetricEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MetricR2dbcRepositoryTest {


    @Mock
    MetricR2dbcRepository metricReository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMetricAll() {
        MetricEntity meetChannelEntity = MetricEntity.builder()
                .id(2)
                .area("Metric Area")
                .name("Matric Name")
                .year(2022)
                .goal(1521)
                .compliance(10)
                .measure("Measure metrics")
                .build();

        List<MetricEntity> lstEntity = new ArrayList<>();
        lstEntity.add(meetChannelEntity);
        Flux<MetricEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(metricReository.getMetricAll()).thenReturn(entityFlux);
        assertNotNull(metricReository.getMetricAll());
    }

    @Test
    void getMetricById() {
        MetricEntity meetChannelEntity = MetricEntity.builder()
                .id(2)
                .area("Metric Area")
                .name("Matric Name")
                .year(2022)
                .goal(1521)
                .compliance(10)
                .measure("Measure metrics")
                .build();

        List<MetricEntity> lstEntity = new ArrayList<>();
        lstEntity.add(meetChannelEntity);
        Flux<MetricEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(metricReository.getMetricById(2)).thenReturn(entityFlux);
        assertNotNull(metricReository.getMetricById(2));
    }

    @Test
    void createMetric() {
        MetricEntity meetChannelEntity = MetricEntity.builder()
                .id(2)
                .area("Metric Area")
                .name("Matric Name")
                .year(2022)
                .goal(1521)
                .compliance(10)
                .measure("Measure metrics")
                .build();

        Mono<MetricEntity> entityMono = Mono.just(meetChannelEntity);

        when(metricReository.createMetric(2, 2, "Matric Name", 2022, 1521, 10, "Measure metrics")).thenReturn(entityMono);
        assertNotNull(metricReository.createMetric(2, 2, "Matric Name", 2022, 1521, 10, "Measure metrics"));
    }
}