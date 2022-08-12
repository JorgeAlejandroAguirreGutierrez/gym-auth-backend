package co.com.bancolombia.model.meetchannelstatus.gateways;
import co.com.bancolombia.model.meetchannelstatus.MeetChannelStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface MeetChannelStatusService {
    Flux<MeetChannelStatus> getAll();
    Mono<MeetChannelStatus> getById(int id);
}
