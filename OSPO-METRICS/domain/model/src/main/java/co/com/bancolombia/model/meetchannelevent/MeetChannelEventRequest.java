package co.com.bancolombia.model.meetchannelevent;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MeetChannelEventRequest {

    private int id;

}
