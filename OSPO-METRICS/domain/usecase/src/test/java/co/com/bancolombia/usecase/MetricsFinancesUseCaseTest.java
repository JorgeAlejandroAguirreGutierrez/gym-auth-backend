package co.com.bancolombia.usecase;

import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import co.com.bancolombia.model.meetchannel.gateways.MeetChannelRepository;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.project.ProjectEntity;
import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
import co.com.bancolombia.model.projectdetail.gateways.ProjectDetailRepository;
import co.com.bancolombia.usecase.metricsFromDb.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class MetricsFinancesUseCaseTest {

    @Mock
    MeetChannelRepository meetChannelRepository;

    @Mock
    private MeetChannelUseCase meetChannelUseCase;

    @Mock
    private MeetChannelEventUseCase meetChannelEventUseCase;

    @Mock
    private MetricUseCase metricUseCase;

    @Mock
    private ProjectUseCase projectUseCase;

    @Mock
    private ProjectDetailUseCase projectDetailUseCase;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


    }

    @Test
    void meetChannelUseCaseTest()
    {
        MeetChannelEntity meetChannelEntity = MeetChannelEntity.builder()
                                                .id(2)
                                                .name("Elixir Colombia")
                                                .eventname("Migration")
                                                .about("")
                                                .location("")
                                                .members(0)
                                                .build();

        List<MeetChannelEntity> meetChannelEntityList = new ArrayList<>();
        meetChannelEntityList.add(meetChannelEntity);
        Flux<MeetChannelEntity> meetChannelEntityFlux = Flux.fromIterable(meetChannelEntityList);

        when(meetChannelUseCase.getMeetChannel(2)).thenReturn(meetChannelEntityFlux);

        assertNotNull(meetChannelUseCase.getMeetChannel(2));
        assertEquals("MeetChannelEntity", meetChannelEntityFlux, meetChannelUseCase.getMeetChannel(2));
    }

    @Test
    void meetChannelEventUseCaseTest(){
        MeetChannelEventEntity meetChannelEventEntity = MeetChannelEventEntity.builder()
                                                .id(2)
                                                .meetchannelid(2)
                                                .meetschannelname("Elixir Colombia")
                                                .name("Migration")
                                                .detail("")
                                                .date("")
                                                .urlyoutube("")
                                                .urltwitch("")
                                                .assistants(0)
                                                .state("")
                                                .build();


        List<MeetChannelEventEntity> lstEntity = new ArrayList<>();
        lstEntity.add(meetChannelEventEntity);
        Flux<MeetChannelEventEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(meetChannelEventUseCase.getMeetChannel(2)).thenReturn(entityFlux);

        assertNotNull(meetChannelEventUseCase.getMeetChannel(2));
        assertEquals("meetChannelEventUseCaseTest", entityFlux, meetChannelEventUseCase.getMeetChannel(2));
    }

    @Test
    void metricUseCaseTest(){

        MetricEntity metricEntity = MetricEntity.builder()
                                                .id(2)
                                                .area("")
                                                .name("")
                                                .year(0)
                                                .goal(0)
                                                .compliance(0)
                                                .measure("")
                                                .build();

        List<MetricEntity> lstEntity = new ArrayList<>();
        lstEntity.add(metricEntity);
        Flux<MetricEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(metricUseCase.getMetricAllOrById(2)).thenReturn(entityFlux);

        assertNotNull(metricUseCase.getMetricAllOrById(2));
        assertEquals("metricUseCaseTest", entityFlux, metricUseCase.getMetricAllOrById(2));
    }

    @Test
    void projectUseCaseTest(){
        ProjectEntity projectEntity = ProjectEntity.builder()
                                                .id(2)
                                                .awscode("awscode")
                                                .nameprojectlicensed("nameprojectlicensed")
                                                .nameprojectospo("nameprojectospo")
                                                .personleadospo("personleadospo")
                                                .nameevc("nameevc")
                                                .personleadevc("personleadevc")
                                                .firstcontactdate("firstcontactdate")
                                                .costlicensedusd(122)
                                                .costlicensedcop(5141)
                                                .costospousd(2134)
                                                .costospocop(51251)
                                                .costsaving(51210)
                                                .year(2022)
                                                .migrationstart("migrationstart")
                                                .migrationend("migrationend")
                                                .projectarchitecturetype("projectarchitecturetype")
                                                .projecttype("projecttype")
                                                .projectimpact("projectimpact")
                                                .projecteffort("projecteffort")
                                                .projectpriority("projectpriority")
                                                .comments("comments project Use Case")
                                                .projectstatus("projectstatus")
                                                .evcParticipants("evcParticipants")
                                                .servicetype("servicetype")
                                                .savingcostusd("savingcostusd")
                                                .build();

        List<ProjectEntity> lstEntity = new ArrayList<>();
        lstEntity.add(projectEntity);
        Flux<ProjectEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(projectUseCase.getProjectAllOrById(2)).thenReturn(entityFlux);

        assertNotNull(projectUseCase.getProjectAllOrById(2));
        assertEquals("projectUseCaseTest", entityFlux, projectUseCase.getProjectAllOrById(2));
    }

    @Test
    void projectDetailUseCaseTest(){
        ProjectDetailEntity projectDetailEntity = ProjectDetailEntity.builder()
                                                .id(2)
                                                .messageError("")
                                                .id(2)
                                                .costlicensedusd(122)
                                                .costlicensedcop(5141)
                                                .costospousd(2134)
                                                .costospocop(51251)
                                                .costsaving(51210)
                                                .costsavingcop(51210)
                                                .projectsubtypelicensed("projectsubtypelicensed")
                                                .projectsubtypeospo("projectsubtypeospo")
                                                .quantitymigrated(0)
                                                .quantityobjective(0)
                                                .comments("comments project Use Case")
                                                .projectstatus("projectstatus")
                                                .project("project")
                                                .projectenviroment("projectenviroment")
                                                .projecttypelicensed("projecttypelicensed")
                                                .projecttypeospo("projecttypeospo")
                                                .evc("evc")
                                                .build();

        List<ProjectDetailEntity> lstEntity = new ArrayList<>();
        lstEntity.add(projectDetailEntity);
        Flux<ProjectDetailEntity> entityFlux = Flux.fromIterable(lstEntity);

        when(projectDetailUseCase.getProjectDetailAllOrById(2)).thenReturn(entityFlux);

        assertNotNull(projectDetailUseCase.getProjectDetailAllOrById(2));
        assertEquals("projectDetailUseCaseTest", entityFlux, projectDetailUseCase.getProjectDetailAllOrById(2));
    }

}
