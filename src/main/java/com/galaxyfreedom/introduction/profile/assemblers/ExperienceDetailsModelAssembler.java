package com.galaxyfreedom.introduction.profile.assemblers;

import com.galaxyfreedom.introduction.profile.entity.Experience;
import com.galaxyfreedom.introduction.profile.mapper.ExperienceMapper;
import com.galaxyfreedom.introduction.profile.model.ExperienceDetailsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;

public class ExperienceDetailsModelAssembler extends RepresentationModelAssemblerSupport<Experience, ExperienceDetailsModel> {
    private static final Logger log = LoggerFactory.getLogger(ExperienceDetailsModelAssembler.class);

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ExperienceDetailsModelAssembler(Class<?> controllerClass, Class<ExperienceDetailsModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected @NonNull ExperienceDetailsModel instantiateModel(@NonNull Experience entity) {
        return ExperienceMapper.toDetailsModel(entity);
    }

    @Override
    public @NonNull ExperienceDetailsModel toModel(@NonNull Experience entity) {
        log.info("Experience Details Id: {}, Profile Id: {}", entity.getId(), entity.getProfile().getId());
        return createModelWithId(entity.getId(), entity, entity.getProfile().getId());
    }
}
