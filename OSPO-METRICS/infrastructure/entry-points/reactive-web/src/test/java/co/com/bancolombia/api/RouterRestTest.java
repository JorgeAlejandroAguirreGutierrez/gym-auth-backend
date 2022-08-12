package co.com.bancolombia.api;

import co.com.bancolombia.api.constants.RouterPaths;
import co.com.bancolombia.model.metric.OpenSearchRequestMetric;
import co.com.bancolombia.model.opensearchservice.OpenSearchRequest;
import co.com.bancolombia.model.opensearchservice.OpenSearchResponse;
import co.com.bancolombia.opensearchhelper.OpenSearchMapperHelper;
import co.com.bancolombia.usecase.azuretoken.AzureTokenUseCase;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RouterRest.class, Handler.class, OpenSearchUseCase.class, OpenSearchResponse.class})
@WebFluxTest
@Slf4j
public class RouterRestTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private OpenSearchUseCase openSearch;
    @MockBean
    private AzureTokenUseCase azureService;
    @MockBean
    private OpenSearchMapperHelper helper;

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
    private WebTestClient client;
    private MultiValueMap<String, String> clientHeadersMap;

    private OpenSearchRequest testRequest;
    private OpenSearchRequestMetric testCreateMetric;

    @BeforeEach
    public void setUp() {
        testRequest = OpenSearchRequest.builder()
                .indexName("open_github_repo").build();

        client = WebTestClient.bindToApplicationContext(context)
                .apply(csrf())
                .apply(springSecurity()).build();
    }

    @Test
    void testConsultMetricByName_unauthorized() {
        Mockito.when(helper.getOpenSearchRequest(Mockito.anyString()))
                .thenReturn(Mono.just(testRequest));

        client.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(RouterPaths.CONSULT_METRICS_BY_NAME)
                        .build("open_github_repo"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void testConsultMetricByName_authorize() {
        Mockito.when(helper.getOpenSearchRequest(Mockito.anyString()))
                .thenReturn(Mono.just(testRequest));
        Mockito.when(openSearch.consultByIndexName(testRequest))
                .thenReturn(Mono.just(OpenSearchResponse.builder().build()));

        client.mutateWith(mockJwt())
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(RouterPaths.CONSULT_METRICS_BY_NAME)
                        .build("open_github_repo"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OpenSearchResponse.class);
    }

    @Test
    void testCreateMetric_authorize() {
        Mockito.when(helper.getOpenSearchRequestMono(Mockito.anyString()))
                .thenReturn(Mono.just(testRequest));
        Mockito.when(openSearch.createMetricByIndex(testRequest))
                .thenReturn(Mono.just(OpenSearchResponse.builder().build()));

        client.mutateWith(mockJwt())
                .mutateWith(csrf())
                .method(HttpMethod.POST)
                .uri(uriBuilder -> uriBuilder
                        .path(RouterPaths.CREATE_METRIC)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OpenSearchResponse.class);
    }

    @Test
    void testUpdateMetric_authorize() {
        Mockito.when(helper.getOpenSearchRequestMono(Mockito.anyString()))
                .thenReturn(Mono.just(testRequest));
        Mockito.when(openSearch.updateMetricByIndex(testRequest))
                .thenReturn(Mono.just(OpenSearchResponse.builder().build()));

        client.mutateWith(mockJwt())
                .mutateWith(csrf())
                .method(HttpMethod.PUT)
                .uri(uriBuilder -> uriBuilder
                        .path(RouterPaths.UPDATE_METRIC)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OpenSearchResponse.class);
    }

    @Test
    void testDeleteMetric_authorize() {
        Mockito.when(helper.getOpenSearchRequestMono(Mockito.anyString()))
                .thenReturn(Mono.just(testRequest));
        Mockito.when(openSearch.deleteMetricByName(testRequest))
                .thenReturn(Mono.just(OpenSearchResponse.builder().build()));

        client.mutateWith(mockJwt())
                .mutateWith(csrf())
                .method(HttpMethod.DELETE)
                .uri(uriBuilder -> uriBuilder
                        .path(RouterPaths.DELETE_METRIC)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OpenSearchResponse.class);
    }
}
