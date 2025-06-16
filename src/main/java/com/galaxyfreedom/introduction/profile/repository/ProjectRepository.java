package com.galaxyfreedom.introduction.profile.repository;

import com.galaxyfreedom.introduction.profile.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
