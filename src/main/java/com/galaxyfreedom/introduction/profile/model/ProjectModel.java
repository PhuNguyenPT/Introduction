package com.galaxyfreedom.introduction.profile.model;

import com.galaxyfreedom.introduction.profile.enums.Status;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class ProjectModel extends RepresentationModel<ProjectModel> {
    private final UUID id;
    private final String title;
    private final Status status;
    private final String description;
    private final String duration;

    public ProjectModel(UUID id, String title, Status status, String description, String duration) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.duration = duration;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }
}
