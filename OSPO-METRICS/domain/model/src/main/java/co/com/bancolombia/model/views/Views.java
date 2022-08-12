package co.com.bancolombia.model.views;

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
public class Views {
    private Repo repo;
    private long uniques;
    private long count;
    private List<ViewsData> data;
}
