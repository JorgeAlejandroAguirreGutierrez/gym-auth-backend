package co.com.bancolombia.model.meetchannel;

import co.com.bancolombia.model.opensearchservice.Filter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OpenSearchRequestMeetChannel {

    private String indexName;
    private Filter filter;
    private MeetChannelEntity metricMeetChannel;
}
