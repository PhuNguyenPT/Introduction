package com.galaxyfreedom.introduction.profile.service;

import com.galaxyfreedom.introduction.profile.entity.Profile;
import com.galaxyfreedom.introduction.profile.repository.ProfileRepository;
import com.galaxyfreedom.introduction.profile.repository.InterestRepository;
import com.galaxyfreedom.introduction.security.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final InterestRepository interestRepository;

    public ProfileService(ProfileRepository profileRepository, InterestRepository interestRepository) {
        this.profileRepository = profileRepository;
        this.interestRepository = interestRepository;
    }

    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getPrimaryProfile() {
        return profileRepository.findFirstByIsPrimaryOrderByCreatedAtDesc(true)
                .orElseThrow(() -> new EntityNotFoundException("Primary profile not found"));
    }

    public boolean existsByUser(UserEntity user) {
        return profileRepository.existsByUser(user);
    }

    public Optional<Profile> findByUser(UserEntity user) {
        return profileRepository.findByUser(user);
    }

    public Profile getByUser(UserEntity user) {
        return findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found for user: " + user.getUsername()));
    }
}
