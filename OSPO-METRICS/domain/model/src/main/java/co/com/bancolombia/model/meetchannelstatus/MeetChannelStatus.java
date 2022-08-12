package co.com.bancolombia.model.meetchannelstatus;
import lombok.Builder;
import lombok.Data;
@Data
@Builder(toBuilder = true)
public class MeetChannelStatus {
    private int id;
    private String name;
}
