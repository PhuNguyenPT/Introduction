package com.galaxyfreedom.introduction.profile.entity;

import com.galaxyfreedom.introduction.audit.Auditable;
import com.galaxyfreedom.introduction.profile.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "projects", schema = "portfolio")
public class Project extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(length = 2000)
    private String detailedDescription;

    private String projectUrl;
    private String githubUrl;
    private String demoUrl;

    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate startDate;
    private LocalDate endDate;

    @ElementCollection
    @CollectionTable(name = "project_technologies", schema = "portfolio",
            joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "technology")
    private Set<String> technologies;

    @ElementCollection
    @CollectionTable(name = "project_features", schema = "portfolio",
            joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "feature")
    private Set<String> keyFeatures;

    private String imageUrl;
    private Boolean isFeatured = false;
    private Integer displayOrder = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    // Constructors
    public Project() {}

    public Project(String title, String description, ProjectType projectType) {
        this.title = title;
        this.description = description;
        this.projectType = projectType;
        this.status = Status.COMPLETED;
    }

    // Enums
    public enum ProjectType {
        WEB_APPLICATION("Web Application"),
        MOBILE_APPLICATION("Mobile Application"),
        DATA_SCIENCE("Data Science/ML"),
        UI_UX_DESIGN("UI/UX Design"),
        API_BACKEND("API/Backend"),
        DESKTOP_APPLICATION("Desktop Application"),
        GAME("Game Development"),
        OTHER("Other");

        private final String displayName;

        ProjectType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }


    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<String> technologies) {
        this.technologies = technologies;
    }

    public Set<String> getKeyFeatures() {
        return keyFeatures;
    }

    public void setKeyFeatures(Set<String> keyFeatures) {
        this.keyFeatures = keyFeatures;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    // Utility methods
    public boolean isCompleted() {
        return Status.COMPLETED.equals(this.status);
    }

    public boolean isInProgress() {
        return Status.IN_PROGRESS.equals(this.status);
    }

    public boolean isFeaturedProject() {
        return Boolean.TRUE.equals(this.isFeatured);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", projectUrl='" + projectUrl + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", demoUrl='" + demoUrl + '\'' +
                ", projectType=" + projectType +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", imageUrl='" + imageUrl + '\'' +
                ", isFeatured=" + isFeatured +
                ", displayOrder=" + displayOrder +
                super.toString();
    }
}