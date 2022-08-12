package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.elasticconsumer.dto.CollaboratorMetricDTO;
import co.com.bancolombia.model.collaborator.Collaborator;
import co.com.bancolombia.model.collaborator.gateways.CollaboratorMetricService;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.repo.Repo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ConsumerMetricCollaborator implements CollaboratorMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricCollaborator.class);
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Override
    public Mono<Void> putCollaboratorMetric(Repo repo, Collaborator collaborator) {
        return client
                .method(HttpMethod.PUT)
                .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                        .build(Enums.GithubIndex.Collaborators.value,
                                repo.getOwnerId() + "." + repo.getId() + "." + collaborator.getUserId()))
                .bodyValue(getMetricCollaborator(repo, collaborator))
                .exchange()
                .flatMap(res -> {
                    HttpStatus httpStatus = res.statusCode();
                    if (httpStatus.is2xxSuccessful()) {
                        logger.info("Collaborator posted successfully");
                    } else {
                        logger.info("Collaborator posted failed. Status=" + httpStatus);
                    }
                    return res.bodyToMono(String.class);
                })
                .map(this::mapDataResult)
                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                .then();
    }
    private CollaboratorMetricDTO getMetricCollaborator(Repo repo, Collaborator collaborator) {
        return CollaboratorMetricDTO
                .builder()
                .repoId(String.valueOf(repo.getId()))
                .repoName(repo.getName())
                .repoOwnerId(repo.getOwnerId())
                .repoOwnerLogin(repo.getOwnerLogin())
                .userId(collaborator.getUserId())
                .userNodeId(collaborator.getUserNodeId())
                .userLogin(collaborator.getUserLogin())
                .userName(collaborator.getUserName())
                .userEmail(collaborator.getUserEmail())
                .userLocation(collaborator.getUserLocation())
                .userAssociation(collaborator.getUserAssociation())
                .userType(collaborator.getUserType())
                .build();
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Result Collaborator {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
