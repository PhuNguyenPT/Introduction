package com.galaxyfreedom.introduction.profile.config;

import com.galaxyfreedom.introduction.profile.assemblers.*;
import com.galaxyfreedom.introduction.profile.controller.ProfileController;
import com.galaxyfreedom.introduction.profile.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileBeanConfig {
    @Bean(name = "profileModelAssembler")
    public ProfileModelAssembler profileModelAssembler() {
        return new ProfileModelAssembler(ProfileController.class, ProfileModel.class);
    }

    @Bean(name = "experienceModelAssembler")
    public ExperienceModelAssembler experienceModelAssembler() {
        return new ExperienceModelAssembler(ProfileController.class, ExperienceModel.class);
    }

    @Bean(name = "experienceDetailsModelAssembler")
    public ExperienceDetailsModelAssembler experienceDetailsModelAssembler() {
        return new ExperienceDetailsModelAssembler(ProfileController.class, ExperienceDetailsModel.class);
    }

    @Bean(name = "contactModelAssembler")
    public ContactModelAssembler contactModelAssembler() {
        return new ContactModelAssembler(ProfileController.class, ContactModel.class);
    }

    @Bean(name = "projectModelAssembler")
    public ProjectModelAssembler projectModelAssembler() {
        return new ProjectModelAssembler(ProfileController.class, ProjectModel.class);
    }
}
