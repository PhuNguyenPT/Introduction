package com.galaxyfreedom.introduction.profile.service;

import com.galaxyfreedom.introduction.profile.entity.Skill;
import com.galaxyfreedom.introduction.profile.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Set<Skill> findAlLByProfile_Id(UUID profileId) {
        return skillRepository.findAllByProfile_Id(profileId);
    }
}
