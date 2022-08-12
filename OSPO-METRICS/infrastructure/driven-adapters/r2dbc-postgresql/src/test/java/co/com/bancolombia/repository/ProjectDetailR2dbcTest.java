package co.com.bancolombia.repository;

import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
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

class ProjectDetailR2dbcTest {

    @Mock
    ProjectDetailR2dbc projectDetailR2dbc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getProjectDetailAll() {
        ProjectDetailEntity projectDetailRepository = ProjectDetailEntity.builder()
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
        lstEntity.add(projectDetailRepository);
        Flux<ProjectDetailEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(projectDetailR2dbc.getProjectDetailAll()).thenReturn(entityFlux);
        assertNotNull(projectDetailR2dbc.getProjectDetailAll());
    }

    @Test
    void getProjectDetailById() {
        ProjectDetailEntity projectDetailRepository = ProjectDetailEntity.builder()
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
        lstEntity.add(projectDetailRepository);
        Flux<ProjectDetailEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(projectDetailR2dbc.getProjectDetailById(2)).thenReturn(entityFlux);
        assertNotNull(projectDetailR2dbc.getProjectDetailById(2));
    }

    @Test
    void createProjectDetail() {
        ProjectDetailEntity projectDetailRepository = ProjectDetailEntity.builder()
                .id(2)
                .messageError("messageError")
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

        Mono<ProjectDetailEntity> entityFlux = Mono.just(projectDetailRepository);

        when(projectDetailR2dbc.createProjectDetail(
                2,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                1,
                0,
                "projectsubtypelicensed",
                1,
                "projectsubtypeospo",
                0,
                0,
                "comments",
                "evc",
                0
        )).thenReturn(entityFlux);

        assertNotNull(projectDetailR2dbc.createProjectDetail(
                2,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                1,
                0,
                "projectsubtypelicensed",
                1,
                "projectsubtypeospo",
                0,
                0,
                "comments",
                "evc",
                0
        ));
    }
}