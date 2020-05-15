package de.quastenflossler.snail.service.issue.domain.impl;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.issue.domain.SmartIssue;
import de.quastenflossler.snail.service.issue.domain.UserStoryPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Scope(value = "prototype")
public class DefaultSmartIssue extends DefaultBasicIssue implements SmartIssue {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSmartIssue.class);

    @Resource(name = DefaultUserStoryPrinter.RESOURCE_NAME)
    private UserStoryPrinter userStoryPrinter;

    @Override
    public String createPdf() throws InternalServiceException, DataValidationServiceException {

        return userStoryPrinter.createPdf(this, UserStoryLayout.DOUBLE_SIDED);
    }

}
