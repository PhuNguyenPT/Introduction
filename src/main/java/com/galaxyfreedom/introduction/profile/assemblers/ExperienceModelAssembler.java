package com.galaxyfreedom.introduction.profile.assemblers;

import com.galaxyfreedom.introduction.profile.entity.Experience;
import com.galaxyfreedom.introduction.profile.mapper.ExperienceMapper;
import com.galaxyfreedom.introduction.profile.model.ExperienceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;

public class ExperienceModelAssembler extends RepresentationModelAssemblerSupport<Experience, ExperienceModel> {


    private static final Logger log = LoggerFactory.getLogger(ExperienceModelAssembler.class);

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ExperienceModelAssembler(Class<?> controllerClass, Class<ExperienceModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected @NonNull ExperienceModel instantiateModel(@NonNull Experience entity) {
        return ExperienceMapper.toModel(entity);
    }

    @Override
    public @NonNull ExperienceModel toModel(@NonNull Experience entity) {
        ExperienceModel experienceModel = createModelWithId(entity.getId(), entity, entity.getProfile().getId());
        log.info("ExperienceModel {}", experienceModel);
        return experienceModel;
    }
}
