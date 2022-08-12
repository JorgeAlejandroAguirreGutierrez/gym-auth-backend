package co.com.bancolombia.model.meetchannel;

import co.com.bancolombia.model.opensearchservice.Filter;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class OpenSearchResponseMeetChannel {

    private String indexName;
    private Filter filter;
    private List<MeetChannelEntity> metricMeetChannel;
}
