package com.galaxyfreedom.introduction.profile.model;

import org.springframework.hateoas.RepresentationModel;

public class ContactModel extends RepresentationModel<ContactModel> {
    private final String email;
    private final String phone;
    private final String location;
    private final String linkedinUrl;
    private final String githubUrl;
    private final String portfolioUrl;

    public ContactModel(String email, String phone, String location, String linkedinUrl, String githubUrl, String portfolioUrl) {
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.linkedinUrl = linkedinUrl;
        this.githubUrl = githubUrl;
        this.portfolioUrl = portfolioUrl;
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
}
