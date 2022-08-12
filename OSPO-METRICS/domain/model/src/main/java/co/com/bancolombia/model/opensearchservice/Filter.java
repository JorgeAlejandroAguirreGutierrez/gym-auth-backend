package co.com.bancolombia.model.opensearchservice;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;

@Data
@Builder(toBuilder = true)
public class Filter {

    private String key;
    private String value;
}
