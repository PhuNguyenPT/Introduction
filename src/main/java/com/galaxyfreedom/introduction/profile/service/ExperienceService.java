package com.galaxyfreedom.introduction.profile.service;

import com.galaxyfreedom.introduction.profile.entity.Experience;
import com.galaxyfreedom.introduction.profile.repository.ExperienceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class ExperienceService {
    private static final Logger log = LoggerFactory.getLogger(ExperienceService.class);
    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public Set<Experience> findByProfile_Id(UUID profileId) {
        log.info("Find experiences set by profile id: {}", profileId);
        return experienceRepository.findByProfile_Id(profileId);
    }

    public Experience findWithProfileIDById(UUID id) {
        log.info("Find experience by id: {}", id);
        return experienceRepository.findWithProfile_IdById(id)
                .orElseThrow(() -> new EntityNotFoundException("Experience with id " + id + " not found"));
    }
}
