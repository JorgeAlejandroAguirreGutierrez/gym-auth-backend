package co.com.bancolombia.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GithubClonesDataDTO {
    @JsonProperty("count")
    private long count;
    @JsonProperty("uniques")
    private long uniques;
    @JsonProperty("timestamp")
    private Date timestamp;
    public Date getDateFrom() {
        return this.getTimestamp();
    }
    public Date getDateTo() {
        Calendar dateTo = Calendar.getInstance();
        dateTo.setTime(this.getTimestamp());
        dateTo.add(Calendar.DATE, 7);
        return dateTo.getTime();
    }
}
