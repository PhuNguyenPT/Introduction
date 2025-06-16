package com.galaxyfreedom.introduction.profile.model;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class ExperienceModel extends RepresentationModel<ExperienceModel> {
    private final UUID id;
    private final String title;
    private final String company;
    private final String description;
    private final String duration;

    public ExperienceModel(UUID id, String title, String company, String description, String duration) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.description = description;
        this.duration = duration;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }
}
