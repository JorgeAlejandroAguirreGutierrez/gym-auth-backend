package co.com.bancolombia.opensearchhelper.dto;

import co.com.bancolombia.opensearchhelper.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectDetailDTO {

    @JsonProperty(Constants.TIME_STAMP)
    private String timestamp;

    @JsonProperty("project_detail_id")
    private int id;

    @JsonProperty("project_component_licensed_cost_usd")
    private long costlicensedusd;

    @JsonProperty("project_component_licensed_cost_cop")
    private long costlicensedcop;

    @JsonProperty("project_component_ospo_cost_usd")
    private long costospousd;

    @JsonProperty("project_component_ospo_cost_cop")
    private long costospocop;

    @JsonProperty("project_component_saving_cost_usd")
    private long costsaving;

    @JsonProperty("project_component_saving_cost_cop")
    private long costsavingcop;

    @JsonProperty("project_component_licensed_subtype")
    private String projectsubtypelicensed;

    @JsonProperty("project_component_ospo_subtype")
    private String projectsubtypeospo;

    @JsonProperty("project_detail_quantity_migrated")
    private int quantitymigrated;

    @JsonProperty("project_detail_quantity_objective")
    private int quantityobjective;

    @JsonProperty("project_detail_comments")
    private String comments;

    @JsonProperty("project_environment_status")
    private String projectstatus;

    @JsonProperty("projectdetailproject")
    private String project;

    @JsonProperty("project_environment")
    private String projectenviroment;

    @JsonProperty("project_component_licensed_type")
    private String projecttypelicensed;

    @JsonProperty("project_component_ospo_type")
    private String projecttypeospo;

    @JsonProperty("project_evc")
    private String projectevc;
}
