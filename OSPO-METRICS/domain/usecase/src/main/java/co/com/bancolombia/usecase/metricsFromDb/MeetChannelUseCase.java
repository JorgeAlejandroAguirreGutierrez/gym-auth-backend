package co.com.bancolombia.usecase.metricsFromDb;

import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import co.com.bancolombia.model.meetchannel.gateways.MeetChannelRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class MeetChannelUseCase {

    private final MeetChannelRepository meetChannelRepository;

    public Flux<MeetChannelEntity> getMeetChannel(int id){

        if(id == 0){
            return meetChannelRepository.getMeetChannelAll().map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }
        else{
            return meetChannelRepository.getMeetChannelById(id).map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }

    }

    public Mono<MeetChannelEntity> createMeetChannel(MeetChannelEntity meetChannel){
        return meetChannelRepository.createMeetChannel(meetChannel).map(x->{
            x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
            return x;
        });
    }

}
