package co.com.bancolombia.events;

import co.com.bancolombia.model.repo.Repo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Level;

@Log
@AllArgsConstructor
@EnableDirectAsyncGateway
public class ReactiveDirectAsyncGateway {
    public static final String TARGET_NAME = "OSPOMetrics";
    public static final String CMD_NAME_CLONES = "process-Github-Clones";
    public static final String CMD_NAME_CODE_FRECUENCY = "process-Github-CodeFrecuency";
    public static final String CMD_NAME_COLLABORATOR = "process-Github-Collaborator";
    public static final String CMD_NAME_COMMIT = "process-Github-Commit";
    public static final String CMD_NAME_COMMIT_ACTIVITY = "process-Github-CommitActivity";
    public static final String CMD_NAME_CONTRIBUTOR = "process-Github-Contributor";
    public static final String CMD_NAME_ISSUES = "process-Github-Issues";
    public static final String CMD_NAME_PULLS = "process-Github-Pulls";
    public static final String CMD_NAME_REPO = "process-Github-Repo";
    public static final String CMD_NAME_VIEWS = "process-Github-Views";
    private final DirectAsyncGateway gateway;
    public Mono<Void> runRemoteJobClones(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_CLONES, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_CLONES, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
    public Mono<Void> runRemoteJobCodeFrecuency(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_CODE_FRECUENCY, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_CODE_FRECUENCY, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
    public Mono<Void> runRemoteJobCollaborator(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_COLLABORATOR, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_COLLABORATOR, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
    public Mono<Void> runRemoteJobCommit(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_COMMIT, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_COMMIT, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
    public Mono<Void> runRemoteJobCommitActivity(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_COMMIT_ACTIVITY, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_COMMIT_ACTIVITY, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
    public Mono<Void> runRemoteJobContributor(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_CONTRIBUTOR, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_CONTRIBUTOR, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
    public Mono<Void> runRemoteJobIssues(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_ISSUES, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_ISSUES, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
    public Mono<Void> runRemoteJobPulls(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_PULLS, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_PULLS, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
    public Mono<Void> runRemoteJobRepo(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_REPO, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_REPO, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
    public Mono<Void> runRemoteJobViews(Repo repo) {
        log.log(Level.INFO, "Sending Command: {0}: {1}", new String[]{ CMD_NAME_VIEWS, repo.toString()});
        return gateway.sendCommand(new Command<>(CMD_NAME_VIEWS, UUID.randomUUID().toString(), repo), TARGET_NAME);
    }
}
