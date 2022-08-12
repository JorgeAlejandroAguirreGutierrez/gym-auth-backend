package co.com.bancolombia.model.clones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClonesData {
    private String id;
    private String key;
    private long count;
    private long uniques;
    private Date date;
    private Date dateFrom;
    private Date dateTo;
}
