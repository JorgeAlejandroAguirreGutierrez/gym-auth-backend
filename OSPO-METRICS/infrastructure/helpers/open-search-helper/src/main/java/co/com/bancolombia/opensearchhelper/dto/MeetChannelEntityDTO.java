package co.com.bancolombia.opensearchhelper.dto;

import co.com.bancolombia.opensearchhelper.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class MeetChannelEntityDTO {

    @JsonProperty(Constants.TIME_STAMP)
    private String timestamp;

    @JsonProperty("meets_channel_id")
    private int id;

    @JsonProperty("meets_channel_name")
    private String name;

    @JsonProperty("meet_channel_event_name")
    private String nameEvent;

    @JsonProperty("meets_channel_about")
    private String about;

    @JsonProperty("meets_channel_location")
    private String location;

    @JsonProperty("meets_channel_total_members")
    private long members;

}
