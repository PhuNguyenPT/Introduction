package com.galaxyfreedom.introduction.profile.controller;

import com.galaxyfreedom.introduction.profile.entity.Skill;
import com.galaxyfreedom.introduction.profile.service.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/profiles/{profileId}/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public String getSkills(Model model, @PathVariable UUID profileId) {
        Set<Skill> skills = skillService.findAlLByProfile_Id(profileId);
        model.addAttribute("skills", skills);
        return "profile/fragments/skills";
    }

}
