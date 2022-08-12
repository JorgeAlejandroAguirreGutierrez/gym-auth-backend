package co.com.bancolombia.usecase.metricsFromDb;

import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.metric.gateways.MetricReository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class MetricUseCase {

    private final MetricReository metricReository;

    public Flux<MetricEntity> getMetricAllOrById(int id){

        //if the request not contain the id then get all records. Another way get entity by id.
        if(id == 0){
            return metricReository.getAllMetrics().map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }
        else{
            return metricReository.getMetricById(id).map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }
    }

    public Mono<MetricEntity> createMetric(MetricEntity metricEntity){
        return metricReository.createMetric(metricEntity).map(x->{
            x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
            return x;
        });
    }


}
