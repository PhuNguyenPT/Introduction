package com.galaxyfreedom.introduction.profile.controller;

import com.galaxyfreedom.introduction.profile.assemblers.*;
import com.galaxyfreedom.introduction.profile.entity.*;
import com.galaxyfreedom.introduction.profile.model.*;
import com.galaxyfreedom.introduction.profile.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profiles")
public class ProfileController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    private final @Qualifier("profileModelAssembler") ProfileModelAssembler profileModelAssembler;
    private final ProfileService profileService;


    public ProfileController(ProfileModelAssembler profileModelAssembler, ProfileService profileService) {
        this.profileModelAssembler = profileModelAssembler;
        this.profileService = profileService;

    }

    @GetMapping
    public String getProfile(Model model) {
        // Get the main profile (assuming there's one primary profile)
        Profile profile = profileService.getPrimaryProfile();
        ProfileModel profileModel = profileModelAssembler.toModel(profile);

        model.addAttribute("profile", profileModel);
        return "profile/index";
    }
}
