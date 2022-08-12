package co.com.bancolombia.model.project.gateways;

import co.com.bancolombia.model.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class ProjectRepositoryTest {

    @Mock
    ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProjects() {
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

        List<ProjectEntity> projectEntityList = new ArrayList<>();
        projectEntityList.add(projectEntity);
        Flux<ProjectEntity> projectEntityFlux = Flux.fromIterable(projectEntityList);

        when(projectRepository.getAllProjects()).thenReturn(projectEntityFlux);

        assertNotNull(projectRepository.getAllProjects());
        assertEquals("ProjectEntity", projectEntityFlux, projectRepository.getAllProjects());
    }

    @Test
    void getProjectById() {
        ProjectEntity meetChannelEntity = ProjectEntity.builder()
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
        lstEntity.add(meetChannelEntity);
        Flux<ProjectEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(projectRepository.getProjectById(2)).thenReturn(entityFlux);
        assertNotNull(projectRepository.getProjectById(2));
        assertEquals("ProjectEntity", projectRepository.getProjectById(2), projectRepository.getProjectById(2));
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

        when(projectRepository.createProject(projectEntity)).thenReturn(entityFlux);
        assertNotNull(projectRepository.createProject(projectEntity));
        assertEquals("ProjectEntity", projectRepository.createProject(projectEntity), projectRepository.createProject(projectEntity));
    }

}