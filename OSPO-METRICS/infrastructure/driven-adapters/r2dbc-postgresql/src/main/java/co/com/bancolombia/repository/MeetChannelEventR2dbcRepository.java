package co.com.bancolombia.repository;

import co.com.bancolombia.model.meetchannel.MeetChannelRequest;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventRequest;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface MeetChannelEventR2dbcRepository extends ReactiveCrudRepository<MeetChannelEventEntity, Long> {

    //meetschannelname
    @Query("SELECT me.id as \"id\", m.channel_name as \"meetschannelname\", meetchannelid as \"meetchannelid\", me.name as \"name\", detail, date, urlyoutube, urltwitch, assistants, ms.name as \"state\" FROM public.meetchannelevent me inner join public.meetchannelstatus ms on ms.id = me.state INNER JOIN public.meetchannel m ON m.id = me.meetchannelid")
    public Flux<MeetChannelEventEntity> getMeetChannelEventAll();

    @Query("SELECT me.id as \"id\", m.channel_name as \"meetschannelname\", meetchannelid as \"meetchannelid\", me.name as \"name\", detail, date, urlyoutube, urltwitch, assistants, ms.name as \"state\" FROM public.meetchannelevent me inner join public.meetchannelstatus ms on ms.id = me.state INNER JOIN public.meetchannel m ON m.id = me.meetchannelid where me.id = :idmeetchannelevent")
    public Flux<MeetChannelEventEntity> getMeetChannelEventById(@Param("idmeetchannelevent") int id);

    @Query("INSERT INTO public.meetchannelevent (id, meetchannelid, \"name\", detail, \"date\", urlyoutube, urltwitch, assistants, state) values(:id, :meetchannelid, :name, :detail, :date, :urlyoutube, :urltwitch, :assistants, :state)")
    public Mono<MeetChannelEventEntity> createMeetChannelEvent(@Param("id") int id,
                                                               @Param("meetchannelid") int meetchannelidame,
                                                               @Param("name") String name,
                                                               @Param("detail") String detail,
                                                               @Param("date") String date,
                                                               @Param("urlyoutube") String urlyoutube,
                                                               @Param("urltwitch") String urltwitch,
                                                               @Param("assistants") int assistants,
                                                               @Param("state") int state);


}
