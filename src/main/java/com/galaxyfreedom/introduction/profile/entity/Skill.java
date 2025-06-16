package com.galaxyfreedom.introduction.profile.entity;

import com.galaxyfreedom.introduction.audit.Auditable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "skills", schema = "portfolio")
public class Skill extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Skill(String name) {
        this.name = name;
    }

    public Skill() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                super.toString();
    }
}
