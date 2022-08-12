package co.com.bancolombia.repository;

import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface ProjectDetailR2dbc extends ReactiveCrudRepository<ProjectDetailEntity, Long> {

    @Query("SELECT  pd.id, pd.costlicensedusd, pd.costlicensedcop, pd.costospousd, pd.costospocop, pd.costsaving, ps.name AS \"projectstatus\", p.nameprojectospo  as \"project\", pe.\"name\" as \"projectenviroment\", pt.\"name\" as  \"projecttypelicensed\", projectsubtypelicensed, pt2.\"name\" as \"projecttypeospo\", projectsubtypeospo, quantitymigrated, quantityobjective, pd.comments, evc, costsavingcop FROM public.projectdetail pd left JOIN public.projectstatus ps ON ps.id = pd.projectstatus left join public.projectenviroment pe on pe.id = pd.projectenviroment left join public.projecttype pt on pt.id = pd.projecttypelicensed left join public.projecttype pt2 on pt2.id = pd.projecttypeospo left join public.project p on p.id = pd.project ")
    public Flux<ProjectDetailEntity> getProjectDetailAll();

    @Query("SELECT  pd.id, pd.costlicensedusd, pd.costlicensedcop, pd.costospousd, pd.costospocop, pd.costsaving, ps.name AS \"projectstatus\", p.nameprojectospo  as \"project\", pe.\"name\" as \"projectenviroment\", pt.\"name\" as  \"projecttypelicensed\", projectsubtypelicensed, pt2.\"name\" as \"projecttypeospo\", projectsubtypeospo, quantitymigrated, quantityobjective, pd.comments, evc, costsavingcop FROM public.projectdetail pd left JOIN public.projectstatus ps ON ps.id = pd.projectstatus left join public.projectenviroment pe on pe.id = pd.projectenviroment left join public.projecttype pt on pt.id = pd.projecttypelicensed left join public.projecttype pt2 on pt2.id = pd.projecttypeospo left join public.project p on p.id = pd.project WHERE pd.id = :id")
    public Flux<ProjectDetailEntity> getProjectDetailById(@Param("id") int id);

    @Query("INSERT INTO public.projectdetail (id, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, projectstatus, project, projectenviroment, projecttypelicensed, projectsubtypelicensed, projecttypeospo, projectsubtypeospo, quantityMigrated, quantityObjective, comments, evc, costsavingcop ) values(:id, :costlicensedusd, :costlicensedcop, :costospousd, :costospocop, :costsaving, :projectstatus, :project, :projectenviroment, :projecttypelicensed, :projectsubtypelicensed, :projecttypeospo, :projectsubtypeospo, :quantitymigrated, :quantityobjective, :comments, :evc, :costsavingcop )")
    public Mono<ProjectDetailEntity> createProjectDetail(@Param("id") int id,
                                                  @Param("costlicensedusd") long costlicensedusd,
                                                  @Param("costlicensedcop") long costlicensedcop,
                                                  @Param("costospousd") long costospousd,
                                                  @Param("costospocop") long costospocop,
                                                  @Param("costsaving") long costsaving,
                                                  @Param("projectstatus") int projectstatus,
                                                  @Param("project") int project,
                                                  @Param("projectenviroment") int projectenviroment,
                                                  @Param("projecttypelicensed") int projecttypelicensed,
                                                  @Param("projectsubtypelicensed") String projectsubtypelicensed,
                                                  @Param("projecttypeospo") int projecttypeospo,
                                                  @Param("projectsubtypeospo") String projectsubtypeospo,
                                                  @Param("quantitymigrated") int quantityMigrated,
                                                  @Param("quantityobjective") int quantityObjective,
                                                  @Param("comments") String comments,
                                                  @Param("evc") String evc,
                                                  @Param("costsavingcop") long costsavingcop);



}

