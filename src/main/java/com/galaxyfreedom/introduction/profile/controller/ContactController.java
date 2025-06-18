package com.galaxyfreedom.introduction.profile.controller;

import com.galaxyfreedom.introduction.profile.assemblers.ContactModelAssembler;
import com.galaxyfreedom.introduction.profile.entity.Profile;
import com.galaxyfreedom.introduction.profile.model.ContactModel;
import com.galaxyfreedom.introduction.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/profiles/{profileId}/contact")
public class ContactController {

    private final @Qualifier("contactModelAssembler") ContactModelAssembler contactModelAssembler;
    private final ProfileService profileService;

    public ContactController(ContactModelAssembler contactModelAssembler, ProfileService profileService) {
        this.contactModelAssembler = contactModelAssembler;
        this.profileService = profileService;
    }

    @GetMapping
    public String getContactInfo(Model model, @PathVariable UUID profileId) {
        Profile profile = profileService.findById(profileId);
        ContactModel contactModel = contactModelAssembler.toModel(profile);
        model.addAttribute("contact", contactModel);
        return "profile/fragments/contact";
    }

    @PostMapping
    @ResponseBody
    public String sendMessage(@RequestParam String message, @RequestParam String email, @PathVariable String profileId) {
        // Handle contact form submission
        // You can implement email sending logic here
        return "<div class='alert alert-success'>Thank you for your message! I'll get back to you soon.</div>";
    }
}
