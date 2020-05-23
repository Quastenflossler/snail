package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.service.issue.DefaultIssueService;
import de.quastenflossler.snail.service.issue.IssueService;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;
import de.quastenflossler.snail.ui.command.BasicCommand;
import de.quastenflossler.snail.ui.command.SnailCmd;
import de.quastenflossler.snail.ui.model.JiraLoginModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Scope(value = "prototype")
public class FindJiraIssueCommand implements BasicCommand {

    @Resource(name = DefaultIssueService.RESOURCE_NAME)
    private IssueService issueService;

    @Resource(name = JiraLoginModel.RESOURCE_NAME)
    private JiraLoginModel jiraLoginModel;

    @Override
    public void execute() {

        BasicIssueTO jiraIssue = issueService.findJiraIssue(jiraLoginModel.getJql(), jiraLoginModel.getUsername(), jiraLoginModel.getPassword());

        if (jiraIssue != null) {

            jiraLoginModel.addIssue(jiraIssue);
        }
    }
}
