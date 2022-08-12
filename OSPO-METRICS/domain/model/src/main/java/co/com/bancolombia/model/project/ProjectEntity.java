package co.com.bancolombia.model.project;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectEntity {
    private String messageError;
    private int id;
    private String awscode;
    private String nameprojectlicensed;
    private String nameprojectospo;
    private String personleadospo;
    private String nameevc;
    private String personleadevc;
    private String firstcontactdate;
    private long costlicensedusd;
    private long costlicensedcop;
    private long costospousd;
    private long costospocop;
    private long costsaving;
    private long year;
    private String migrationstart;
    private String migrationend;

    private String projectarchitecturetype;
    private String projecttype;
    private String projectimpact;
    private String projecteffort;
    private String projectpriority;
    private String comments;
    private String projectstatus;

    private String evcParticipants;
    private String servicetype;
    private String savingcostusd;

}
