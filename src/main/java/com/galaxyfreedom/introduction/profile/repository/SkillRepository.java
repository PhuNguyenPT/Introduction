package com.galaxyfreedom.introduction.profile.repository;

import com.galaxyfreedom.introduction.profile.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface SkillRepository extends JpaRepository<Skill, UUID> {
    Set<Skill> findAllByProfile_Id(UUID profileId);
}
