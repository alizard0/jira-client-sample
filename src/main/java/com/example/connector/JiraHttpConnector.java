package com.example.connector;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class JiraHttpConnector {

    @Value("jira.username")
    private final String username;

    @Value("jira.password")
    private final String password;

    @Value("jira.url")
    private final String jiraUrl;

    private final JiraRestClient restClient;

    public JiraHttpConnector(String username, String password, String jiraUrl) {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }

    private JiraRestClient rest() {
        return this.restClient;
    }

    public List<BasicProject> getProjects() {
        List<BasicProject> projects = new ArrayList<>();
        this.rest().getProjectClient().getAllProjects().claim().forEach(projects::add);
        return projects;
    }

    public Project getProject(String projectKey) {
        return this.rest().getProjectClient().getProject(projectKey).claim();
    }

    public SearchResult searchJql(String query) {
        return this.rest().getSearchClient().searchJql(query).claim();
    }

    public List<User> findUsers(String username) {
        List<User> users = new ArrayList<>();
        this.rest().getUserClient().findUsers(username).claim().forEach(users::add);
        return users;
    }
}
