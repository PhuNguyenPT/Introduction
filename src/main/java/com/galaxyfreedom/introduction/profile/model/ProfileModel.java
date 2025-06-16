package com.galaxyfreedom.introduction.profile.model;

import com.galaxyfreedom.introduction.profile.entity.Experience;
import com.galaxyfreedom.introduction.profile.entity.Profile;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

@Relation(collectionRelation = "profiles")
public class ProfileModel extends RepresentationModel<ProfileModel> {
    private final UUID id;
    private final String name;
    private final String title;
    private final String description;
    private final String email;
    private final String phone;
    private final String location;
    private final String linkedinUrl;
    private final String githubUrl;
    private final String portfolioUrl;
    private final ProfileStats stats;

    public ProfileModel(UUID id, String name, String title, String description, String email, String phone,
                        String location, String linkedinUrl, String githubUrl, String portfolioUrl,
                        ProfileStats stats) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.linkedinUrl = linkedinUrl;
        this.githubUrl = githubUrl;
        this.portfolioUrl = portfolioUrl;
        this.stats = stats;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public ProfileStats getStats() {
        return stats;
    }

    public record ProfileStats(int projectsCount, int yearsExperience, int clientsCount) {

        public static ProfileModel.ProfileStats calculateStats(Profile profile) {
                int projectsCount = 50; // This could be calculated from a projects table
                int yearsExperience = calculateYearsOfExperience(profile);
                int clientsCount = 25; // This could be calculated from a clients table

                return new ProfileModel.ProfileStats(projectsCount, yearsExperience, clientsCount);
            }

            public static int calculateYearsOfExperience(Profile profile) {
                if (profile.getExperiences() == null || profile.getExperiences().isEmpty()) {
                    return 0;
                }

                LocalDate earliestStart = profile.getExperiences().stream()
                        .map(Experience::getStartDate)
                        .min(LocalDate::compareTo)
                        .orElse(LocalDate.now());

                return Period.between(earliestStart, LocalDate.now()).getYears();
            }
        }
}
