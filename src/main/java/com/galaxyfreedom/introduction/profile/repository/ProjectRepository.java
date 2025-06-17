package com.galaxyfreedom.introduction.profile.repository;

import com.galaxyfreedom.introduction.profile.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Page<Project> findAllByProfile_Id(UUID profileId, Pageable pageable);

    Optional<Project> findWithProfile_IdById(UUID id);
}
