package co.com.bancolombia.api;

import co.com.bancolombia.model.clones.Clones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;

import org.springframework.util.LinkedMultiValueMap;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Clones.class})
@WebFluxTest
public class GithubClonesTest {
    private static final String OWNER = "bancolombia";
    private static final String REPO_NAME = "scaffold-clean-architecture";
    private static final String MIMETYPE = "application/vnd.github.v3+json";
    private static final String USER_AGENT = "bancolombia";
    @Autowired
    private ApplicationContext context;
    @Autowired
    private WebTestClient client;
    private MultiValueMap<String, String> clientHeadersMap;

    @BeforeEach
    public void setUp() {
        client = WebTestClient.bindToApplicationContext(context).build();
        clientHeadersMap = new LinkedMultiValueMap<>();
        clientHeadersMap.add(HttpHeaders.CONTENT_TYPE, MIMETYPE);
        clientHeadersMap.add(HttpHeaders.USER_AGENT, USER_AGENT);
    }
    @Test
    void testGetClonesByRepo() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/github/clones-by-repo/{0}/{1}")
                        .build(OWNER, REPO_NAME))
                .headers(h -> h.addAll(clientHeadersMap))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody(Clones.class);
    }
}
