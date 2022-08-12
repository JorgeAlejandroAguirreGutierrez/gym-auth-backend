package co.com.bancolombia.elasticconsumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RepoMetricDTO {
    @JsonProperty("repo_key")
    private String key;
    @JsonProperty("repo_id")
    private long repoId;
    @JsonProperty("repo_name")
    private String repoName;
    @JsonProperty("repo_description")
    private String repoDescription;
    @JsonProperty("repo_owner_id")
    private long repoOwnerId;
    @JsonProperty("repo_owner")
    private String repoOwnerLogin;
    @JsonProperty("repo_owner_type")
    private String repoOwnerType;
    @JsonProperty("repo_owner_node_id")
    private String repoOwnerNodeId;
    @JsonProperty("repo_created")
    private String dateCreated;
    @JsonProperty("repo_updated")
    private String dateUpdated;
    @JsonProperty("repo_processed")
    private String dateProcessed;
    @JsonProperty("repo_date_from")
    private String dateFrom;
    @JsonProperty("repo_date_to")
    private String dateTo;
    @JsonProperty("repo_license_key")
    private String licenseKey;
    @JsonProperty("repo_license_name")
    private String licenseName;
    @JsonProperty("repo_license_spdx_id")
    private String licenseSPDXId;
    @JsonProperty("repo_license_url")
    private String licenseUrl;
    @JsonProperty("repo_license_node")
    private String licenseNodeId;

    @JsonProperty("repo_topics")
    private String[] topics;
    @JsonProperty("repo_language")
    private String language;
    @JsonProperty("repo_size")
    private long size;
    @JsonProperty("repo_has_issues")
    private Boolean hasIssues;
    @JsonProperty("repo_has_projects")
    private Boolean hasProjects;
    @JsonProperty("repo_has_downloads")
    private Boolean hasDownloads;
    @JsonProperty("repo_has_wiki")
    private Boolean hasWiki;
    @JsonProperty("repo_has_pages")
    private Boolean hasPages;

}
