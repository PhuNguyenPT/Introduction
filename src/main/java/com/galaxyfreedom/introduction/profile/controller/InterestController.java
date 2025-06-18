package com.galaxyfreedom.introduction.profile.controller;

import com.galaxyfreedom.introduction.profile.entity.Interest;
import com.galaxyfreedom.introduction.profile.service.InterestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/profiles/{profileId}/interests")
public class InterestController {
    private static final Logger log = LoggerFactory.getLogger(InterestController.class);
    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping
    public String getInterests(Model model, @PathVariable UUID profileId) {
        Set<Interest> interests = interestService.findInterestsByProfile_Id(profileId);
        log.info("Interest size {}", interests.size());
        model.addAttribute("interests", interests);
        return "profile/fragments/interests";
    }

}
