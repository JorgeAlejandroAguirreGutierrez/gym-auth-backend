package co.com.bancolombia.model.projectdetail.gateways;

import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
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

class ProjectDetailRepositoryTest {

    @Mock
    ProjectDetailRepository projectDetailRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProjectsDetail() {
        ProjectDetailEntity meetChannelEntity = ProjectDetailEntity.builder()
                .id(2)
                .messageError("messageError")
                .id(2)
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .costsavingcop(0)
                .projectsubtypelicensed("projectsubtypelicensed")
                .projectsubtypeospo("projectsubtypeospo")
                .quantitymigrated(0)
                .quantityobjective(0)
                .comments("comments")
                .projectstatus("projectstatus")
                .project("project")
                .projectenviroment("projectenviroment")
                .projecttypelicensed("projecttypelicensed")
                .projecttypeospo("projecttypeospo")
                .evc("evc")
                .build();

        List<ProjectDetailEntity> lstEntity = new ArrayList<>();
        lstEntity.add(meetChannelEntity);
        Flux<ProjectDetailEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(projectDetailRepository.getAllProjectsDetail()).thenReturn(entityFlux);
        assertNotNull(projectDetailRepository.getAllProjectsDetail());
    }

    @Test
    void getProjectDetailById() {
        ProjectDetailEntity meetChannelEntity = ProjectDetailEntity.builder()
                .id(2)
                .messageError("messageError")
                .id(2)
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .costsavingcop(0)
                .projectsubtypelicensed("projectsubtypelicensed")
                .projectsubtypeospo("projectsubtypeospo")
                .quantitymigrated(0)
                .quantityobjective(0)
                .comments("comments")
                .projectstatus("projectstatus")
                .project("project")
                .projectenviroment("projectenviroment")
                .projecttypelicensed("projecttypelicensed")
                .projecttypeospo("projecttypeospo")
                .evc("evc")
                .build();

        List<ProjectDetailEntity> lstEntity = new ArrayList<>();
        lstEntity.add(meetChannelEntity);
        Flux<ProjectDetailEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(projectDetailRepository.getProjectDetailById(2)).thenReturn(entityFlux);
        assertNotNull(projectDetailRepository.getProjectDetailById(2));
    }

    @Test
    void createProjectDetail() {
        ProjectDetailEntity meetChannelEntity = ProjectDetailEntity.builder()
                .id(2)
                .messageError("messageError")
                .id(2)
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .costsavingcop(0)
                .projectsubtypelicensed("projectsubtypelicensed")
                .projectsubtypeospo("projectsubtypeospo")
                .quantitymigrated(0)
                .quantityobjective(0)
                .comments("comments")
                .projectstatus("projectstatus")
                .project("project")
                .projectenviroment("projectenviroment")
                .projecttypelicensed("projecttypelicensed")
                .projecttypeospo("projecttypeospo")
                .evc("evc")
                .build();

        when(projectDetailRepository.createProjectDetail(meetChannelEntity)).thenReturn(Mono.just(meetChannelEntity));
        assertNotNull(projectDetailRepository.createProjectDetail(meetChannelEntity));
    }
}