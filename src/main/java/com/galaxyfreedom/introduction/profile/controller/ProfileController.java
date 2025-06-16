package com.galaxyfreedom.introduction.profile.controller;

import com.galaxyfreedom.introduction.profile.assemblers.ExperienceDetailsModelAssembler;
import com.galaxyfreedom.introduction.profile.assemblers.ExperienceModelAssembler;
import com.galaxyfreedom.introduction.profile.assemblers.ProfileModelAssembler;
import com.galaxyfreedom.introduction.profile.entity.Experience;
import com.galaxyfreedom.introduction.profile.entity.Interest;
import com.galaxyfreedom.introduction.profile.entity.Profile;
import com.galaxyfreedom.introduction.profile.model.ExperienceDetailsModel;
import com.galaxyfreedom.introduction.profile.model.ExperienceModel;
import com.galaxyfreedom.introduction.profile.model.ProfileModel;
import com.galaxyfreedom.introduction.profile.service.ExperienceService;
import com.galaxyfreedom.introduction.profile.service.InterestService;
import com.galaxyfreedom.introduction.profile.service.ProfileService;
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
@RequestMapping("/profiles")
public class ProfileController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    private final @Qualifier("profileModelAssembler") ProfileModelAssembler profileModelAssembler;
    private final ProfileService profileService;
    private final ExperienceService experienceService;
    private final @Qualifier("experienceModelAssembler") ExperienceModelAssembler experienceModelAssembler;
    private final @Qualifier("experienceDetailsModelAssembler") ExperienceDetailsModelAssembler experienceDetailsModelAssembler;
    private final InterestService interestService;

    public ProfileController(ProfileModelAssembler profileModelAssembler, ProfileService profileService, ExperienceService experienceService, ExperienceModelAssembler experienceModelAssembler, ExperienceDetailsModelAssembler experienceDetailsModelAssembler, InterestService interestService) {
        this.profileModelAssembler = profileModelAssembler;
        this.profileService = profileService;
        this.experienceService = experienceService;
        this.experienceModelAssembler = experienceModelAssembler;
        this.experienceDetailsModelAssembler = experienceDetailsModelAssembler;
        this.interestService = interestService;
    }

    @GetMapping
    public String getProfile(Model model) {
        // Get the main profile (assuming there's one primary profile)
        Profile profile = profileService.getPrimaryProfile();
        ProfileModel profileModel = profileModelAssembler.toModel(profile);

        model.addAttribute("profile", profileModel);
        return "profile/index";
    }

    @GetMapping("/{profileId}/experiences")
    public String getExperiences(@PathVariable("profileId") UUID profileId, Model model) {
        Set<Experience> experiences = experienceService.findByProfile_Id(profileId);
        Set<ExperienceModel> experienceModels = experiences.stream()
                .map(experienceModelAssembler::toModel)
                .collect(Collectors.toSet());
        model.addAttribute("experiences", experienceModels);
        return "profile/fragments/experiences";
    }

    @GetMapping("/{profileId}/experiences/{experienceId}")
    public String getExperienceDetails(@PathVariable UUID profileId, @PathVariable("experienceId") UUID id, Model model) {
        Experience experience = experienceService.findWithProfileIDById(id);
        Assert.notNull(experience, "Experience with Id " + id + " not found");

        // Assert that the experience's profile ID matches the path variable profileId
        Assert.isTrue(experience.getProfile().getId().equals(profileId), "Experience with id " + id + " does not belong to profile with id " + profileId); // Assuming Experience has getProfile().getId()

        ExperienceDetailsModel details = experienceDetailsModelAssembler.toModel(experience);
        model.addAttribute("experience", details);

        return "profile/fragments/experience-modal";
    }

    @GetMapping("/{profileId}/skills")
    public String getSkills(Model model, @PathVariable String profileId) {
        Profile profile = profileService.getPrimaryProfile();
        model.addAttribute("skills", profile.getSkills());
        return "profile/fragments/skills";
    }

    @GetMapping("/{profileId}/interests")
    public String getInterests(Model model, @PathVariable UUID profileId) {
        Set<Interest> interests = interestService.findInterestsByProfile_Id(profileId);
        log.info("Interest size {}", interests.size());
        model.addAttribute("interests", interests);
        return "profile/fragments/interests";
    }

    @GetMapping("/{profileId}/contact")
    public String getContactInfo(Model model, @PathVariable String profileId) {
        Profile profile = profileService.getPrimaryProfile();
        ProfileModel profileModel = profileModelAssembler.toModel(profile);
        model.addAttribute("profile", profileModel);
        return "profile/fragments/contact";
    }

    @PostMapping("/{profileId}/contact")
    @ResponseBody
    public String sendMessage(@RequestParam String message, @RequestParam String email, @PathVariable String profileId) {
        // Handle contact form submission
        // You can implement email sending logic here
        return "<div class='alert alert-success'>Thank you for your message! I'll get back to you soon.</div>";
    }
}
