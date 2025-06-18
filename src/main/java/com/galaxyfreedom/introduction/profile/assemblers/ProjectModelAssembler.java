package com.galaxyfreedom.introduction.profile.assemblers;

import com.galaxyfreedom.introduction.profile.entity.Project;
import com.galaxyfreedom.introduction.profile.mapper.ProjectMapper;
import com.galaxyfreedom.introduction.profile.model.ProjectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;

public class ProjectModelAssembler extends RepresentationModelAssemblerSupport<Project, ProjectModel> {
    private static final Logger log = LoggerFactory.getLogger(ProjectModelAssembler.class);

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ProjectModelAssembler(Class<?> controllerClass, Class<ProjectModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected @NonNull ProjectModel instantiateModel(@NonNull Project entity) {
        return ProjectMapper.toModel(entity);
    }

    @Override
    public @NonNull ProjectModel toModel(@NonNull Project entity) {
        log.info("Converting project id {}, profile id {}", entity.getId(), entity.getProfile().getId());
        ProjectModel projectModel = createModelWithId(entity.getId(), entity, entity.getProfile().getId());
        log.info("Converted project to model: {}", projectModel);
        return projectModel;
    }
}
