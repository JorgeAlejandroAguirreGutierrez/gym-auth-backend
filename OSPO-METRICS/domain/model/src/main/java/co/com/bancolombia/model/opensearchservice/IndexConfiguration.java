package co.com.bancolombia.model.opensearchservice;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;

@Data
@Builder(toBuilder = true)
public class IndexConfiguration {

    private LinkedList aliases;
    private LinkedList mappings;
    private IndexSettingsInfo settings;
}
