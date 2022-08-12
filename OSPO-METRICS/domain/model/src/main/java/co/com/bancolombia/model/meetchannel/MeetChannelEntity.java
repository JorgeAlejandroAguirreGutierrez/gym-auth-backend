package co.com.bancolombia.model.meetchannel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MeetChannelEntity {
    private String messageError;
    private int id;
    private String name;
    private String eventname;
    private String about;
    private String location;
    private long members;


}
