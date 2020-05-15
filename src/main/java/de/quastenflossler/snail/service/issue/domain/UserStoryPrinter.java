package de.quastenflossler.snail.service.issue.domain;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.issue.domain.impl.UserStoryLayout;

public interface UserStoryPrinter {

    String createPdf(BasicIssue issue, UserStoryLayout layout) throws InternalServiceException, DataValidationServiceException;
}
