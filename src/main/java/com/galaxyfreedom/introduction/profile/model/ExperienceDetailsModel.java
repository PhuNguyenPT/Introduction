package com.galaxyfreedom.introduction.profile.model;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.UUID;

public class ExperienceDetailsModel extends RepresentationModel<ExperienceDetailsModel> {
    private final UUID id;
    private final String title;
    private final String company;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public ExperienceDetailsModel(UUID id, String title, String company, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
