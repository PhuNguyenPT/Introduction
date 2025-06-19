package com.galaxyfreedom.introduction.profile.assembler;

import com.galaxyfreedom.introduction.profile.entity.Profile;
import com.galaxyfreedom.introduction.profile.mapper.ProfileMapper;
import com.galaxyfreedom.introduction.profile.model.ProfileModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;

public class ProfileModelAssembler extends RepresentationModelAssemblerSupport<Profile, ProfileModel> {
    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ProfileModelAssembler(Class<?> controllerClass, Class<ProfileModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected @NonNull ProfileModel instantiateModel(@NonNull Profile entity) {
        return ProfileMapper.toModel(entity);
    }

    @Override
    public @NonNull ProfileModel toModel(@NonNull Profile entity) {
        return createModelWithId(entity.getId(), entity);
    }
}
