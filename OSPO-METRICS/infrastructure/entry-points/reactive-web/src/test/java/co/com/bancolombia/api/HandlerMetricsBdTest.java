package co.com.bancolombia.api;

import co.com.bancolombia.api.constants.RouterPaths;
import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import co.com.bancolombia.model.meetchannel.MeetChannelRequest;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventRequest;
import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.metric.MetricRequest;
import co.com.bancolombia.model.opensearchservice.OpenSearchResponse;
import co.com.bancolombia.model.project.ProjectEntity;
import co.com.bancolombia.model.project.ProjectRequest;
import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
import co.com.bancolombia.model.projectdetail.ProjectDetailRequest;
import co.com.bancolombia.opensearchhelper.OpenSearchMapperHelper;
import co.com.bancolombia.usecase.azuretoken.AzureTokenUseCase;
import co.com.bancolombia.usecase.metricsFromDb.*;
import co.com.bancolombia.usecase.opensearch.OpenSearchUseCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        RouterRest.class,
        Handler.class,
        OpenSearchUseCase.class,
        OpenSearchResponse.class,
        HandlerMetricsBd.class,
        MeetChannelUseCase.class,
        MeetChannelEntity.class,
        MeetChannelRequest.class
})
@WebFluxTest
@Slf4j
class HandlerMetricsBdTest {

    @MockBean
    HandlerMetricsBd handlerMetricsBd;
    @MockBean
    MeetChannelUseCase meetChannelUseCase;
    @MockBean
    MeetChannelEntity meetChannelEntity;
    @MockBean
    MeetChannelRequest MeetChannelRequest;

    @MockBean
    private OpenSearchUseCase openSearch;

    @MockBean
    private AzureTokenUseCase azureService;

    @MockBean
    private OpenSearchMapperHelper helper;

    @MockBean
    MeetChannelEventUseCase meetChannelEventUseCase;

    @MockBean
    MetricUseCase metricUseCase;

    @MockBean
    ProjectUseCase projectUseCase;

    @MockBean
    ProjectDetailUseCase projectDetailUseCase;

    @MockBean
    private HandlerGithubRepo handler;

    @MockBean
    private HandlerGithubIssue handler1;

    @MockBean
    private HandlerGithubCommit handler2;

    @MockBean
    private HandlerGithubPull handler3;

    @MockBean
    private HandlerGithubCollaborator handler4;

    @MockBean
    private HandlerGithubContributor handler5;

    @MockBean
    private HandlerGithubClones handler6;

    @MockBean
    private HandlerGithubViews handler7;

    @MockBean
    private HandlerGithubCodeFrecuency handler8;

    @MockBean
    private HandlerGithubCommitActivity handler9;

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ApplicationContext context;

    @BeforeEach
    public void setUp() {
        webClient = WebTestClient.bindToApplicationContext(context)
                .apply(springSecurity()).build();
    }


