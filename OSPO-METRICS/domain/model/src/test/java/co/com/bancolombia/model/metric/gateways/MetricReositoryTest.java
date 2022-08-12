package co.com.bancolombia.model.metric.gateways;

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
import static org.springframework.test.util.AssertionErrors.assertEquals;


class MetricReositoryTest {

    @Mock
    MetricReository metricReository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMetrics() {
        MetricEntity metricEntity = MetricEntity.builder()
                .id(2)
                .area("Metric Area")
                .name("Matric Name")
                .year(2022)
                .goal(1521)
                .compliance(10)
                .measure("Measure metrics")
                .build();

        List<MetricEntity> metricEntityList = new ArrayList<>();
        metricEntityList.add(metricEntity);
        Flux<MetricEntity> metricEntityFlux = Flux.fromIterable(metricEntityList);

        when(metricReository.getAllMetrics()).thenReturn(metricEntityFlux);

        assertNotNull(metricReository.getAllMetrics());
        assertEquals("MetricEntity", metricEntityFlux, metricReository.getAllMetrics());
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
        assertEquals("MetricEntity", metricReository.getMetricById(2), metricReository.getMetricById(2));
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

        Mono<MetricEntity> entityFlux = Mono.just(meetChannelEntity);

        when(metricReository.createMetric(meetChannelEntity)).thenReturn(entityFlux);
        assertNotNull(metricReository.createMetric(meetChannelEntity));
        assertEquals("MetricEntity", metricReository.createMetric(meetChannelEntity), metricReository.createMetric(meetChannelEntity));
    }
}