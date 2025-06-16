package com.galaxyfreedom.introduction.profile.mapper;

import com.galaxyfreedom.introduction.profile.entity.Project;
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
}