    @Test
    void getMeetChannel() {
        int idMeetChannel = 2;

        MeetChannelRequest meetChannelRequest= MeetChannelRequest.builder()
                .id(idMeetChannel)
                .build();

        MeetChannelEntity meetChannelEntity = MeetChannelEntity.builder()
                .id(idMeetChannel)
                .name("Elixir Colombia")
                .eventname("Migration")
                .about("Compartir conocimiento sobre Elixir a través de casos de uso, casos de éxito e iniciativas open source, así como promover la adopción del lenguaje.")
                .location("Medellín, Colombia")
                .members(58)
                .messageError("")
                .build();

        List<MeetChannelEntity> lstMeetChannelEntity = new ArrayList<MeetChannelEntity>();
        lstMeetChannelEntity.add(meetChannelEntity);

        Flux<MeetChannelEntity> meetChannelEntityFlux = Flux.fromIterable(lstMeetChannelEntity);

        Mockito.when(meetChannelUseCase.getMeetChannel(idMeetChannel)).thenReturn(meetChannelEntityFlux);

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(RouterPaths.CONSULT_API_FINANCE_MEET_CHANNEL)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(meetChannelRequest))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(MeetChannelEntity.class);
    }

    @Test
    void createMeetChannelInBdAndOpenSearch() {
        int idMeetChannel = 2;

        //Entity MeetChannelEntity
        MeetChannelEntity meetChannelEntity = MeetChannelEntity.builder()
                .id(idMeetChannel)
                .name("Elixir Colombia")
                .eventname("Migration")
                .about("Compartir conocimiento sobre Elixir a través de casos de uso, casos de éxito e iniciativas open source, así como promover la adopción del lenguaje.")
                .location("Medellín, Colombia")
                .members(58)
                .messageError("")
                .build();

        //When getMeetchannel
        List<MeetChannelEntity> lstMeetChannelEntity = new ArrayList<MeetChannelEntity>();
        lstMeetChannelEntity.add(meetChannelEntity);
        Flux<MeetChannelEntity> meetChannelEntityFlux = Flux.fromIterable(lstMeetChannelEntity);
        Mockito.when(meetChannelUseCase.getMeetChannel(idMeetChannel)).thenReturn(meetChannelEntityFlux);

        //When createMeetChannel
        Mono<MeetChannelEntity> meetChannelEntityMono = Mono.just(meetChannelEntity);
        Mockito.when(meetChannelUseCase.createMeetChannel(meetChannelEntity)).thenReturn(meetChannelEntityMono);

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(RouterPaths.CREATE_API_FINANCE_MEET_CHANNEL)
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(meetChannelEntity))
                .exchange()
                .expectStatus().isOk()
                .expectBody(MeetChannelEntity.class);
    }

    @Test
    void getMeetChannelEvent() {
        int idMeetChannelEvent = 2;

        MeetChannelEventEntity meetChannelEventEntity = MeetChannelEventEntity.builder()
                .id(idMeetChannelEvent)
                .meetchannelid(1)
                .name("La primera herramienta contra la complejidad")
                .detail("detail")
                .date("24/03/2022")
                .urlyoutube("www.youtube.com")
                .urltwitch("www.twitch.com")
                .assistants(201)
                .state("1")
                .messageError("")
                .build();

        List<MeetChannelEventEntity> lstMeetChannelEventEntity = new ArrayList<MeetChannelEventEntity>();
        lstMeetChannelEventEntity.add(meetChannelEventEntity);

        Flux<MeetChannelEventEntity> meetChannelEventEntityFlux = Flux.fromIterable(lstMeetChannelEventEntity);

        Mockito.when(meetChannelEventUseCase.getMeetChannel(idMeetChannelEvent)).thenReturn(meetChannelEventEntityFlux);

        MeetChannelEventRequest meetChannelEventRequest = MeetChannelEventRequest.builder()
                .id(idMeetChannelEvent)
                .build();

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(RouterPaths.CONSULT_API_FINANCE_MEET_CHANNEL_EVENT)
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(meetChannelEventRequest))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MeetChannelEventEntity.class);
    }

    @Test
    void createMeetChannelEventInBdAndOpenSearch() {
        int idMeetChannelEvent = 2;

        //Entity meetChannelEventEntity
        MeetChannelEventEntity meetChannelEventEntity = MeetChannelEventEntity.builder()
                .id(idMeetChannelEvent)
                .meetchannelid(idMeetChannelEvent)
                .name("La primera herramienta contra la complejidad")
                .detail("La primera herramienta contra la complejidad Elixir es un lenguaje que nos presenta varias herramientas para combatir la complejidad en el desarrollo y mantención de software. Sin duda principal entre ellas está el modelo de concurrencia por actores, pero otro factor de suma importancia es la inmutabilidad. La inmutabilidad permite reducir el nivel de complejidad y esfuerzo mental al crear software—pero aún más importante es para mantener software que por definición se considera legado a partir de su creación. Exploraremos las ventajas que brinda la inmutabilidad y por qué es la primera herramienta que trae Elixir contra la complejidad en desarrollo de software.")
                .date("24/03/2022")
                .urlyoutube("www.youtube.com/watch?v=123456")
                .urltwitch("www.twitch.com/123456")
                .assistants(201)
                .state("1")
                .messageError("")
                .build();

        //When getMeetchannel
        List<MeetChannelEventEntity> lstMeetChannelEntity = new ArrayList<MeetChannelEventEntity>();
        lstMeetChannelEntity.add(meetChannelEventEntity);
        Flux<MeetChannelEventEntity> meetChannelEventEntityFlux = Flux.fromIterable(lstMeetChannelEntity);
        Mockito.when(meetChannelEventUseCase.getMeetChannel(idMeetChannelEvent)).thenReturn(meetChannelEventEntityFlux);

        //When createMeetChannel
        Mono<MeetChannelEventEntity> meetChannelEntityMono = Mono.just(meetChannelEventEntity);
        Mockito.when(meetChannelEventUseCase.createMeetChannelEvent(meetChannelEventEntity)).thenReturn(meetChannelEntityMono);

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(RouterPaths.CREATE_API_FINANCE_MEET_CHANNEL_EVENT)
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(meetChannelEventEntity))
                .exchange()
                .expectStatus().isOk()
                .expectBody(MeetChannelEventEntity.class);

    }

    @Test
    void getMeetricFromDB() {
        int idMetric = 2;

        MetricEntity meetChannelEventEntity = MetricEntity.builder()
                .id(idMetric)
                .area("1c")
                .name("Migrar componentes privativos a tecnologías Open Source (BD, WAS, UrbanCode)")
                .year(2022)
                .goal(100)
                .compliance(32)
                .measure("Unit")
                .messageError("")
                .build();

        List<MetricEntity> lstMetricEntity = new ArrayList<MetricEntity>();
        lstMetricEntity.add(meetChannelEventEntity);

        Flux<MetricEntity> meetChannelEventEntityFlux = Flux.fromIterable(lstMetricEntity);

        Mockito.when(metricUseCase.getMetricAllOrById(idMetric)).thenReturn(meetChannelEventEntityFlux);

        MetricRequest meetChannelRequest = MetricRequest.builder()
                .id(idMetric)
                .build();

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(RouterPaths.CONSULT_API_FINANCE_METRIC_FOM_DB)
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(meetChannelRequest))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MetricEntity.class);
    }

    @Test
    void createMetricInBdAndOpenSearch() {
        int idMetric = 2;

        //Entity meetChannelEventEntity
        MetricEntity metricEntity = MetricEntity.builder()
                .id(idMetric)
                .area("1c")
                .name("Migrar componentes privativos a tecnologías Open Source (BD, WAS, UrbanCode)")
                .year(2022)
                .goal(100)
                .compliance(32)
                .measure("Unit")
                .messageError("")
                .build();

        //When getMeetchannel
        List<MetricEntity> lstMeetChannelEntity = new ArrayList<MetricEntity>();
        lstMeetChannelEntity.add(metricEntity);
        Flux<MetricEntity> metricEntityFlux = Flux.fromIterable(lstMeetChannelEntity);
        Mockito.when(metricUseCase.getMetricAllOrById(idMetric)).thenReturn(metricEntityFlux);

        //When createMeetChannel
        Mono<MetricEntity> meetChannelEntityMono = Mono.just(metricEntity);
        Mockito.when(metricUseCase.createMetric(metricEntity)).thenReturn(meetChannelEntityMono);

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(RouterPaths.CREATE_API_FINANCE_METRIC)
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(metricEntity))
                .exchange()
                .expectStatus().isOk()
                .expectBody(MetricEntity.class);
    }

    @Test
    void getProjectFromDB() {
        int idProject = 2;

        ProjectEntity projectEntity = ProjectEntity.builder()
                .id(idProject)
                .awscode("N/E")
                .nameprojectlicensed("WCC2")
                .nameprojectospo("N/E")
                .personleadospo("N/E")
                .nameevc("N/E")
                .personleadevc("N/E")
                .firstcontactdate("10/07/2021")
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .year(2022)
                .migrationstart("")
                .migrationend("")
                .projectarchitecturetype("3")
                .projecttype("3")
                .projectimpact("4")
                .projecteffort("4")
                .projectpriority("4")
                .comments("")
                .projectstatus("6")
                .messageError("")
                .build();

        List<ProjectEntity> lstProjectEntity = new ArrayList<ProjectEntity>();
        lstProjectEntity.add(projectEntity);

        Flux<ProjectEntity> projectEntityFlux = Flux.fromIterable(lstProjectEntity);

        Mockito.when(projectUseCase.getProjectAllOrById(idProject)).thenReturn(projectEntityFlux);

        ProjectRequest meetChannelRequest = ProjectRequest.builder()
                .id(idProject)
                .build();

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(RouterPaths.CONSULT_API_FINANCE_PROJECT_FOM_DB)
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(meetChannelRequest))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProjectEntity.class);
    }

    @Test
    void createProjectInBdAndOpenSearch() {
        int idMetric = 2;

        //Entity meetChannelEventEntity
        ProjectEntity entity = ProjectEntity.builder()
                .id(idMetric)
                .awscode("N/E")
                .nameprojectlicensed("WCC2")
                .nameprojectospo("N/E")
                .personleadospo("N/E")
                .nameevc("N/E")
                .personleadevc("N/E")
                .firstcontactdate("10/07/2021")
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .year(2022)
                .migrationstart("migrationstart")
                .migrationend("migrationend")
                .projectarchitecturetype("3")
                .projecttype("3")
                .projectimpact("4")
                .projecteffort("4")
                .projectpriority("4")
                .comments("comments")
                .projectstatus("6")
                .messageError("")
                .build();

        //When getMeetchannel
        List<ProjectEntity> lstEntity = new ArrayList<ProjectEntity>();
        lstEntity.add(entity);
        Flux<ProjectEntity> entityFlux = Flux.fromIterable(lstEntity);
        Mockito.when(projectUseCase.getProjectAllOrById(idMetric)).thenReturn(entityFlux);

        //When createMeetChannel
        Mono<ProjectEntity> meetChannelEntityMono = Mono.just(entity);
        Mockito.when(projectUseCase.createProject(entity)).thenReturn(meetChannelEntityMono);

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(RouterPaths.CREATE_API_FINANCE_PROJECT)
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(entity))
                .exchange()
                .expectStatus().isOk()
                .expectBody(MetricEntity.class);
    }

    @Test
    void getProjectDetailFromDB() {
        int idProject = 2;

        ProjectDetailEntity projectEntity = ProjectDetailEntity.builder()
                .id(idProject)
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .projectsubtypelicensed("N/E")
                .projectsubtypeospo("N/E")
                .quantitymigrated(0)
                .quantityobjective(0)
                .comments("")
                .projectstatus("N/E")
                .project("N/E")
                .projectenviroment("N/E")
                .projecttypelicensed("N/E")
                .projecttypeospo("N/E")
                .messageError("")
                .build();

        List<ProjectDetailEntity> lstProjectEntity = new ArrayList<ProjectDetailEntity>();
        lstProjectEntity.add(projectEntity);

        Flux<ProjectDetailEntity> projectEntityFlux = Flux.fromIterable(lstProjectEntity);

        Mockito.when(projectDetailUseCase.getProjectDetailAllOrById(idProject)).thenReturn(projectEntityFlux);

        ProjectDetailRequest meetChannelRequest = ProjectDetailRequest.builder()
                .id(idProject)
                .build();


        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(RouterPaths.CONSULT_API_FINANCE_PROJECT_DETAIL_FOM_DB)
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(meetChannelRequest))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProjectDetailEntity.class);
    }

    @Test
    void createProjectDetailInBdAndOpenSearch() {
        int idMetric = 2;

        //Entity meetChannelEventEntity
        ProjectDetailEntity entity = ProjectDetailEntity.builder()
                .id(idMetric)
                .costlicensedusd(0)
                .costlicensedcop(0)
                .costospousd(0)
                .costospocop(0)
                .costsaving(0)
                .projectsubtypelicensed("N/E")
                .projectsubtypeospo("N/E")
                .quantitymigrated(0)
                .quantityobjective(0)
                .comments("")
                .projectstatus("N/E")
                .project("N/E")
                .projectenviroment("N/E")
                .projecttypelicensed("N/E")
                .projecttypeospo("N/E")
                .messageError("")
                .build();

        //When getMeetchannel
        List<ProjectDetailEntity> lstEntity = new ArrayList<ProjectDetailEntity>();
        lstEntity.add(entity);
        Flux<ProjectDetailEntity> entityFlux = Flux.fromIterable(lstEntity);
        Mockito.when(projectDetailUseCase.getProjectDetailAllOrById(idMetric)).thenReturn(entityFlux);

        //When createMeetChannel
        Mono<ProjectDetailEntity> entityMono = Mono.just(entity);
        Mockito.when(projectDetailUseCase.createProjectDetail(entity)).thenReturn(entityMono);

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(RouterPaths.CREATE_API_FINANCE_PROJECT_DETAIL)
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(entity))
                .exchange()
                .expectStatus().isOk()
                .expectBody(MetricEntity.class);
    }

    @Test
    void testGetProjectDetailFromDB() {
        int idMeetChannel = 2;

        MeetChannelRequest meetChannelRequest= MeetChannelRequest.builder()
                .id(idMeetChannel)
                .build();

        MeetChannelEntity meetChannelEntity = MeetChannelEntity.builder()
                .id(idMeetChannel)
                .name("Elixir Colombia")
                .eventname("Migration")
                .about("Compartir conocimiento sobre Elixir a través de casos de uso, casos de éxito e iniciativas open source, así como promover la adopción del lenguaje.")
                .location("Medellín, Colombia")
                .members(58)
                .messageError("")
                .build();

        List<MeetChannelEntity> lstMeetChannelEntity = new ArrayList<MeetChannelEntity>();
        lstMeetChannelEntity.add(meetChannelEntity);

        Flux<MeetChannelEntity> meetChannelEntityFlux = Flux.fromIterable(lstMeetChannelEntity);

        Mockito.when(meetChannelUseCase.getMeetChannel(idMeetChannel)).thenReturn(meetChannelEntityFlux);

        webClient.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(RouterPaths.CONSULT_API_FINANCE_MEET_CHANNEL)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(meetChannelRequest))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(MeetChannelEntity.class);
    }
}