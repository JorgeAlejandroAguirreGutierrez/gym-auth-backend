package co.com.bancolombia.model.opensearchservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class IndexSettingsInfo {

    private IndexInfo index;
    private String providedName;
}
