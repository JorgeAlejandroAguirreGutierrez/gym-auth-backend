package co.com.bancolombia.model.meetchannel.gateways;

import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MeetChannelRepository {

    Flux<MeetChannelEntity> getMeetChannelAll();

    Flux<MeetChannelEntity> getMeetChannelById(int id);

    Mono<MeetChannelEntity> createMeetChannel(MeetChannelEntity meetChannel);

}

