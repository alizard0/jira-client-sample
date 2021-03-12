package com.example.controller;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.example.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/projects")
    public List<BasicProject> getProjects() {
        return projectService.getProjects();
    }

    @RequestMapping("/projects/{id}")
    public Project getProjectById(String id) {
        return projectService.getProjectById(id);
    }
}