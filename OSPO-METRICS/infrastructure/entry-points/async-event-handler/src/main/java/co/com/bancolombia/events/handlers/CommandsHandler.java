package co.com.bancolombia.events.handlers;

import co.com.bancolombia.model.repo.Repo;
import co.com.bancolombia.usecase.clones.ClonesMetricUseCase;
import co.com.bancolombia.usecase.clones.ClonesUseCase;
import co.com.bancolombia.usecase.codefrecuency.CodeFrecuencyMetricUseCase;
import co.com.bancolombia.usecase.codefrecuency.CodeFrecuencyUseCase;
import co.com.bancolombia.usecase.collaborator.CollaboratorMetricUseCase;
import co.com.bancolombia.usecase.collaborator.CollaboratorUseCase;
import co.com.bancolombia.usecase.commit.CommitMetricUseCase;
import co.com.bancolombia.usecase.commit.CommitUseCase;
import co.com.bancolombia.usecase.commitactivity.CommitActivityMetricUseCase;
import co.com.bancolombia.usecase.commitactivity.CommitActivityUseCase;
import co.com.bancolombia.usecase.contributor.ContributorMetricUseCase;
import co.com.bancolombia.usecase.contributor.ContributorUseCase;
import co.com.bancolombia.usecase.issue.IssueMetricUseCase;
import co.com.bancolombia.usecase.issue.IssueUseCase;
import co.com.bancolombia.usecase.pull.PullMetricUseCase;
import co.com.bancolombia.usecase.pull.PullUseCase;
import co.com.bancolombia.usecase.repo.RepoMetricUseCase;
import co.com.bancolombia.usecase.views.ViewsMetricUseCase;
import co.com.bancolombia.usecase.views.ViewsUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.impl.config.annotations.EnableCommandListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;

