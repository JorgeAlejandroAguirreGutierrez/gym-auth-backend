package co.com.bancolombia.api;

import co.com.bancolombia.api.constants.RouterPaths;
import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import co.com.bancolombia.model.meetchannel.MeetChannelRequest;
import co.com.bancolombia.model.meetchannel.OpenSearchRequestMeetChannel;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventRequest;
import co.com.bancolombia.model.meetchannelevent.OpenSearchRequestMeetChannelEvent;
import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.metric.MetricRequest;
import co.com.bancolombia.model.metric.OpenSearchRequestMetric;
import co.com.bancolombia.model.project.OpenSearchRequestProject;
import co.com.bancolombia.model.project.ProjectEntity;
import co.com.bancolombia.model.project.ProjectRequest;
import co.com.bancolombia.model.projectdetail.OpenSearchRequestProjectDetail;
import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
import co.com.bancolombia.model.projectdetail.ProjectDetailRequest;
import co.com.bancolombia.opensearchhelper.Constants;
import co.com.bancolombia.usecase.metricsFromDb.*;
import co.com.bancolombia.usecase.opensearch.OpenSearchUseCase;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@RestController
@Configuration
public class HandlerMetricsBd {

    private static final Logger logger = LoggerFactory.getLogger(Handler.class);

    private final MeetChannelUseCase meetChannelUseCase;

    private final MeetChannelEventUseCase meetChannelEventUseCase;

    private final MetricUseCase metricUseCase;

    private final ProjectUseCase projectUseCase;

    private final ProjectDetailUseCase projectDetailUseCase;

    private final OpenSearchUseCase openSearch;


