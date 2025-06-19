package com.galaxyfreedom.introduction.profile.assembler;

import com.galaxyfreedom.introduction.profile.entity.Project;
import com.galaxyfreedom.introduction.profile.mapper.ProjectMapper;
import com.galaxyfreedom.introduction.profile.model.ProjectDetailsModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;

public class ProjectDetailsModelAssembler extends RepresentationModelAssemblerSupport<Project, ProjectDetailsModel> {

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ProjectDetailsModelAssembler(Class<?> controllerClass, Class<ProjectDetailsModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected @NonNull ProjectDetailsModel instantiateModel(@NonNull Project entity) {
        return ProjectMapper.toDetailsModel(entity);
    }

    @Override
    public @NonNull ProjectDetailsModel toModel(@NonNull Project entity) {
        return createModelWithId(entity.getId(), entity, entity.getProfile().getId());
    }
}
