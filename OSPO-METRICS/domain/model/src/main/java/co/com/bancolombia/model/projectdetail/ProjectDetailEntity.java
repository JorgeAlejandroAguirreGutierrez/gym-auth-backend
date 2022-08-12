package co.com.bancolombia.model.projectdetail;

import co.com.bancolombia.model.projectstatus.ProjectStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProjectDetailEntity {
    private String messageError;
    private int id;
    private long costlicensedusd;
    private long costlicensedcop;
    private long costospousd;
    private long costospocop;
    private long costsaving;
    private long costsavingcop;
    private String projectsubtypelicensed;
    private String projectsubtypeospo;
    private int quantitymigrated;
    private int quantityobjective;
    private String comments;

    private String projectstatus;
    private String project;
    private String projectenviroment;
    private String projecttypelicensed;
    private String projecttypeospo;
    private String evc;

}
