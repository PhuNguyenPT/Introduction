package com.galaxyfreedom.introduction.security.config;

import com.galaxyfreedom.introduction.profile.controller.ProfileController;
import com.galaxyfreedom.introduction.security.assembler.UserModelAssembler;
import com.galaxyfreedom.introduction.security.model.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityBeanConfig {
    @Bean(name = "userModelAssemblerProfile")
    public UserModelAssembler userModelAssemblerProfile() {
        return new UserModelAssembler(ProfileController.class, UserModel.class);
    }
}
