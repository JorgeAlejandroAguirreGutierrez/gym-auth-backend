package co.com.bancolombia.events;

import co.com.bancolombia.events.handlers.CommandsHandler;
import co.com.bancolombia.model.repo.Repo;
import org.reactivecommons.async.api.HandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerRegistryConfiguration {
    @Bean("handlerRegistryGithubRepo")
    public HandlerRegistry handlerRegistryGithubRepo(CommandsHandler commands) {
        return HandlerRegistry.register()
                .handleCommand("process-Github-Clones",
                        commands::handlerCommandGithubClones, Repo.class)
                .handleCommand("process-Github-CodeFrecuency",
                        commands::handlerCommandGithubCodeFrecuency, Repo.class)
                .handleCommand("process-Github-Collaborator",
                        commands::handlerCommandGithubCollaborator, Repo.class)
                .handleCommand("process-Github-Commit",
                        commands::handlerCommandGithubCommit, Repo.class)
                .handleCommand("process-Github-CommitActivity",
                        commands::handlerCommandGithubCommitActivity, Repo.class)
                .handleCommand("process-Github-Contributor",
                        commands::handlerCommandGithubContributor, Repo.class)
                .handleCommand("process-Github-Issues",
                        commands::handlerCommandGithubIssues, Repo.class)
                .handleCommand("process-Github-Pulls",
                        commands::handlerCommandGithubPulls, Repo.class)
                .handleCommand("process-Github-Repo",
                        commands::handlerCommandGithubRepo, Repo.class)
                .handleCommand("process-Github-Views",
                        commands::handlerCommandGithubViews, Repo.class);
    }
}
