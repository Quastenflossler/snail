package de.quastenflossler.snail.service.issue;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;

import java.util.List;

public interface IssueService {

    String createPdfFromIssue(BasicIssueTO issueTO, String exportPath) throws InternalServiceException, DataValidationServiceException;

    String loginToJira(String username, String password);

    List<BasicIssueTO> findJiraIssuesByJql(String jql);

    BasicIssueTO findJiraIssue(String issueKey, String username, String password);
}
