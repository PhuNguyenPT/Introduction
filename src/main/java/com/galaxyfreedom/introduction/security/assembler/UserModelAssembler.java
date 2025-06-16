package com.galaxyfreedom.introduction.security.assembler;

import com.galaxyfreedom.introduction.security.entity.UserEntity;
import com.galaxyfreedom.introduction.security.mapper.UserMapper;
import com.galaxyfreedom.introduction.security.model.UserModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;

public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserEntity, UserModel> {

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public UserModelAssembler(Class<?> controllerClass, Class<UserModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    protected @NonNull UserModel instantiateModel(@NonNull UserEntity entity) {
        return UserMapper.toModel(entity);
    }

    @Override
    public @NonNull UserModel toModel(@NonNull UserEntity entity) {
        return createModelWithId(entity.getId(), entity);
    }
}
