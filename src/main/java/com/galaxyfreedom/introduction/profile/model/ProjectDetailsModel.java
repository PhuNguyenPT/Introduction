package com.galaxyfreedom.introduction.profile.model;

import com.galaxyfreedom.introduction.profile.enums.ProjectType;
import com.galaxyfreedom.introduction.profile.enums.Status;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class ProjectDetailsModel extends RepresentationModel<ProjectDetailsModel> {
    private final UUID id;
    private final String title;
    private final String description;
    private final String detailedDescription;
    private final String projectUrl;
    private final String githubUrl;
    private final String demoUrl;
    private final ProjectType projectType;
    private final Status status;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Set<String> technologies;
    private final Set<String> keyFeatures;
    private final String imageUrl;

    public ProjectDetailsModel(UUID id, String title, String description, String detailedDescription, String projectUrl, String githubUrl, String demoUrl, ProjectType projectType, Status status, LocalDate startDate, LocalDate endDate, Set<String> technologies, Set<String> keyFeatures, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.projectUrl = projectUrl;
        this.githubUrl = githubUrl;
        this.demoUrl = demoUrl;
        this.projectType = projectType;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.technologies = technologies;
        this.keyFeatures = keyFeatures;
        this.imageUrl = imageUrl;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Set<String> getTechnologies() {
        return technologies;
    }

    public Set<String> getKeyFeatures() {
        return keyFeatures;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
