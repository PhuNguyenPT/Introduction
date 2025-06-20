package com.galaxyfreedom.introduction.profile.controller;

import com.galaxyfreedom.introduction.profile.assembler.ProjectDetailsModelAssembler;
import com.galaxyfreedom.introduction.profile.assembler.ProjectModelAssembler;
import com.galaxyfreedom.introduction.profile.entity.Project;
import com.galaxyfreedom.introduction.profile.model.ProjectDetailsModel;
import com.galaxyfreedom.introduction.profile.model.ProjectModel;
import com.galaxyfreedom.introduction.profile.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/profiles/{profileId}/projects")
public class ProjectController {
    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);
    private final ProjectService projectService;
    private final @Qualifier("projectModelAssembler") ProjectModelAssembler projectModelAssembler;
    private final @Qualifier("pagedResourcesAssemblerProject") PagedResourcesAssembler<Project> projectPagedResourcesAssembler;
    private final ProjectDetailsModelAssembler projectDetailsModelAssembler;

    public ProjectController(ProjectService projectService, ProjectModelAssembler projectModelAssembler,
                             PagedResourcesAssembler<Project> projectPagedResourcesAssembler, ProjectDetailsModelAssembler projectDetailsModelAssembler) {
        this.projectService = projectService;
        this.projectModelAssembler = projectModelAssembler;
        this.projectPagedResourcesAssembler = projectPagedResourcesAssembler;
        this.projectDetailsModelAssembler = projectDetailsModelAssembler;
    }

    @GetMapping
    public String getProjects(@PathVariable UUID profileId,
                              @PageableDefault(size = 2, sort = {"startDate"}, direction = Sort.Direction.DESC) Pageable pageable,
                              Model model) {
        log.info("Fetch projects for Profile Id {}", profileId);
        Page<Project> projectPage = projectService.findAllByProfile_IdOrderByDisplayOrderAsc(profileId, pageable);
        projectPagedResourcesAssembler.setForceFirstAndLastRels(true);
        PagedModel<ProjectModel> projectModelPagedModel = projectPagedResourcesAssembler.toModel(projectPage, projectModelAssembler);
        log.info("Project PagedModel links: {}", projectModelPagedModel.getLinks());
        log.info("Project PagedModel has self link {}, last link: {}, next link: {}",
                projectModelPagedModel.hasLink("self"), projectModelPagedModel.hasLink("last"),
                projectModelPagedModel.hasLink("next"));
        model.addAttribute("projectModelPagedModel", projectModelPagedModel);
        return "profile/fragments/projects";
    }

    @GetMapping(path="/{projectId}")
    public String getProjectDetails(@PathVariable UUID profileId, @PathVariable UUID projectId, Model model) {
        Project project = projectService.findWithProfile_IdById(projectId);
        Assert.isTrue(project.getProfile().getId().equals(profileId), "Profile Id does not match");
        ProjectDetailsModel projectDetailsModel = projectDetailsModelAssembler.toModel(project);
        log.info("Project Model: {}", projectDetailsModel);
        model.addAttribute("projectDetailsModel", projectDetailsModel);
        return "profile/fragments/project-details";
    }
}
