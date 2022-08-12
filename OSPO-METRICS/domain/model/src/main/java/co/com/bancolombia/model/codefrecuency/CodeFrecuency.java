package co.com.bancolombia.model.codefrecuency;

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
public class CodeFrecuency {
    private Repo repo;
    private List<CodeFrecuencyData> data;
}
