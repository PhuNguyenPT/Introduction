package com.galaxyfreedom.introduction.profile.controller;

import com.galaxyfreedom.introduction.profile.assemblers.ContactModelAssembler;
import com.galaxyfreedom.introduction.profile.assemblers.ExperienceDetailsModelAssembler;
import com.galaxyfreedom.introduction.profile.assemblers.ExperienceModelAssembler;
import com.galaxyfreedom.introduction.profile.assemblers.ProjectModelAssembler;
import com.galaxyfreedom.introduction.profile.entity.*;
import com.galaxyfreedom.introduction.profile.model.ContactModel;
import com.galaxyfreedom.introduction.profile.model.ExperienceDetailsModel;
import com.galaxyfreedom.introduction.profile.model.ExperienceModel;
import com.galaxyfreedom.introduction.profile.model.ProjectModel;
import com.galaxyfreedom.introduction.profile.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profiles/{profileId}")
public class ProfileDetailsController {
    private static final Logger log = LoggerFactory.getLogger(ProfileDetailsController.class);
    private final ExperienceService experienceService;
    private final @Qualifier("experienceModelAssembler") ExperienceModelAssembler experienceModelAssembler;
    private final @Qualifier("experienceDetailsModelAssembler") ExperienceDetailsModelAssembler experienceDetailsModelAssembler;
    private final InterestService interestService;
    private final SkillService skillService;
    private final @Qualifier("contactModelAssembler") ContactModelAssembler contactModelAssembler;
    private final ProjectService projectService;
    private final @Qualifier("projectModelAssembler") ProjectModelAssembler projectModelAssembler;
    private final @Qualifier("pagedResourcesAssemblerProject") PagedResourcesAssembler<Project> projectPagedResourcesAssembler;
    private final ProfileService profileService;

    public ProfileDetailsController(ExperienceService experienceService,
                                    ExperienceModelAssembler experienceModelAssembler,
                                    ExperienceDetailsModelAssembler experienceDetailsModelAssembler,
                                    InterestService interestService,
                                    SkillService skillService,
                                    ContactModelAssembler contactModelAssembler,
                                    ProjectService projectService,
                                    ProjectModelAssembler projectModelAssembler,
                                    PagedResourcesAssembler<Project> projectPagedResourcesAssembler, ProfileService profileService) {
        this.experienceService = experienceService;
        this.experienceModelAssembler = experienceModelAssembler;
        this.experienceDetailsModelAssembler = experienceDetailsModelAssembler;
        this.interestService = interestService;
        this.skillService = skillService;
        this.contactModelAssembler = contactModelAssembler;
        this.projectService = projectService;
        this.projectModelAssembler = projectModelAssembler;
        this.projectPagedResourcesAssembler = projectPagedResourcesAssembler;
        this.profileService = profileService;
    }

    @GetMapping("/projects")
    public String getProjects(@PathVariable UUID profileId,
                              @PageableDefault(size = 3, sort = {"startDate"}, direction = Sort.Direction.DESC) Pageable pageable,
                              Model model) {
        Page<Project> projectPage = projectService.findAllByProfile_Id(profileId, pageable);
        projectPagedResourcesAssembler.setForceFirstAndLastRels(true);
        PagedModel<ProjectModel> projectModelPagedModel = projectPagedResourcesAssembler.toModel(projectPage, projectModelAssembler);
        model.addAttribute("projectModelPagedModel", projectModelPagedModel);
        return "profile/fragments/projects";
    }


    @GetMapping("/experiences")
    public String getExperiences(@PathVariable("profileId") UUID profileId, Model model) {
        Set<Experience> experiences = experienceService.findByProfile_Id(profileId);
        Set<ExperienceModel> experienceModels = experiences.stream()
                .map(experienceModelAssembler::toModel)
                .collect(Collectors.toSet());
        model.addAttribute("experiences", experienceModels);
        return "profile/fragments/experiences";
    }

    @GetMapping("/experiences/{experienceId}")
    public String getExperienceDetails(@PathVariable UUID profileId, @PathVariable("experienceId") UUID id, Model model) {
        Experience experience = experienceService.findWithProfileIDById(id);
        Assert.notNull(experience, "Experience with Id " + id + " not found");

        // Assert that the experience's profile ID matches the path variable profileId
        Assert.isTrue(experience.getProfile().getId().equals(profileId), "Experience with id " + id + " does not belong to profile with id " + profileId); // Assuming Experience has getProfile().getId()

        ExperienceDetailsModel details = experienceDetailsModelAssembler.toModel(experience);
        model.addAttribute("experience", details);

        return "profile/fragments/experience-modal";
    }

    @GetMapping("/skills")
    public String getSkills(Model model, @PathVariable UUID profileId) {
        Set<Skill> skills = skillService.findAlLByProfile_Id(profileId);
        model.addAttribute("skills", skills);
        return "profile/fragments/skills";
    }

    @GetMapping("/interests")
    public String getInterests(Model model, @PathVariable UUID profileId) {
        Set<Interest> interests = interestService.findInterestsByProfile_Id(profileId);
        log.info("Interest size {}", interests.size());
        model.addAttribute("interests", interests);
        return "profile/fragments/interests";
    }

    @GetMapping("/contact")
    public String getContactInfo(Model model, @PathVariable UUID profileId) {
        Profile profile = profileService.findById(profileId);
        ContactModel contactModel = contactModelAssembler.toModel(profile);
        model.addAttribute("contact", contactModel);
        return "profile/fragments/contact";
    }

    @PostMapping("/contact")
    @ResponseBody
    public String sendMessage(@RequestParam String message, @RequestParam String email, @PathVariable String profileId) {
        // Handle contact form submission
        // You can implement email sending logic here
        return "<div class='alert alert-success'>Thank you for your message! I'll get back to you soon.</div>";
    }
}
