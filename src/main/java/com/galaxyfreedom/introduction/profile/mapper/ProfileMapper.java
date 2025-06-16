package com.galaxyfreedom.introduction.profile.mapper;

import com.galaxyfreedom.introduction.profile.entity.Profile;
import com.galaxyfreedom.introduction.profile.model.ContactModel;
import com.galaxyfreedom.introduction.profile.model.ProfileModel;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    public static ProfileModel toModel(Profile profile) {
        return (profile != null) ? new ProfileModel(
                profile.getId(),
                profile.getName(),
                profile.getTitle(),
                profile.getDescription(),
                profile.getEmail(),
                profile.getPhone(),
                profile.getLocation(),
                profile.getLinkedinUrl(),
                profile.getGithubUrl(),
                profile.getPortfolioUrl(),
                ProfileModel.ProfileStats.calculateStats(profile)
        ) : null;
    }

    public static ContactModel toContactModel(Profile profile) {
        return (profile != null) ? new ContactModel(
                profile.getEmail(),
                profile.getPhone(),
                profile.getLocation(),
                profile.getLinkedinUrl(),
                profile.getGithubUrl(),
                profile.getPortfolioUrl()
        ) : null;
    }
}
