package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.service.issue.DefaultIssueService;
import de.quastenflossler.snail.service.issue.IssueService;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;
import de.quastenflossler.snail.ui.command.BasicCommand;
import de.quastenflossler.snail.ui.model.JiraLoginModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Scope(value = "prototype")
public class FindIssuesCommand implements BasicCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindIssuesCommand.class);

    @Resource(name = DefaultIssueService.RESOURCE_NAME)
    private IssueService issueService;

    @Resource(name = JiraLoginModel.RESOURCE_NAME)
    private JiraLoginModel jiraLoginModel;

    private String jql;

    public String getJql() {
        return jql;
    }

    public void setJql(final String jql) {
        this.jql = jql;
    }

    @Override
    public void execute() {

        List<BasicIssueTO> issues = issueService.findJiraIssuesByJql(jql);
        issues.forEach(v -> jiraLoginModel.addIssue(v));

        LOGGER.debug("{} issues found!", issues.size());
    }
}
