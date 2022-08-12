package co.com.bancolombia.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GithubCommitActivityDTO {
    @JsonProperty("days")
    private long[] days;
    @JsonProperty("total")
    private long total;
    @JsonProperty("week")
    private long week;
    public Date getDateFrom() {
        Date dateFrom = new Date(this.getWeek() * 1000);
        return dateFrom;
    }
    public Date getDateTo() {
        Calendar dateTo = Calendar.getInstance();
        dateTo.setTime(new Date(this.getWeek() * 1000));
        dateTo.add(Calendar.DATE, 7);
        return dateTo.getTime();
    }
}
