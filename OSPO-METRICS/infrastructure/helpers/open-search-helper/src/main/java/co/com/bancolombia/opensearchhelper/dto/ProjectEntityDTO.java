package co.com.bancolombia.opensearchhelper.dto;
import co.com.bancolombia.opensearchhelper.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectEntityDTO {

    @JsonProperty(Constants.TIME_STAMP)
    private String timestamp;

    @JsonProperty("project_id")
    private int id;

    @JsonProperty("project_aws")
    private String awscode;

    @JsonProperty("project_licensed_name")
    private String nameProjectLicensed;

    @JsonProperty("project_ospo_name")
    private String nameProjectOSPO;

    @JsonProperty("project_person_lead_ospo")
    private String personLeadOSPO;

    @JsonProperty("project_evc")
    private String nameEVC;

    @JsonProperty("project_person_lead_evc")
    private String personLeadEVC;

    @JsonProperty("project_first_contact_date")
    private String firstContactDate;

    @JsonProperty("project_licensed_cost_usd")
    private Long costLicensedUSD;

    @JsonProperty("project_licensed_cost_cop")
    private Long costLicensedCOP;

    @JsonProperty("project_ospo_cost_usd")
    private Long costOSPOUSD;

    @JsonProperty("project_ospo_cost_cop")
    private Long costOSPOCOP;

    @JsonProperty("project_saving_cost_cop")
    private Long costSaving;

    @JsonProperty("project_migration_year")
    private Long year;

    @JsonProperty("project_migration_start")
    private String migrationStart;

    @JsonProperty("project_migration_end")
    private String migrationEnd;

    @JsonProperty("project_architecture")
    private String projectArchitectureType;

    @JsonProperty("project_project_type")
    private String projectType;

    @JsonProperty("project_impact")
    private String projectImpact;

    @JsonProperty("project_effort")
    private String projectEffort;

    @JsonProperty("project_priority")
    private String projectPriority;

    @JsonProperty("project_comments")
    private String comments;

    @JsonProperty("project_status")
    private String projectStatus;

    @JsonProperty("project_evc_participants")
    private String evcParticipants;

    @JsonProperty("project_service_type")
    private String serviceType;

    @JsonProperty("project_saving_cost_usd")
    private String savingCostUSD;

}
