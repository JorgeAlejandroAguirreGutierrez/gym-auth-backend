package co.com.bancolombia.opensearchhelper.dto;

import co.com.bancolombia.opensearchhelper.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

//import java.util.Date;


@Data
@Builder(toBuilder = true)
public class MeetChannelEventEntityDTO {

    @JsonProperty(Constants.TIME_STAMP)
    private String timestamp;

    @JsonProperty("meets_channel_event_id")
    private int id;

    @JsonProperty("meets_channel_event_name")
    private String name;

    @JsonProperty("meets_channel_id")
    private int meetschannelid;

    @JsonProperty("meets_channel_name")
    private String meetschannelname;

    @JsonProperty("meets_channel_event_detail")
    private String detail;

    @JsonProperty("meets_channel_event_date")
    private String date;

    @JsonProperty("meets_channel_event_connection_youtube")
    private String urlyoutube;

    @JsonProperty("meets_channel_event_connection_twitch")
    private String urltwitch;

    @JsonProperty("meets_channel_event_assistants_total")
    private int assistants;

    @JsonProperty("meets_channel_event_state")
    private String state;

}
