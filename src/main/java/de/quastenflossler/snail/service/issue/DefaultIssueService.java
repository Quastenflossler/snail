package de.quastenflossler.snail.service.issue;

import de.quastenflossler.snail.service.core.domain.DomainObjectMapper;
import de.quastenflossler.snail.service.core.domain.impl.DefaultDomainObjectMapper;
import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.issue.domain.SmartIssue;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;

import javax.annotation.Resource;
import javax.inject.Named;

@Named(value = DefaultIssueService.RESOURCE_NAME)
public class DefaultIssueService implements IssueService {

    public static final String RESOURCE_NAME = "DefaultIssueService";

    @Resource(name = DefaultDomainObjectMapper.RESOURCE_NAME)
    private DomainObjectMapper domainObjectMapper;

    @Override
    public String createPdfFromIssue(final BasicIssueTO issueTO, final String exportPath) throws InternalServiceException, DataValidationServiceException {

        SmartIssue issue = domainObjectMapper.createSmartIssue(issueTO);
        return issue.createPdf(exportPath);
    }
}
