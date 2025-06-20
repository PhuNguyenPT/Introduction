package com.galaxyfreedom.introduction.profile.entity;

import com.galaxyfreedom.introduction.audit.Auditable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "interests", schema = "portfolio")
public class Interest extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Interest(String name) {
        this.name = name;
    }

    public Interest() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                super.toString();
    }
}
