package co.com.bancolombia.model.meetchannelevent;

import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import co.com.bancolombia.model.opensearchservice.Filter;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class OpenSearchResponseMeetChannelEvent {

    private String indexName;
    private Filter filter;
    private List<MeetChannelEventEntity> meetChannelEventEntity;
}
