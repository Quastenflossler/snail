package de.quastenflossler.snail.service.issue;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;

public interface IssueService {

    void printIssue(BasicIssueTO issueTO) throws InternalServiceException, DataValidationServiceException;
}
