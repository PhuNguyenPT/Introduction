package com.galaxyfreedom.introduction.profile.service;

import com.galaxyfreedom.introduction.profile.entity.Project;
import com.galaxyfreedom.introduction.profile.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Page<Project> findAllByProfile_IdOrderByDisplayOrderAsc(UUID profileId, Pageable pageable) {
        return projectRepository.findAllByProfile_IdOrderByDisplayOrderAsc(profileId, pageable);
    }

    public Project findWithProfile_IdById(UUID projectId) {
        return projectRepository.findWithProfile_IdById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + " not found"));
    }
}
