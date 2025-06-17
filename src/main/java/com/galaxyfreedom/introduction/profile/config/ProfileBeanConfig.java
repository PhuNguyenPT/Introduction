package com.galaxyfreedom.introduction.profile.config;

import com.galaxyfreedom.introduction.profile.assemblers.*;
import com.galaxyfreedom.introduction.profile.controller.ContactController;
import com.galaxyfreedom.introduction.profile.controller.ProfileController;
import com.galaxyfreedom.introduction.profile.controller.ExperienceController;
import com.galaxyfreedom.introduction.profile.controller.ProjectController;
import com.galaxyfreedom.introduction.profile.entity.Project;
import com.galaxyfreedom.introduction.profile.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PagedResourcesAssembler;

@Configuration
public class ProfileBeanConfig {
    @Bean(name = "profileModelAssembler")
    public ProfileModelAssembler profileModelAssembler() {
        return new ProfileModelAssembler(ProfileController.class, ProfileModel.class);
    }

    @Bean(name = "experienceModelAssembler")
    public ExperienceModelAssembler experienceModelAssembler() {
        return new ExperienceModelAssembler(ExperienceController.class, ExperienceModel.class);
    }

    @Bean(name = "experienceDetailsModelAssembler")
    public ExperienceDetailsModelAssembler experienceDetailsModelAssembler() {
        return new ExperienceDetailsModelAssembler(ExperienceController.class, ExperienceDetailsModel.class);
    }

    @Bean(name = "contactModelAssembler")
    public ContactModelAssembler contactModelAssembler() {
        return new ContactModelAssembler(ContactController.class, ContactModel.class);
    }

    @Bean(name = "projectModelAssembler")
    public ProjectModelAssembler projectModelAssembler() {
        return new ProjectModelAssembler(ProjectController.class, ProjectModel.class);
    }

    @Bean(name = "pagedResourcesAssemblerProject")
    public PagedResourcesAssembler<Project> pagedResourcesAssemblerProject(PagedResourcesAssembler<Project> pagedResourcesAssembler) {
        return pagedResourcesAssembler;
    }
}
