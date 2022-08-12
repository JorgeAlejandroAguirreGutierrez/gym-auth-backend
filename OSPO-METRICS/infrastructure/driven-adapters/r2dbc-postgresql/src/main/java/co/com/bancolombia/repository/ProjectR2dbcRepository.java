package co.com.bancolombia.repository;

import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.project.ProjectEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface ProjectR2dbcRepository  extends ReactiveCrudRepository<ProjectEntity, Long> {

    @Query("SELECT  p.id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, year, migrationstart, migrationend, pa.\"name\" as \"projectarchitecturetype\", pt.\"name\" as \"projecttype\", pim.\"name\" as \"projectimpact\", pe.\"name\" as \"projecteffort\", pp.\"name\" as \"projectpriority\", pes.\"name\" as \"projectstatus\", comments, evcParticipants, servicetype, savingcostusd FROM public.project p left join public.projectarchitecturetype pa on pa.id = p.projectarchitecturetype left join public.projecttype pt on pt.id = p.projecttype left join public.projectimpact pim on pim.id = p.projectimpact left join public.projecteffort pe on pe.id = p.projecteffort left join public.projectpriority pp on pp.id = p.projectpriority left join public.projectstatus pes on pes.id = p.projectstatus")
    public Flux<ProjectEntity> getProjectAll();

    @Query("SELECT  p.id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, year, migrationstart, migrationend, pa.\"name\" as \"projectarchitecturetype\", pt.\"name\" as \"projecttype\", pim.\"name\" as \"projectimpact\", pe.\"name\" as \"projecteffort\", pp.\"name\" as \"projectpriority\", pes.\"name\" as \"projectstatus\", comments, evcParticipants, servicetype, savingcostusd FROM public.project p left join public.projectarchitecturetype pa on pa.id = p.projectarchitecturetype left join public.projecttype pt on pt.id = p.projecttype left join public.projectimpact pim on pim.id = p.projectimpact left join public.projecteffort pe on pe.id = p.projecteffort left join public.projectpriority pp on pp.id = p.projectpriority left join public.projectstatus pes on pes.id = p.projectstatus WHERE p.id=:idproject")
    public Flux<ProjectEntity> getProjectById(@Param("idproject") int id);

    @Query("INSERT INTO public.project (id, awscode, nameprojectlicensed, nameprojectospo, personleadospo, nameevc, personleadevc, firstcontactdate, costlicensedusd, costlicensedcop, costospousd, costospocop, costsaving, \"year\", migrationstart, migrationend, projectarchitecturetype, projecttype, projectimpact, projecteffort, projectpriority, projectstatus, \"comments\", evcParticipants, servicetype, savingcostusd) VALUES(:id, :awscode, :nameprojectlicensed, :nameprojectospo, :personleadospo, :nameevc, :personleadevc, :firstcontactdate, :costlicensedusd, :costlicensedcop, :costospousd, :costospocop, :costsaving, :year, :migrationstart, :migrationend, :projectarchitecturetype, :projecttype, :projectimpact, :projecteffort, :projectpriority, :projectstatus, :comments, :evcParticipants, :servicetype, :savingcostusd)")
    public Mono<ProjectEntity> createProject(@Param("id") int id,
                                            @Param("awscode") String awscode,
                                            @Param("nameprojectlicensed") String nameprojectlicensed,
                                            @Param("nameprojectospo") String nameprojectospo,
                                            @Param("personleadospo") String personleadospo,
                                            @Param("nameevc") String nameevc,
                                            @Param("personleadevc") String personleadevc,
                                            @Param("firstcontactdate") String firstcontactdate,
                                            @Param("costlicensedusd") long costlicensedusd,
                                            @Param("costlicensedcop") long costlicensedcop,
                                            @Param("costospousd") long costospousd,
                                            @Param("costospocop") long costospocop,
                                            @Param("costsaving") long costsaving,
                                            @Param("year") long year,
                                            @Param("migrationstart") String migrationstart,
                                            @Param("migrationend") String migrationend,
                                            @Param("projectarchitecturetype") int projectarchitecturetype,
                                            @Param("projecttype") int projecttype,
                                            @Param("projectimpact") int projectimpact,
                                            @Param("projecteffort") int projecteffort,
                                            @Param("projectpriority") int projectpriority,
                                            @Param("comments") String comments,
                                            @Param("projectstatus") int projectstatus,
                                             @Param("evcParticipants") String evcParticipants,
                                             @Param("servicetype") String servicetype,
                                             @Param("savingcostusd") String savingcostusd);




}
