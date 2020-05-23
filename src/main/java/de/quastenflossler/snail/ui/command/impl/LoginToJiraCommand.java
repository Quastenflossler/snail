package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.service.issue.DefaultIssueService;
import de.quastenflossler.snail.service.issue.IssueService;
import de.quastenflossler.snail.ui.command.BasicCommand;
import de.quastenflossler.snail.ui.model.JiraLoginModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Scope(value = "prototype")
public class LoginToJiraCommand implements BasicCommand {

    @Resource(name = DefaultIssueService.RESOURCE_NAME)
    private IssueService issueService;

    @Resource(name = JiraLoginModel.RESOURCE_NAME)
    private JiraLoginModel jiraLoginModel;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public void execute() {

        jiraLoginModel.setSessionId(issueService.loginToJira(username, password));
    }
}
