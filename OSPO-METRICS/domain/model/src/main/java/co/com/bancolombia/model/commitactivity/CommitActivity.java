package co.com.bancolombia.model.commitactivity;

import co.com.bancolombia.model.repo.Repo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommitActivity {
    private String id;
    private String key;
    private long week;
    private Date dateFrom;
    private Date dateTo;
    private long totalSunday;
    private long totalMonday;
    private long totalTuesday;
    private long totalWednesday;
    private long totalThursday;
    private long totalFriday;
    private long totalSaturday;
    private long total;
}
