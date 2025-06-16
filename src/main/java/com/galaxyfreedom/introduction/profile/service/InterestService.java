package com.galaxyfreedom.introduction.profile.service;

import com.galaxyfreedom.introduction.profile.entity.Interest;
import com.galaxyfreedom.introduction.profile.repository.InterestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class InterestService {
    private static final Logger log = LoggerFactory.getLogger(InterestService.class);
    private final InterestRepository interestRepository;

    public InterestService(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public Set<Interest> findInterestsByProfile_Id(UUID profileId) {
        log.info("Finding interests by profile id {}", profileId);
        return interestRepository.findInterestsByProfile_Id(profileId);
    }
}