@Log
@AllArgsConstructor
@EnableCommandListeners
public class CommandsHandler {
    private static final Logger logger = LoggerFactory.getLogger(CommandsHandler.class);
    private final ClonesMetricUseCase clonesMetricUseCase;
    private final CodeFrecuencyMetricUseCase codeFrecuencyMetricUseCase;
    private final CollaboratorMetricUseCase collaboratorMetricUseCase;
    private final CommitMetricUseCase commitMetricUseCase;
    private final CommitActivityMetricUseCase commitActivityMetricUseCase;
    private final ContributorMetricUseCase contributorMetricUseCase;
    private final IssueMetricUseCase issueMetricUseCase;
    private final PullMetricUseCase pullMetricUseCase;
    private final RepoMetricUseCase repoMetricUseCase;
    private final ViewsMetricUseCase viewsMetricUseCase;
    private final ClonesUseCase clonesUseCase;
    private final CodeFrecuencyUseCase codeFrecuencyUseCase;
    private final CollaboratorUseCase collaboratorUseCase;
    private final CommitUseCase commitUseCase;
    private final CommitActivityUseCase commitActivityUseCase;
    private final ContributorUseCase contributorUseCase;
    private final IssueUseCase issueUseCase;
    private final PullUseCase pullUseCase;
    private final ViewsUseCase viewsUseCase;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    public Mono<Void> handlerCommandGithubClones(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandDetailGithubClones(command.getData());
    }
    public Mono<Void> handlerCommandGithubCodeFrecuency(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandDetailGithubCodeFrecuency(command.getData());
    }
    public Mono<Void> handlerCommandGithubCollaborator(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandDetailGithubCollaborator(command.getData());
    }
    public Mono<Void> handlerCommandGithubCommit(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandDetailGithubCommit(command.getData());
    }
    public Mono<Void> handlerCommandGithubCommitActivity(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandDetailGithubCommitActivity(command.getData());
    }
    public Mono<Void> handlerCommandGithubContributor(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandDetailGithubContributor(command.getData());
    }
    public Mono<Void> handlerCommandGithubIssues(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandDetailGithubIssues(command.getData());
    }
    public Mono<Void> handlerCommandGithubPulls(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandDetailGithubPulls(command.getData());
    }
    public Mono<Void> handlerCommandGithubRepo(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandGithubRepoDetail(command.getData());
    }
    public Mono<Void> handlerCommandGithubViews(Command<Repo> command) {
        logger.info("Received Command: {}: {} {} {}",
                command.getName(),
                command.getData().getId(),
                command.getData().getOwnerLogin(),
                command.getData().getName());

        return handlerCommandDetailGithubViews(command.getData());
    }
    public Mono<Void> handlerCommandDetailGithubClones(Repo repo) {
        return clonesUseCase.getByRepoName(repo.getOwnerLogin(), repo.getName(), 100)
                .filter(filter -> filter.getData().size() > 0)
                .flatMap(clonesMetricUseCase::putMetric)
                .then();
    }
    public Mono<Void> handlerCommandDetailGithubCodeFrecuency(Repo repo) {
        return codeFrecuencyUseCase.getByRepoName(repo.getOwnerLogin(), repo.getName(), 100)
                .filter(filter -> filter.getData().size() > 0)
                .flatMap(codeFrecuencyMetricUseCase::putMetric)
                .then();
    }
    public Mono<Void> handlerCommandDetailGithubCollaborator(Repo repo) {
        return collaboratorUseCase.getByRepo(repo.getOwnerLogin(), repo.getName(), 100)
                .filter(filter -> filter.getUserId() > 0)
                .flatMap(collaborator -> collaboratorMetricUseCase.putCollaboratorMetric(repo, collaborator))
                .then();
    }
    public Mono<Void> handlerCommandDetailGithubCommit(Repo repo) {
        return commitUseCase.getByRepo(repo.getOwnerLogin(), repo.getName(), 100)
                .filter(filter -> filter.getSha() != "")
                .flatMap(commit -> commitMetricUseCase.putCommitMetric(repo, commit))
                .then();
    }
    public Mono<Void> handlerCommandDetailGithubCommitActivity(Repo repo) {
        return commitActivityUseCase.getByRepo(repo.getOwnerLogin(), repo.getName(), 100)
                .filter(filter -> filter.getId() != "")
                .flatMap(commit -> commitActivityMetricUseCase.putCommitActivityMetric(repo, commit))
                .then();
    }
    public Mono<Void> handlerCommandDetailGithubContributor(Repo repo) {
        return contributorUseCase.getByRepo(repo.getOwnerLogin(), repo.getName(), 100)
                .filter(fil -> fil.getUserId() > 0)
                .flatMap(contributor -> contributorMetricUseCase.putContributorMetric(repo, contributor))
                .then();
    }
    public Mono<Void> handlerCommandDetailGithubIssues(Repo repo) {
        return issueUseCase
                .getByDate(repo.getOwnerLogin(), repo.getName(), dateFormat.format(repo.getDateProcessed()), 100)
                .filter(fil -> fil.getId() > 0)
                .flatMap(issue -> issueMetricUseCase.putIssueMetric(repo, issue))
                .then();
    }
    public Mono<Void> handlerCommandDetailGithubPulls(Repo repo) {
        return pullUseCase
                .getByRepo(repo.getOwnerLogin(), repo.getName(), 100)
                .filter(fil -> fil.getId() > 0)
                .flatMap(pull -> pullMetricUseCase.putPullMetric(repo, pull))
                .then();
    }
    public Mono<Void> handlerCommandGithubRepoDetail(Repo repo) {
        return repoMetricUseCase.putRepoMetric(repo);
    }
    public Mono<Void> handlerCommandDetailGithubViews(Repo repo) {
        return viewsUseCase.getByRepoName(repo.getOwnerLogin(), repo.getName(), 100)
                .filter(filter -> filter.getData().size() > 0)
                .flatMap(viewsMetricUseCase::putViewsMetric)
                .then();
    }
}