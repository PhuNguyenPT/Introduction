package com.galaxyfreedom.introduction.security.model;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class UserModel extends RepresentationModel<UserModel> {
    private final UUID id;
    private final String username;

    public UserModel(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
