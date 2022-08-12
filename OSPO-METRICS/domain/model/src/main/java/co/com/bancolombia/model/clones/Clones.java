package co.com.bancolombia.model.clones;

import co.com.bancolombia.model.repo.Repo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Clones {
    private Repo repo;
    private long uniques;
    private long count;
    private List<ClonesData> data;
}
