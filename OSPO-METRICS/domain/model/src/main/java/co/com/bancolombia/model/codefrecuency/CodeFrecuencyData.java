package co.com.bancolombia.model.codefrecuency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CodeFrecuencyData {
    private String id;
    private String key;
    private long week;
    private Date dateFrom;
    private Date dateTo;
    private long additions;
    private long deletions;
}
