package com.galaxyfreedom.introduction.profile.controller;

import com.galaxyfreedom.introduction.profile.assembler.ExperienceDetailsModelAssembler;
import com.galaxyfreedom.introduction.profile.assembler.ExperienceModelAssembler;
import com.galaxyfreedom.introduction.profile.entity.*;
import com.galaxyfreedom.introduction.profile.model.ExperienceDetailsModel;
import com.galaxyfreedom.introduction.profile.model.ExperienceModel;
import com.galaxyfreedom.introduction.profile.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profiles/{profileId}/experiences")
public class ExperienceController {
    private static final Logger log = LoggerFactory.getLogger(ExperienceController.class);
    private final ExperienceService experienceService;
    private final @Qualifier("experienceModelAssembler") ExperienceModelAssembler experienceModelAssembler;
    private final @Qualifier("experienceDetailsModelAssembler") ExperienceDetailsModelAssembler experienceDetailsModelAssembler;


    public ExperienceController(ExperienceService experienceService,
                                ExperienceModelAssembler experienceModelAssembler,
                                ExperienceDetailsModelAssembler experienceDetailsModelAssembler) {
        this.experienceService = experienceService;
        this.experienceModelAssembler = experienceModelAssembler;
        this.experienceDetailsModelAssembler = experienceDetailsModelAssembler;
    }

    @GetMapping
    public String getExperiences(@PathVariable("profileId") UUID profileId, Model model) {
        Set<Experience> experiences = experienceService.findByProfile_Id(profileId);
        Set<ExperienceModel> experienceModels = experiences.stream()
                .map(experienceModelAssembler::toModel)
                .collect(Collectors.toSet());

        model.addAttribute("experiences", experienceModels);
        return "profile/fragments/experiences";
    }

    @GetMapping(path="/{experienceId}")
    public String getExperienceDetails(@PathVariable UUID profileId, @PathVariable("experienceId") UUID id, Model model) {
        Experience experience = experienceService.findWithProfileIDById(id);
        Assert.notNull(experience, "Experience with Id " + id + " not found");

        // Assert that the experience's profile ID matches the path variable profileId
        Assert.isTrue(experience.getProfile().getId().equals(profileId), "Experience with id " + id + " does not belong to profile with id " + profileId); // Assuming Experience has getProfile().getId()

        ExperienceDetailsModel details = experienceDetailsModelAssembler.toModel(experience);
        model.addAttribute("experience", details);

        return "profile/fragments/experience-modal";
    }


}
