package de.quastenflossler.snail.service.issue.domain;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;

public interface SmartIssue extends BasicIssue {

    String createPdf() throws InternalServiceException, DataValidationServiceException;
}
