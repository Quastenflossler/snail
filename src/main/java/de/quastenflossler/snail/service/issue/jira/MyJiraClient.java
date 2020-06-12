package de.quastenflossler.snail.service.issue.jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class MyJiraClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyJiraClient.class);

    private final String username;
    private final String password;
    private final String jiraUrl;
    private final JiraRestClient restClient;

    public MyJiraClient(final String username, final String password, final String jiraUrl) {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        restClient = getJiraRestClient();
    }

    public Issue getIssue(final String issueKey) {

        return restClient.getIssueClient()
                .getIssue(issueKey)
                .claim();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }
}
