package co.com.bancolombia.repository;

import co.com.bancolombia.model.meetchannel.gateways.MeetChannelRepository;
import co.com.bancolombia.model.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProjectR2dbcRepositoryTest {

    @Mock
    ProjectR2dbcRepository projectR2dbcRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProjectAll() {
        ProjectEntity projectEntity = ProjectEntity.builder()
                .id(2)
                .awscode("awscode")
                .nameprojectlicensed("nameprojectlicensed")
                .nameprojectospo("nameprojectospo")
                .personleadospo("personleadospo")
                .nameevc("nameevc")
                .personleadevc("personleadevc")
                .firstcontactdate("firstcontactdate")
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .year(0)
                .migrationstart("migrationstart")
                .migrationend("migrationend")
                .projectarchitecturetype("projectarchitecturetype")
                .projecttype("projecttype")
                .projectimpact("projectimpact")
                .projecteffort("projecteffort")
                .projectpriority("projectpriority")
                .comments("comments")
                .projectstatus("projectstatus")
                .evcParticipants("evcParticipants")
                .servicetype("servicetype")
                .savingcostusd("savingcostusd")
                .build();

        List<ProjectEntity> lstEntity = new ArrayList<>();
        lstEntity.add(projectEntity);
        Flux<ProjectEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(projectR2dbcRepository.getProjectAll()).thenReturn(entityFlux);
        assertNotNull(projectR2dbcRepository.getProjectAll());
    }

    @Test
    void getProjectById() {
        ProjectEntity projectEntity = ProjectEntity.builder()
                .id(2)
                .awscode("awscode")
                .nameprojectlicensed("nameprojectlicensed")
                .nameprojectospo("nameprojectospo")
                .personleadospo("personleadospo")
                .nameevc("nameevc")
                .personleadevc("personleadevc")
                .firstcontactdate("firstcontactdate")
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .year(0)
                .migrationstart("migrationstart")
                .migrationend("migrationend")
                .projectarchitecturetype("projectarchitecturetype")
                .projecttype("projecttype")
                .projectimpact("projectimpact")
                .projecteffort("projecteffort")
                .projectpriority("projectpriority")
                .comments("comments")
                .projectstatus("projectstatus")
                .evcParticipants("evcParticipants")
                .servicetype("servicetype")
                .savingcostusd("savingcostusd")
                .build();

        List<ProjectEntity> lstEntity = new ArrayList<>();
        lstEntity.add(projectEntity);
        Flux<ProjectEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(projectR2dbcRepository.getProjectById(2)).thenReturn(entityFlux);
        assertNotNull(projectR2dbcRepository.getProjectById(2));
    }

    @Test
    void createProject() {
        ProjectEntity projectEntity = ProjectEntity.builder()
                .id(2)
                .awscode("awscode")
                .nameprojectlicensed("nameprojectlicensed")
                .nameprojectospo("nameprojectospo")
                .personleadospo("personleadospo")
                .nameevc("nameevc")
                .personleadevc("personleadevc")
                .firstcontactdate("firstcontactdate")
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .year(0)
                .migrationstart("migrationstart")
                .migrationend("migrationend")
                .projectarchitecturetype("projectarchitecturetype")
                .projecttype("projecttype")
                .projectimpact("projectimpact")
                .projecteffort("projecteffort")
                .projectpriority("projectpriority")
                .comments("comments")
                .projectstatus("projectstatus")
                .evcParticipants("evcParticipants")
                .servicetype("servicetype")
                .savingcostusd("savingcostusd")
                .build();

        Mono<ProjectEntity> entityFlux = Mono.just(projectEntity);

        when(projectR2dbcRepository.createProject(
                1,
                "awscode",
                "nameprojectlicensed",
                "nameprojectospo",
                "personleadospo",
                "nameevc",
                "personleadevc",
                "firstcontactdate",
                0,
                0,
                0,
                0,
                0,
                2022,
                "migrationstart",
                "migrationend",
                1,
                1,
                1,
                1,
                1,
                "comments",
                1,
                "evcParticipants",
                "servicetype",
                ""
        )).thenReturn(Mono.just(projectEntity));

        assertNotNull(projectR2dbcRepository.createProject(
                1,
                "awscode",
                "nameprojectlicensed",
                "nameprojectospo",
                "personleadospo",
                "nameevc",
                "personleadevc",
                "firstcontactdate",
                0,
                0,
                0,
                0,
                0,
                2022,
                "migrationstart",
                "migrationend",
                1,
                1,
                1,
                1,
                1,
                "comments",
                1,
                "evcParticipants",
                "servicetype",
                ""
        ));
    }
}