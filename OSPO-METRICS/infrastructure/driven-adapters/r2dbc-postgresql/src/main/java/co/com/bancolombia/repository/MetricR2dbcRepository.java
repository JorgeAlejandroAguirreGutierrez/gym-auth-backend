package co.com.bancolombia.repository;

import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventRequest;
import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.metric.MetricRequest;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface MetricR2dbcRepository extends ReactiveCrudRepository<MetricEntity, Long> {

    @Query("SELECT  m.id, ma.\"name\" as \"area\", m.name, m.year, m.goal, m.compliance, m.measure FROM  public.metric m left join public.metricarea ma on ma.id = m.area")
    public Flux<MetricEntity> getMetricAll();

    @Query("SELECT  m.id,ma.\"name\" as \"area\", m.name, m.year, m.goal, m.compliance, m.measure FROM  public.metric m left join public.metricarea ma on ma.id = m.area WHERE m.id=:idmetric")
    public Flux<MetricEntity> getMetricById(@Param("idmetric") int id);

    @Query("INSERT INTO public.metric (id, \"area\", \"name\", \"year\", goal, compliance, measure ) values(:id, :area, :name, :year, :goal, :compliance, :measure)")
    public Mono<MetricEntity> createMetric(@Param("id") int id,
                                            @Param("area") int area,
                                            @Param("name") String name,
                                            @Param("year") int year,
                                            @Param("goal") long goal,
                                            @Param("compliance") long compliance,
                                            @Param("measure") String measure);


}

