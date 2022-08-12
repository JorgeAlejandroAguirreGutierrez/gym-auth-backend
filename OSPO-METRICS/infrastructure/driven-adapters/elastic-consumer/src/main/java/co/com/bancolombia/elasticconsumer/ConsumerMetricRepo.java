package co.com.bancolombia.elasticconsumer;

import co.com.bancolombia.elasticconsumer.dto.DocResponseDTO;
import co.com.bancolombia.elasticconsumer.dto.RepoMetricDTO;
import co.com.bancolombia.model.enums.Enums;
import co.com.bancolombia.model.failure.Failure;
import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.model.repo.gateways.RepoMetricService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsumerMetricRepo implements RepoMetricService {
    private final Logger logger = LoggerFactory.getLogger(ConsumerMetricRepo.class);
    private final String SEARCH_INDEX = "/{0}/_search";
    private final String DOCUMENT_INDEX = "/{0}/_doc/{2}";
    @Autowired
    @Qualifier("openSearchClient")
    private WebClient client;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public Mono<Repo> getRepoMetric(Repo repo) {
        return client
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(SEARCH_INDEX)
                        .build(Enums.GithubIndex.Repository.value))
                .bodyValue(getRepoQueryFilter("repo_name", repo.getName()))
                .exchangeToMono(exc -> exc.bodyToMono(DocResponseDTO.class))
                .map(repoRes -> mapDataRepo(repo, repoRes))
                .onErrorMap((ex) -> new Failure(ex.getMessage()));
    }
    @Override
    public Mono<Void> putRepoMetric(Repo repo) {
        return client
                .method(HttpMethod.PUT)
                .uri(uriBuilder -> uriBuilder.path(DOCUMENT_INDEX)
                        .build(Enums.GithubIndex.Repository.value, repo.getId()))
                .bodyValue(getRepoBody(repo))
                .exchange()
                .flatMap(res -> {
                    HttpStatus httpStatus = res.statusCode();
                    if (httpStatus.is2xxSuccessful()) {
                        logger.info("Repo posted successfully");
                    } else {
                        logger.info("Repo posted failed. Status=" + httpStatus);
                    }
                    return res.bodyToMono(String.class);
                })
                .onErrorMap((ex) -> new Failure(ex.getMessage()))
                .then();
    }
    public RepoMetricDTO getRepoBody(Repo repo) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date dateNow = new Date(System.currentTimeMillis());
        return RepoMetricDTO
                .builder()
                .key(date.format(dateNow))
                .repoId(repo.getId())
                .repoName(repo.getName())
                .repoDescription(repo.getDescription())
                .repoOwnerId(repo.getOwnerId())
                .repoOwnerLogin(repo.getOwnerLogin())
                .repoOwnerType(repo.getOwnerType())
                .repoOwnerNodeId(repo.getOwnerNodeId())
                .dateCreated(date.format(repo.getDateCreated()))
                .dateUpdated(date.format(repo.getDateUpdated()))
                .dateProcessed(date.format(repo.getDateProcessed()))
                .dateFrom(date.format(repo.getDateCreated()))
                .dateTo(date.format(repo.getDateProcessed()))
                .licenseKey(date.format(repo.getLicenseKey()))
                .licenseName(date.format(repo.getLicenseName()))
                .licenseSPDXId(date.format(repo.getLicenseSPDXId()))
                .licenseUrl(date.format(repo.getLicenseUrl()))
                .licenseNodeId(date.format(repo.getLicenseNodeId()))
                .build();
    }
    public String getRepoQueryFilter(String docKey, String docValue) {
        ObjectNode query = objectMapper.createObjectNode();
        ObjectNode filterBy = objectMapper.createObjectNode();
        ObjectNode filter = objectMapper.createObjectNode();
        filter.put(docKey, docValue);
        return query
                .putPOJO("query", filterBy.putPOJO("match_phrase_prefix", filter))
                .toString();
    }
    private Repo mapDataRepo(Repo repo, DocResponseDTO docResponseDTO) {
        if(Objects.isNull(docResponseDTO.getError())) {
            if(docResponseDTO.getHits().getHits().length == 1) {
                String dateString = docResponseDTO.getHits().getHits()[0].getSource().get("repo_date_to").asText();
                try {
                    Date dateProcessed = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(dateString);
                    repo.setDateProcessed(dateProcessed);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return repo;
    }
    private String mapDataResult(String string) {
        logger.info("/********************************************************************************************/");
        logger.info("Repo Result {}", string);
        logger.info("/********************************************************************************************/");
        return string;
    }
}
