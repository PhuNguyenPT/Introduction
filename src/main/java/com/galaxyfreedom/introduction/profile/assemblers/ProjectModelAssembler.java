package com.galaxyfreedom.introduction.profile.assemblers;

import com.galaxyfreedom.introduction.profile.entity.Project;
import com.galaxyfreedom.introduction.profile.mapper.ProjectMapper;
import com.galaxyfreedom.introduction.profile.model.ProjectModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;

public class ProjectModelAssembler extends RepresentationModelAssemblerSupport<Project, ProjectModel> {
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
        return createModelWithId(entity.getId(), entity, entity.getProfile().getId());
    }
}
