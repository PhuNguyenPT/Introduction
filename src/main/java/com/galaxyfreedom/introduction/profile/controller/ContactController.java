package com.galaxyfreedom.introduction.profile.controller;

import com.galaxyfreedom.introduction.profile.assembler.ContactModelAssembler;
import com.galaxyfreedom.introduction.profile.dto.ContactMessage;
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

    @GetMapping(path = "/me")
    public String getContactInfo(Model model, @PathVariable UUID profileId) {
        Profile profile = profileService.findById(profileId);
        ContactModel contactModel = contactModelAssembler.toModel(profile);
        model.addAttribute("contact", contactModel);
        model.addAttribute("profileId", profileId);
        return "profile/fragments/contact";
    }

    /**
     * REPLACE your old sendMessage method with this one.
     * This method correctly uses a DTO to capture all form fields.
     */
    @PostMapping("/me")
    public String sendMessage(@ModelAttribute ContactMessage contactMessage, // This captures name, email, subject, message
                              @PathVariable UUID profileId,
                              Model model) {

        // You can now access all the data:
        // contactMessage.getName()
        // contactMessage.getEmail()
        // contactMessage.getSubject()
        // contactMessage.getMessage()

        model.addAttribute("title", "Message Sent Successfully");
        model.addAttribute("message", "Thank you for your message, " + contactMessage.name() + "! I'll get back to you soon.");

        return "profile/fragments/contact-response";
    }
}