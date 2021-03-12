package com.example.service;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.example.connector.JiraHttpConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    JiraHttpConnector jiraHttpConnector;

    public List<BasicProject> getProjects() {
        return jiraHttpConnector.getProjects();
    }

    public Project getProjectById(String id) {
        return jiraHttpConnector.getProject(id);
    }
}
