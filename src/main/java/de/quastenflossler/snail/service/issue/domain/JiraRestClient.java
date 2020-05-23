package de.quastenflossler.snail.service.issue.domain;

import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;

public interface JiraRestClient {

    void setUrl(String jiraUrl);

    String login(String username, String password);

    BasicIssueTO findIssue(String issueKey, String username, String password);
}
