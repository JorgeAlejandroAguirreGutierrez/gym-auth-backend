package co.com.bancolombia.config;

import co.com.bancolombia.model.meetchannel.MeetChannelEntity;
import co.com.bancolombia.model.meetchannel.gateways.MeetChannelRepository;
import co.com.bancolombia.model.meetchannelevent.MeetChannelEventEntity;
import co.com.bancolombia.model.meetchannelevent.gateways.MeetChannelEventRepository;
import co.com.bancolombia.model.metric.MetricEntity;
import co.com.bancolombia.model.metric.gateways.MetricReository;
import co.com.bancolombia.model.project.ProjectEntity;
import co.com.bancolombia.model.project.gateways.ProjectRepository;
import co.com.bancolombia.model.projectdetail.ProjectDetailEntity;
import co.com.bancolombia.model.projectdetail.gateways.ProjectDetailRepository;
import co.com.bancolombia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@ComponentScan(basePackages = "co.com.bancolombia.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

        @Bean
        public MeetChannelRepository meetChannelRepositoryAll(@Autowired MeetChannelR2dbcRepository meetChannelR2dbcRepository) {
                return new MeetChannelRepository() {
                        @Override
                        public Flux<MeetChannelEntity> getMeetChannelAll() {
                                return meetChannelR2dbcRepository.getMeetChannelAll();
                        }

                        @Override
                        public Flux<MeetChannelEntity> getMeetChannelById(int id) {
                                return meetChannelR2dbcRepository.getMeetChannelById(id);
                        }

                        @Override
                        public Mono<MeetChannelEntity> createMeetChannel(MeetChannelEntity meetChannel) {
                                return meetChannelR2dbcRepository.createMeetChannel(meetChannel.getId(),
                                                                                        meetChannel.getName(),
                                                                                        meetChannel.getEventname(),
                                                                                        meetChannel.getAbout(),
                                                                                        meetChannel.getLocation(),
                                                                                        meetChannel.getMembers());
                        }
                };
        }

        @Bean
        public MeetChannelEventRepository meetChannelEventRepositoryAll(@Autowired MeetChannelEventR2dbcRepository meetChannelEventR2dbcRepository) {

                return new MeetChannelEventRepository() {
                        @Override
                        public Flux<MeetChannelEventEntity> getMeetChannelEventAll() {
                                return meetChannelEventR2dbcRepository.getMeetChannelEventAll();
                        }

                        @Override
                        public Flux<MeetChannelEventEntity> getMeetChannelEventById(int id) {
                                return meetChannelEventR2dbcRepository.getMeetChannelEventById(id);
                        }

                        @Override
                        public Mono<MeetChannelEventEntity> createMeetChannelEvent(MeetChannelEventEntity meetChannelEventEntity) {
                                return meetChannelEventR2dbcRepository.createMeetChannelEvent(meetChannelEventEntity.getId(),
                                                                                        meetChannelEventEntity.getMeetchannelid(),
                                                                                        meetChannelEventEntity.getName(),
                                                                                        meetChannelEventEntity.getDetail(),
                                                                                        meetChannelEventEntity.getDate().toString(),
                                                                                        meetChannelEventEntity.getUrlyoutube(),
                                                                                        meetChannelEventEntity.getUrltwitch(),
                                                                                        meetChannelEventEntity.getAssistants(),
                                                                                        Integer.parseInt(meetChannelEventEntity.getState()));
                        }
                };
        }

        @Bean
        public MetricReository metricReository(@Autowired MetricR2dbcRepository metricR2dbcRepository) {

                return new MetricReository() {

                        @Override
                        public Flux<MetricEntity> getAllMetrics() {
                                return metricR2dbcRepository.getMetricAll();
                        }

                        @Override
                        public Flux<MetricEntity> getMetricById(int id) {

                                return metricR2dbcRepository.getMetricById(id);
                        }

                        @Override
                        public Mono<MetricEntity> createMetric(MetricEntity metricEntity) {

                                if(isNumeric(metricEntity.getArea())){
                                        return metricR2dbcRepository.createMetric(metricEntity.getId(),
                                                                                                Integer.parseInt(metricEntity.getArea()),
                                                                                                metricEntity.getName(),
                                                                                                metricEntity.getYear(),
                                                                                                metricEntity.getGoal(),
                                                                                                metricEntity.getCompliance(),
                                                                                                metricEntity.getMeasure());
                                }
                                else{
                                        MetricEntity metricEntityError = MetricEntity.builder()
                                                .messageError("El campo 'Area' debe ser numerico.")
                                                .build();

                                        return Mono.just(metricEntityError);
                                }
                        }
                };

        }

        @Bean
        public ProjectRepository projectReository(@Autowired ProjectR2dbcRepository projectR2dbcRepository) {

                return new ProjectRepository() {

                        @Override
                        public Flux<ProjectEntity> getAllProjects() {
                                return projectR2dbcRepository.getProjectAll();
                        }

                        @Override
                        public Flux<ProjectEntity> getProjectById(int id) {
                                return projectR2dbcRepository.getProjectById(id);
                        }

                        @Override
                        public Mono<ProjectEntity> createProject(ProjectEntity projectEntity) {


                                if(validaCamposTipoNumericosProject(projectEntity)) {
                                        return projectR2dbcRepository.createProject(projectEntity.getId(),
                                                projectEntity.getAwscode(),
                                                projectEntity.getNameprojectlicensed(),
                                                projectEntity.getNameprojectospo(),
                                                projectEntity.getPersonleadospo(),
                                                projectEntity.getNameevc(),
                                                projectEntity.getPersonleadevc(),
                                                projectEntity.getFirstcontactdate(),
                                                projectEntity.getCostlicensedusd(),
                                                projectEntity.getCostlicensedcop(),
                                                projectEntity.getCostospousd(),
                                                projectEntity.getCostospocop(),
                                                projectEntity.getCostsaving(),
                                                projectEntity.getYear(),
                                                projectEntity.getMigrationstart(),
                                                projectEntity.getMigrationend(),
                                                Integer.parseInt(projectEntity.getProjectarchitecturetype()),
                                                Integer.parseInt(projectEntity.getProjecttype()),
                                                Integer.parseInt(projectEntity.getProjectimpact()),
                                                Integer.parseInt(projectEntity.getProjecteffort()),
                                                Integer.parseInt(projectEntity.getProjectpriority()),
                                                projectEntity.getComments(),
                                                Integer.parseInt(projectEntity.getProjectstatus()),
                                                projectEntity.getEvcParticipants(),
                                                projectEntity.getServicetype(),
                                                projectEntity.getSavingcostusd());
                                }
                                else{
                                        ProjectEntity projectEntityError = ProjectEntity.builder()
                                                .messageError("Los campos Projectarchitecturetype," +
                                                                " Projecttype," +
                                                                " Projectimpact," +
                                                                " Projecteffort," +
                                                                " Projectpriority," +
                                                                " Projectstatus deben ser un numericos")
                                                .build();


                                        return Mono.just(projectEntityError);
                                }

                        }
                };
        }

        @Bean
        public ProjectDetailRepository projectDetailReository(@Autowired ProjectDetailR2dbc projectDetailR2dbc) {

                return new ProjectDetailRepository() {

                        @Override
                        public Flux<ProjectDetailEntity> getAllProjectsDetail() {
                                return projectDetailR2dbc.getProjectDetailAll();
                        }

                        @Override
                        public Flux<ProjectDetailEntity> getProjectDetailById(int id) {
                                return projectDetailR2dbc.getProjectDetailById(id);
                        }

                        @Override
                        public Mono<ProjectDetailEntity> createProjectDetail(ProjectDetailEntity projectDetailEntity) {

                                if(validaCamposTipoNumericosProjectDetail(projectDetailEntity)){

                                        return projectDetailR2dbc.createProjectDetail(projectDetailEntity.getId(),
                                                                                        projectDetailEntity.getCostlicensedusd(),
                                                                                        projectDetailEntity.getCostlicensedcop(),
                                                                                        projectDetailEntity.getCostospousd(),
                                                                                        projectDetailEntity.getCostospocop(),
                                                                                        projectDetailEntity.getCostsaving(),
                                                                                        Integer.parseInt(projectDetailEntity.getProjectstatus()),
                                                                                        Integer.parseInt(projectDetailEntity.getProject()),
                                                                                        Integer.parseInt(projectDetailEntity.getProjectenviroment()),
                                                                                        Integer.parseInt(projectDetailEntity.getProjecttypelicensed()),
                                                                                        projectDetailEntity.getProjectsubtypelicensed(),
                                                                                        Integer.parseInt(projectDetailEntity.getProjecttypeospo()),
                                                                                        projectDetailEntity.getProjectsubtypeospo(),
                                                                                        projectDetailEntity.getQuantitymigrated(),
                                                                                        projectDetailEntity.getQuantityobjective(),
                                                                                        projectDetailEntity.getComments(),
                                                                                        projectDetailEntity.getEvc(),
                                                                                        projectDetailEntity.getCostsavingcop());
                                }
                                else{
                                        ProjectDetailEntity projectDetailEntityError = ProjectDetailEntity.builder()
                                                                                        .messageError("Los campos projectstatus," +
                                                                                                        " project," +
                                                                                                        " projectenviroment," +
                                                                                                        " projecttypelicensed," +
                                                                                                        " projecttypeospo deben ser un numericos")
                                                                                        .build();


                                        return Mono.just(projectDetailEntityError);
                                }

                        }
                };

        }

        private boolean validaCamposTipoNumericosProjectDetail(ProjectDetailEntity projectDetailEntity){
                boolean valido = true;

                if(!isNumeric(projectDetailEntity.getProjectstatus())){
                        valido = false;
                }

                if(!isNumeric(projectDetailEntity.getProject())){
                        valido = false;
                }

                if(!isNumeric(projectDetailEntity.getProjectenviroment())){
                        valido = false;
                }

                if(!isNumeric(projectDetailEntity.getProjecttypelicensed())){
                        valido = false;
                }

                if(!isNumeric(projectDetailEntity.getProjecttypeospo())){
                        valido = false;
                }

                return valido;
        }

        private boolean validaCamposTipoNumericosProject(ProjectEntity projectEntity){
                boolean valido = true;

                if(!isNumeric(projectEntity.getProjectstatus())){
                        valido = false;
                }

                if(!isNumeric(projectEntity.getProjectarchitecturetype())){
                        valido = false;
                }

                if(!isNumeric(projectEntity.getProjecttype())){
                        valido = false;
                }

                if(!isNumeric(projectEntity.getProjectimpact())){
                        valido = false;
                }

                if(!isNumeric(projectEntity.getProjecteffort())){
                        valido = false;
                }

                if(!isNumeric(projectEntity.getProjectpriority())){
                        valido = false;
                }

                return valido;
        }

        private boolean isNumeric(String str) {
                return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
        }

}
