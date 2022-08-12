package co.com.bancolombia.model.meetchannelevent;

import lombok.Builder;
import lombok.Data;

//import java.util.Date;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class MeetChannelEventEntity {
    private String messageError;
    private int id;
    private int meetchannelid;
    private String meetschannelname;
    private String name;
    private String detail;
    private String date;
    private String urlyoutube;
    private String urltwitch;
    private int assistants;
    private String state;

}
