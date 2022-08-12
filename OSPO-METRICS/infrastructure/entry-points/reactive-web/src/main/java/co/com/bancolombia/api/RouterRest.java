package co.com.bancolombia.api;

import co.com.bancolombia.api.constants.RouterPaths;
import co.com.bancolombia.model.azuretoken.AzureToken;
import co.com.bancolombia.model.opensearchservice.OpenSearchRequest;
import co.com.bancolombia.model.opensearchservice.OpenSearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterRest {
    @Bean
    @RouterOperations(value = {
            @RouterOperation(
                    path = RouterPaths.CONSULT_METRICS_BY_NAME,
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.GET,
                    beanClass = Handler.class,
                    beanMethod = "consultByIndexName",
                    operation = @Operation(
                            operationId = "consult-metrics-by-name",
                            parameters = {
                                    @Parameter(
                                            name = "index",
                                            in = ParameterIn.PATH,
                                            required = true,
                                            description = "Index name for ospo-metrics innersource",
                                            content = @Content(schema = @Schema(
                                                    implementation = String.class
                                            ))
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Get all metrics by index name",
                                            content = @Content(schema = @Schema(
                                                    implementation = OpenSearchResponse.class
                                            ))
                                    )
                            },
                            security = @SecurityRequirement(
                                    name = "bearer-key",
                                    scopes = {}
                            )
                    )
            ),
            @RouterOperation(
                    path = RouterPaths.CONSULT_METRICS_FILTER,
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.POST,
                    beanClass = Handler.class,
                    beanMethod = "consultByFilters",
                    operation = @Operation(
                            operationId = "consult-metrics-filter",
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = OpenSearchRequest.class
                                    ))
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Get all metrics by index name using filter query",
                                            content = @Content(schema = @Schema(
                                                    implementation = OpenSearchResponse.class
                                            ))
                                    ),
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Get all metrics by index name",
                                            content = @Content(schema = @Schema(
                                                    implementation = OpenSearchResponse.class
                                            ))
                                    )
                            },
                            security = @SecurityRequirement(
                                    name = "bearer-key",
                                    scopes = {}
                            )
                    )
            ),
            @RouterOperation(
                    path = RouterPaths.CREATE_METRIC,
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.POST,
                    beanClass = Handler.class,
                    beanMethod = "createMetricByIndex",
                    operation = @Operation(
                            operationId = "create-metric",
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = OpenSearchRequest.class
                                    ))
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Create a metric by index name",
                                            content = @Content(schema = @Schema(
                                                    implementation = OpenSearchResponse.class
                                            ))
                                    ),
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Get status code successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = OpenSearchResponse.class
                                            ))
                                    )
                            },
                            security = @SecurityRequirement(
                                    name = "bearer-key",
                                    scopes = {}
                            )
                    )
            ),
            @RouterOperation(
                    path = RouterPaths.UPDATE_METRIC,
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.PUT,
                    beanClass = Handler.class,
                    beanMethod = "updateMetricByIndex",
                    operation = @Operation(
                            operationId = "update-metric",
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = OpenSearchRequest.class
                                    ))
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Update a metric by index name and metric id",
                                            content = @Content(schema = @Schema(
                                                    implementation = OpenSearchResponse.class
                                            ))
                                    ),
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Get status code successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = OpenSearchResponse.class
                                            ))
                                    )
                            },
                            security = @SecurityRequirement(
                                    name = "bearer-key",
                                    scopes = {}
                            )
                    )
            ),
            @RouterOperation(
                    path = RouterPaths.DELETE_METRIC,
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.DELETE,
                    beanClass = Handler.class,
                    beanMethod = "deleteMetricByName",
                    operation = @Operation(
                            operationId = "delete-metric",

                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = OpenSearchRequest.class
                                    ))
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Delete a metric by index name and metric id",
                                            content = @Content(schema = @Schema(
                                                    implementation = OpenSearchResponse.class
                                            ))
                                    ),
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Get status code successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = OpenSearchResponse.class
                                            ))
                                    )
                            },
                            security = @SecurityRequirement(
                                    name = "bearer-key",
                                    scopes = {}
                            )
                    )
            ),
            @RouterOperation(
                    path = RouterPaths.GET_AZURE_TOKEN,
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.GET,
                    beanClass = Handler.class,
                    beanMethod = "getAzureToken",
                    operation = @Operation(
                            operationId = "get-azure-token",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Get Token to consume api rest",
                                            content = @Content(schema = @Schema(
                                                    implementation = AzureToken.class
                                            ))
                                    ),
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Get status code successful",
                                            content = @Content(schema = @Schema(
                                                    implementation = AzureToken.class
                                            ))
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET(RouterPaths.CONSULT_METRICS_BY_NAME), handler::consultByIndexName)
                .andRoute(POST(RouterPaths.CONSULT_METRICS_FILTER)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::consultByFilters)
                .andRoute(POST(RouterPaths.CREATE_METRIC)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::createMetricByIndex)
                .andRoute(PUT(RouterPaths.UPDATE_METRIC)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::updateMetricByIndex)
                .andRoute(DELETE(RouterPaths.DELETE_METRIC)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::deleteMetricByName)
                .andRoute(GET(RouterPaths.GET_AZURE_TOKEN)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::getAzureToken);
    }
    @Bean
    public RouterFunction<ServerResponse> githubClonesEndpoint(HandlerGithubClones handler) {
        return route(GET("/api/github/clones-by-owner/{owner}"), handler::getByRepoOwner)
                .and(route(GET("/api/github/clones-by-repo/{owner}/{name}"), handler::getByRepoName));
    }
    @Bean
    public RouterFunction<ServerResponse> githubCodeFrecuencyEndpoint(HandlerGithubCodeFrecuency handler) {
        return route(GET("/api/github/code-frecuency-by-repo/{owner}/{name}"), handler::getByRepo);
    }
    @Bean
    public RouterFunction<ServerResponse> githubCollaboratorsEndpoint(HandlerGithubCollaborator handler) {
        return route(GET("/api/github/collaborator-by-repo/{owner}/{name}"), handler::getByRepo);
    }
    @Bean
    public RouterFunction<ServerResponse> githubCommitsEndpoint(HandlerGithubCommit handler) {
        return route(GET("/api/github/commits-by-sha/{owner}/{name}/{sha}"), handler::getBySha)
                .and(route(GET("/api/github/commits-by-repo/{owner}/{name}"), handler::getByRepo))
                .and(route(GET("/api/github/commits-by-date/{owner}/{name}"), handler::getByDate));
    }
    @Bean
    public RouterFunction<ServerResponse> githubCommitActivityEndpoint(HandlerGithubCommitActivity handler) {
        return route(GET("/api/github/commit-activity-by-repo/{owner}/{name}"), handler::getByRepo);
    }
    @Bean
    public RouterFunction<ServerResponse> githubContributorsEndpoint(HandlerGithubContributor handler) {
        return route(GET("/api/github/contributor-by-repo/{owner}/{name}"), handler::getByRepo);
    }
    @Bean
    public RouterFunction<ServerResponse> githubIssuesEndpoint(HandlerGithubIssue handler) {
        return route(GET("/api/github/issues-by-number/{owner}/{name}/{number}"), handler::getByNumber)
                .and(route(GET("/api/github/issues-by-repo/{owner}/{name}"), handler::getByRepo))
                .and(route(GET("/api/github/issues-by-date/{owner}/{name}"), handler::getByDate));
    }
    @Bean
    public RouterFunction<ServerResponse> githubPullsEndpoint(HandlerGithubPull handler) {
        return route(GET("/api/github/pulls-by-number/{owner}/{name}/{number}"), handler::getByNumber)
                .and(route(GET("/api/github/pulls-by-repo/{owner}/{name}"), handler::getByRepo));
    }
    @Bean
    public RouterFunction<ServerResponse> githubReposEndpoint(HandlerGithubRepo handler) {
        return route(GET("/api/github/repos-by-name/{owner}/{name}"), handler::getByName)
                .and(route(GET("/api/github/repos-by-owner/{owner}"), handler::getByOwner))
                .and(route(GET("/api/github/repos-by-org/{org}"), handler::getByOrg));
    }
    @Bean
    public RouterFunction<ServerResponse> githubViewsEndpoint(HandlerGithubViews handler) {
        return route(GET("/api/github/views-by-repo/{owner}/{name}"), handler::getByRepo);
    }
    @Bean
    public RouterFunction<ServerResponse> githubMQEndpoint(HandlerGithubRepo handler) {
        return route(GET("/api/github/mq/repos-by-owner/{org}"), handler::doRegisterEventRepo);
    }
    @Bean
    public RouterFunction<ServerResponse> healthDummyOspoMetrics(Handler handler) {
        return route(GET("/api/OSPOMetrics/health-dummy-ospo-metrics"), handler::healthOspoMetrics);
    }
}
