package co.com.bancolombia.model.meetchannelevent.gateways;

import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MeetChannelEventRepository {
    
    Flux<MeetChannelEventEntity> getMeetChannelEventAll();

    Flux<MeetChannelEventEntity> getMeetChannelEventById(int id);

    Mono<MeetChannelEventEntity> createMeetChannelEvent(MeetChannelEventEntity meetChannelEventEntity);

}
