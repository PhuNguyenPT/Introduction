package com.galaxyfreedom.introduction.profile.mapper;

import com.galaxyfreedom.introduction.profile.entity.Project;
import com.galaxyfreedom.introduction.profile.model.ProjectDetailsModel;
import com.galaxyfreedom.introduction.profile.model.ProjectModel;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public static ProjectModel toModel(Project project) {
        return (project != null) ? new ProjectModel(
                project.getId(),
                project.getTitle(),
                project.getStatus(),
                project.getDescription(),
                project.getFormattedDuration()
        ) : null;
    }

    public static ProjectDetailsModel toDetailsModel(Project project) {
        return (project != null) ? new ProjectDetailsModel(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getDetailedDescription(),
                project.getProjectUrl(),
                project.getGithubUrl(),
                project.getDemoUrl(),
                project.getProjectType(),
                project.getStatus(),
                project.getStartDate(),
                project.getEndDate(),
                (!project.getTechnologies().isEmpty()) ? project.getTechnologies() : null,
                (!project.getKeyFeatures().isEmpty()) ? project.getKeyFeatures() : null,
                project.getImageUrl()
        ) : null;
    }
}
