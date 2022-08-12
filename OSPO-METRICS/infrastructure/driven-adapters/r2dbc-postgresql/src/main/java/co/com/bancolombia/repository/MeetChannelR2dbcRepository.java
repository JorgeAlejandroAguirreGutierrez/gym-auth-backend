package co.com.bancolombia.repository;

import co.com.bancolombia.model.meetchannel.MeetChannelRequest;
import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface MeetChannelR2dbcRepository extends ReactiveCrudRepository<MeetChannelEntity, Long> {

    @Query("SELECT  id, channel_name as \"name\", event_name as \"eventname\",  about, location, members FROM public.meetchannel ")
    public Flux<MeetChannelEntity> getMeetChannelAll();

    @Query("SELECT  id, channel_name as \"name\", event_name as \"eventname\", about, location, members FROM public.meetchannel WHERE id=:idmeetchannel;")
    public Flux<MeetChannelEntity> getMeetChannelById(@Param("idmeetchannel") int idmeetchannel);

    @Query("INSERT INTO public.meetchannel (id, channel_name, event_name, about, \"location\", members) VALUES(:id,:name_dato,:event_name,:about_dato,:location_dato,:members_dato)")
    public Mono<MeetChannelEntity> createMeetChannel(@Param("id") int id,
                                                     @Param("name_dato") String name,
                                                     @Param("event_name") String event_name,
                                                     @Param("about_dato") String about,
                                                     @Param("location_dato") String location,
                                                     @Param("members_dato") Long members);



}