    @PostMapping(path = RouterPaths.CONSULT_API_FINANCE_MEET_CHANNEL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<MeetChannelEntity> getMeetChannel(@RequestBody String body) {
        Gson gson = new Gson();
        MeetChannelRequest meetChannelRequest = gson.fromJson(body, MeetChannelRequest.class);

        MeetChannelEntity meetChannelEntityNotFound = MeetChannelEntity.builder()
                .id(0)
                .name("")
                .about("")
                .location("")
                .members(0)
                .messageError(Constants.MSG_GENERIC_NO_ENCONTRO_DATOS_CON_EL_ID_ESPECIFICADO)
                .build();

        return meetChannelUseCase.getMeetChannel(meetChannelRequest.getId())
                .switchIfEmpty(Mono.just(meetChannelEntityNotFound));
    }


    /***
     * Create metrics record in data base and publish in opensearch.
     * @param body
     * @return
     */
    @PostMapping(path = RouterPaths.CREATE_API_FINANCE_MEET_CHANNEL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<MeetChannelEntity> createMeetChannelInBdAndOpenSearch(@RequestBody String body) {
        Gson gson = new Gson();
        MeetChannelEntity meetChannelEntity = gson.fromJson(body, MeetChannelEntity.class);
        Mono<MeetChannelEntity> meetChannelMonoResultado;

        logger.info("Id para validacion: " + meetChannelEntity.getId());

        //se valida existencia del id especificado
        Flux<MeetChannelEntity> meetChannelFlux = meetChannelUseCase.getMeetChannel(meetChannelEntity.getId());

        return meetChannelFlux
                .collectList()
                .flatMap(l -> {
                    Mono<MeetChannelEntity> meetChannelMono;

                    //validate if already exist record in data base
                    if(l.isEmpty()) {
                        //to build the request to open search
                        OpenSearchRequestMeetChannel openSearchRequestMeetChannel = OpenSearchRequestMeetChannel.builder()
                                .indexName(Constants.MEET_CHANNEL_INDEX)
                                .metricMeetChannel(meetChannelEntity)
                                .build();

                        //insert in data base meetchannel
                        logger.info("Init create metric recording..." + meetChannelEntity.toString());
                        meetChannelMono = meetChannelUseCase.createMeetChannel(meetChannelEntity);
                        logger.info("Save in data base is successfully..." + meetChannelEntity.toString());

                        //save in open search meetchanneel
                        openSearch.createMetricByIndexMeetChannel(openSearchRequestMeetChannel).subscribe(
                                response -> {
                                    logger.info("Response from open search: " + response.toString());
                                }
                        );

                        return meetChannelMono;
                    }
                    else {
                        MeetChannelEntity meetChannelEntityResult = MeetChannelEntity.builder()
                                                                    .messageError(Constants.MSG_GENERIC_ID_YA_EXITE_O_ID_INCORRECTO)
                                                                    .build();

                        return meetChannelMono = Mono.just(meetChannelEntityResult);
                    }
                }).switchIfEmpty(Mono.just(meetChannelEntity));
    }

    @PostMapping(path = RouterPaths.CONSULT_API_FINANCE_MEET_CHANNEL_EVENT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<MeetChannelEventEntity> getMeetChannelEvent(@RequestBody String body) {
        Gson gson = new Gson();
        MeetChannelEventRequest meetChannelEventRequest = gson.fromJson(body, MeetChannelEventRequest.class);

        MeetChannelEventEntity meetChannelEventEntityNotFound = MeetChannelEventEntity.builder()
                .messageError(Constants.MSG_GENERIC_NO_ENCONTRO_DATOS_CON_EL_ID_ESPECIFICADO)
                .build();

        return meetChannelEventUseCase.getMeetChannel(meetChannelEventRequest.getId())
                .switchIfEmpty(Mono.just(meetChannelEventEntityNotFound));
    }

    /***
     * Create metrics meetchannelevent record in data base and publish in opensearch.
     * @param body
     * @return
     */
    @PostMapping(path = RouterPaths.CREATE_API_FINANCE_MEET_CHANNEL_EVENT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<MeetChannelEventEntity> createMeetChannelEventInBdAndOpenSearch(@RequestBody String body) {
        Gson gson = new Gson();
        MeetChannelEventEntity meetChannelEventEntity = gson.fromJson(body, MeetChannelEventEntity.class);
        Mono<MeetChannelEventEntity> meetChannelEventMonoResultado;

        logger.info("Id para validacion: " + meetChannelEventEntity.getId());

        //se valida existencia del id especificado
        Flux<MeetChannelEventEntity> meetChannelFlux = meetChannelEventUseCase.getMeetChannel(meetChannelEventEntity.getId());

        return meetChannelFlux
                .collectList()
                .flatMap(l -> {
                    Mono<MeetChannelEventEntity> meetChannelMonoEvent;

                    //validate if already exist record in data base
                    if(l.isEmpty()) {
                        //to build the request to open search
                        OpenSearchRequestMeetChannelEvent openSearchRequestMeetChannelEvent = OpenSearchRequestMeetChannelEvent.builder()
                                .indexName(Constants.MEET_CHANNEL_EVENT_INDEX)
                                .meetChannelEventEntity(meetChannelEventEntity)
                                .build();

                        //insert in data base meetchannel
                        logger.info("Init create metric recording..." + meetChannelEventEntity.toString());
                        meetChannelMonoEvent = meetChannelEventUseCase.createMeetChannelEvent(meetChannelEventEntity);
                        logger.info("Save in data base is successfully..." + meetChannelEventEntity.toString());

                        //save in open search meetchanneel
                        openSearch.createMetricByIndexMeetChannelEvent(openSearchRequestMeetChannelEvent).subscribe(
                                response -> {
                                    logger.info("Response from open search: " + response.toString());
                                }
                        );

                        return meetChannelMonoEvent;
                    }
                    else {
                        MeetChannelEventEntity meetChannelEventEntityResult = MeetChannelEventEntity.builder()
                                .messageError(Constants.MSG_GENERIC_ID_YA_EXITE_O_ID_INCORRECTO)
                                .build();

                        return meetChannelMonoEvent = Mono.just(meetChannelEventEntityResult);
                    }
                }).switchIfEmpty(Mono.just(meetChannelEventEntity));
    }

    @PostMapping(path = RouterPaths.CONSULT_API_FINANCE_METRIC_FOM_DB, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<MetricEntity> getMeetricFromDB(@RequestBody String body) {
        Gson gson = new Gson();
        MetricRequest metricRequest = gson.fromJson(body, MetricRequest.class);

        MetricEntity metricEntityNotFound = MetricEntity.builder()
                .messageError(Constants.MSG_GENERIC_NO_ENCONTRO_DATOS_CON_EL_ID_ESPECIFICADO)
                .build();

        return metricUseCase.getMetricAllOrById(metricRequest.getId())
                .switchIfEmpty(Mono.just(metricEntityNotFound));
    }

    /***
     * Create metrics "metric" record in data base and publish in opensearch.
     * @param body
     * @return
     */
    @PostMapping(path = RouterPaths.CREATE_API_FINANCE_METRIC, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<MetricEntity> createMetricInBdAndOpenSearch(@RequestBody String body) {
        Gson gson = new Gson();
        MetricEntity metricEntity = gson.fromJson(body, MetricEntity.class);
        Mono<MetricEntity> metricMonoResultado;

        logger.info("Id para validacion: " + metricEntity.getId());

        //consul records with the id
        Flux<MetricEntity> metricFlux = metricUseCase.getMetricAllOrById(metricEntity.getId());

        return metricFlux
                .collectList()
                .flatMap(l -> {
                    Mono<MetricEntity> metricEntityMono;

                    //validate if already exist record in data base
                    if(l.isEmpty()) {
                        //to build the request to open search
                        OpenSearchRequestMetric openSearchRequestMetric = OpenSearchRequestMetric.builder()
                                .indexName(Constants.METRIC_INDEX)
                                .metricEntity(metricEntity)
                                .build();

                        //insert in data base meetchannel
                        logger.info("Init create metric recording..." + metricEntity.toString());
                        metricEntityMono = metricUseCase.createMetric(metricEntity);
                        logger.info("Save in data base is successfully..." + metricEntity.toString());

                        //save in open search meetchanneel
                        openSearch.createMetricByIndexMetric(openSearchRequestMetric).subscribe(
                                response -> {
                                    logger.info("Response from open search: " + response.toString());
                                }
                        );

                        return metricEntityMono;
                    }
                    else {
                        MetricEntity metricEntityResult = MetricEntity.builder()
                                .messageError(Constants.MSG_GENERIC_ID_YA_EXITE_O_ID_INCORRECTO)
                                .build();

                        return metricEntityMono = Mono.just(metricEntityResult);
                    }
                }).switchIfEmpty(Mono.just(metricEntity));
    }

    @PostMapping(path = RouterPaths.CONSULT_API_FINANCE_PROJECT_FOM_DB, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ProjectEntity> getProjectFromDB(@RequestBody String body) {
        Gson gson = new Gson();
        ProjectRequest projectRequest = gson.fromJson(body, ProjectRequest.class);

        ProjectEntity projectEntityNotFound = ProjectEntity.builder()
                .messageError(Constants.MSG_GENERIC_NO_ENCONTRO_DATOS_CON_EL_ID_ESPECIFICADO)
                .build();

        return projectUseCase.getProjectAllOrById(projectRequest.getId())
                .switchIfEmpty(Mono.just(projectEntityNotFound));
    }

    /***
     * Create metrics "project" record in data base and publish in opensearch.
     * @param body
     * @return
     */
    @PostMapping(path = RouterPaths.CREATE_API_FINANCE_PROJECT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ProjectEntity> createProjectInBdAndOpenSearch(@RequestBody String body) {
        Gson gson = new Gson();
        ProjectEntity projectEntity = gson.fromJson(body, ProjectEntity.class);
        Mono<MetricEntity> projectMonoResultado;

        logger.info("Id para validacion: " + projectEntity.getId());

        //consul records with the id
        Flux<ProjectEntity> projectFlux = projectUseCase.getProjectAllOrById(projectEntity.getId());

        return projectFlux
                .collectList()
                .flatMap(l -> {
                    Mono<ProjectEntity> projectEntityMono;

                    //validate if already exist record in data base
                    if(l.isEmpty()) {
                        //to build the request to open search
                        OpenSearchRequestProject openSearchRequestProject = OpenSearchRequestProject.builder()
                                .indexName(Constants.PROJECT_INDEX)
                                .projectEntity(projectEntity)
                                .build();

                        //insert in data base meetchannel
                        logger.info("Init create metric recording..." + projectEntity.toString());
                        projectEntityMono = projectUseCase.createProject(projectEntity);
                        logger.info("Save in data base is successfully..." + projectEntity.toString());

                        //save in open search meetchanneel
                        openSearch.createMetricByIndexProject(openSearchRequestProject).subscribe(
                                response -> {
                                    logger.info("Response from open search: " + response.toString());
                                }
                        );

                        return projectEntityMono;
                    }
                    else {
                        ProjectEntity projectEntityResult = ProjectEntity.builder()
                                .messageError(Constants.MSG_GENERIC_ID_YA_EXITE_O_ID_INCORRECTO)
                                .build();

                        return projectEntityMono = Mono.just(projectEntityResult);
                    }
                }).switchIfEmpty(Mono.just(projectEntity));
    }

    @PostMapping(path = RouterPaths.CONSULT_API_FINANCE_PROJECT_DETAIL_FOM_DB, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ProjectDetailEntity> getProjectDetailFromDB(@RequestBody String body) {
        Gson gson = new Gson();
        ProjectDetailRequest projectRequest = gson.fromJson(body, ProjectDetailRequest.class);

        ProjectDetailEntity projectDetailEntityNotFound = ProjectDetailEntity.builder()
                .messageError(Constants.MSG_GENERIC_NO_ENCONTRO_DATOS_CON_EL_ID_ESPECIFICADO)
                .build();

        return projectDetailUseCase.getProjectDetailAllOrById(projectRequest.getId())
                .switchIfEmpty(Mono.just(projectDetailEntityNotFound));
    }

    /***
     * Create metrics "project detail" record in data base and publish in opensearch.
     * @param body
     * @return
     */
    @PostMapping(path = RouterPaths.CREATE_API_FINANCE_PROJECT_DETAIL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ProjectDetailEntity> createProjectDetailInBdAndOpenSearch(@RequestBody String body) {
        Gson gson = new Gson();
        ProjectDetailEntity projectDetailEntity = gson.fromJson(body, ProjectDetailEntity.class);
        Mono<ProjectEntity> projectDetailMonoResultado;

        logger.info("Id para validacion: " + projectDetailEntity.getId());

        //consul records with the id
        Flux<ProjectDetailEntity> projectFlux = projectDetailUseCase.getProjectDetailAllOrById(projectDetailEntity.getId());

        return projectFlux
                .collectList()
                .flatMap(l -> {
                    Mono<ProjectDetailEntity> projectDetailEntityMono;

                    //validate if already exist record in data base
                    if(l.isEmpty()) {
                        //to build the request to open search
                        OpenSearchRequestProjectDetail openSearchRequestProjectDetail = OpenSearchRequestProjectDetail.builder()
                                .indexName(Constants.PROJECT_DETAIL_INDEX_)
                                .projectDetailEntity(projectDetailEntity)
                                .build();

                        //insert in data base meetchannel
                        logger.info("Init create metric recording..." + projectDetailEntity.toString());
                        projectDetailEntityMono = projectDetailUseCase.createProjectDetail(projectDetailEntity);
                        logger.info("Save in data base is successfully..." + projectDetailEntity.toString());

                        //save in open search meetchanneel
                        openSearch.createMetricByIndexProjectDetail(openSearchRequestProjectDetail).subscribe(
                                response -> {
                                    logger.info("Response from open search: " + response.toString());
                                }
                        );

                        return projectDetailEntityMono;
                    }
                    else {
                        ProjectDetailEntity projectDetailEntityResult = ProjectDetailEntity.builder()
                                .messageError(Constants.MSG_GENERIC_ID_YA_EXITE_O_ID_INCORRECTO)
                                .build();

                        return projectDetailEntityMono = Mono.just(projectDetailEntityResult);
                    }
                }).switchIfEmpty(Mono.just(projectDetailEntity));
    }

    /***
     * Migration all services data from data base to open search.
     * @return
     */
    @GetMapping(path = RouterPaths.API_FINANCE_ALL_MIGRATION_DB_TO_OPENSEARCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProjectDetailFromDB() {

        meetChannelUseCase.getMeetChannel(0)
                .delayElements(Duration.ofSeconds(3))
                .map(x->{

                    //to build the request to open search
                    OpenSearchRequestMeetChannel openSearchRequestMeetChannel = OpenSearchRequestMeetChannel.builder()
                            .indexName(Constants.MEET_CHANNEL_INDEX)
                            .metricMeetChannel(x)
                            .build();

                    //save in open search meetchanneel
                    openSearch.createMetricByIndexMeetChannel(openSearchRequestMeetChannel).subscribe(
                            response -> {
                                logger.info("Response from open search: " + response.toString());
                            }
                    );

                    return x;
                }).subscribe();


        meetChannelEventUseCase.getMeetChannel(0)
                .delayElements(Duration.ofSeconds(3))
                .map(x->{
                    //to build the request to open search
                    OpenSearchRequestMeetChannelEvent openSearchRequestMeetChannelEvent = OpenSearchRequestMeetChannelEvent.builder()
                            .indexName(Constants.MEET_CHANNEL_EVENT_INDEX)
                            .meetChannelEventEntity(x)
                            .build();

                    //save in open search meetchanneel event
                    openSearch.createMetricByIndexMeetChannelEvent(openSearchRequestMeetChannelEvent).subscribe(
                            response -> {
                                logger.info("Response from open search: " + response.toString());
                            }
                    );

                    return x;
                }
                ).subscribe();


        metricUseCase.getMetricAllOrById(0)
                .delayElements(Duration.ofSeconds(3))
                .map(x->{
                    //to build the request to open search
                    OpenSearchRequestMetric openSearchRequestMetric = OpenSearchRequestMetric.builder()
                            .indexName(Constants.METRIC_INDEX)
                            .metricEntity(x)
                            .build();

                    //save in open search meetchanneel
                    openSearch.createMetricByIndexMetric(openSearchRequestMetric).subscribe(
                            response -> {
                                logger.info("Response from open search: " + response.toString());
                            });

                    return x;
                }).subscribe();

        //project
        projectUseCase.getProjectAllOrById(0)
                .delayElements(Duration.ofSeconds(3))
                .map(x->{

                    //to build the request to open search
                    OpenSearchRequestProject openSearchRequestProject = OpenSearchRequestProject.builder()
                            .indexName(Constants.PROJECT_INDEX)
                            .projectEntity(x)
                            .build();

                    openSearch.createMetricByIndexProject(openSearchRequestProject).subscribe(
                            response -> {
                                logger.info("Response from open search: " + response.toString());
                            }
                    );


                    return x;
                }).subscribe();

        //project detail
        projectDetailUseCase.getProjectDetailAllOrById(0)
                .delayElements(Duration.ofSeconds(3))
                .map(x->{
                    //to build the request to open search
                    OpenSearchRequestProjectDetail openSearchRequestProjectDetail = OpenSearchRequestProjectDetail.builder()
                            .indexName(Constants.PROJECT_DETAIL_INDEX_)
                            .projectDetailEntity(x)
                            .build();

                    openSearch.createMetricByIndexProjectDetail(openSearchRequestProjectDetail).subscribe(
                            response -> {
                                logger.info("Response from open search: " + response.toString());
                            }
                    );

                    return x;
                }).subscribe();


        return "Migration successfully";
    }


}
