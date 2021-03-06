package de.quastenflossler.snail.service.issue;

import com.atlassian.jira.rest.client.api.domain.Issue;
import de.quastenflossler.snail.service.core.domain.DomainObjectMapper;
import de.quastenflossler.snail.service.core.domain.impl.DefaultDomainObjectMapper;
import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.issue.domain.SmartIssue;
import de.quastenflossler.snail.service.issue.jira.MyJiraClient;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;
import de.quastenflossler.snail.ui.model.JiraLoginModel;
import de.quastenflossler.snail.ui.model.UserPreferencesModel;

import javax.annotation.Resource;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;

@Named(value = DefaultIssueService.RESOURCE_NAME)
public class DefaultIssueService implements IssueService {

    public static final String RESOURCE_NAME = "DefaultIssueService";

    @Resource(name = DefaultDomainObjectMapper.RESOURCE_NAME)
    private DomainObjectMapper domainObjectMapper;


    @Resource(name = JiraLoginModel.RESOURCE_NAME)
    private JiraLoginModel jiraLoginModel;

    @Resource(name = UserPreferencesModel.RESOURCE_NAME)
    private UserPreferencesModel userPreferencesModel;

    @Override
    public String createPdfFromIssue(final BasicIssueTO issueTO, final String exportPath) throws InternalServiceException, DataValidationServiceException {

        SmartIssue issue = domainObjectMapper.createSmartIssue(issueTO);
        return issue.createPdf(exportPath);
    }

    @Override
    public String loginToJira(final String username, final String password) {

        return null;
    }

    @Override
    public List<BasicIssueTO> findJiraIssuesByJql(final String jql) {

        BasicIssueTO bla = new BasicIssueTO();
        bla.setKey("42");
        bla.setSummary("ultra krass");

        return Collections.singletonList(bla);

        //return Collections.emptyList();
    }

    @Override
    public BasicIssueTO findJiraIssue(final String issueKey, final String username, final String password) {

        MyJiraClient myJiraClient = new MyJiraClient(username, password, userPreferencesModel.getJiraUrl());

        Issue issue = myJiraClient.getIssue(issueKey);

        BasicIssueTO issueTO = new BasicIssueTO();
        issueTO.setKey(issue.getKey());
        issueTO.setSummary(issue.getSummary());

        return issueTO;
    }

}
