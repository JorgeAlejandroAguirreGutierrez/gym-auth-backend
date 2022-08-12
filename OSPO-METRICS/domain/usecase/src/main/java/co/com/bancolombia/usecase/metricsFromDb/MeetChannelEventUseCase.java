package co.com.bancolombia.usecase.metricsFromDb;

import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import co.com.bancolombia.model.meetchannelevent.gateways.MeetChannelEventRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class MeetChannelEventUseCase {

    private final MeetChannelEventRepository meetChannelEventRepository;

    public Flux<MeetChannelEventEntity> getMeetChannel(int id){

        //if the entity not exists get all records. Another way get entity by id.
        if(id == 0){
            return meetChannelEventRepository.getMeetChannelEventAll().map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }
        else{
            return meetChannelEventRepository.getMeetChannelEventById(id).map(x->{
                x.setMessageError(x.getMessageError()==null?"":x.getMessageError());
                return x;
            });
        }

    }

    public Mono<MeetChannelEventEntity> createMeetChannelEvent(MeetChannelEventEntity meetChannelEventEntity){

        if(isNumeric(meetChannelEventEntity.getState())){
            return meetChannelEventRepository.createMeetChannelEvent(meetChannelEventEntity);
        }
        else{
            MeetChannelEventEntity meetChannelEventEntityError = MeetChannelEventEntity.builder()
                    .messageError("El campo estado debe ser un numerico")
                    .build();

            return Mono.just(meetChannelEventEntityError);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}
