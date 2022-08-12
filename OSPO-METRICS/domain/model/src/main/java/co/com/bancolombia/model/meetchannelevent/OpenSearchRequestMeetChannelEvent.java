package co.com.bancolombia.model.meetchannelevent;

import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import co.com.bancolombia.model.opensearchservice.Filter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpenSearchRequestMeetChannelEvent {

    private String indexName;
    private Filter filter;
    private MeetChannelEventEntity meetChannelEventEntity;
}
