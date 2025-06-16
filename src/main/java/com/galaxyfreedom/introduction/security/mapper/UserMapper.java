package com.galaxyfreedom.introduction.security.mapper;

import com.galaxyfreedom.introduction.security.entity.UserEntity;
import com.galaxyfreedom.introduction.security.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserModel toModel(UserEntity userEntity) {
        return new UserModel(
                userEntity.getId(),
                userEntity.getUsername()
        );
    }
}
