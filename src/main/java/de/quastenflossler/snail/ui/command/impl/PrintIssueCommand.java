package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.issue.DefaultIssueService;
import de.quastenflossler.snail.service.issue.IssueService;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;
import de.quastenflossler.snail.ui.command.BasicCommand;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Scope(value = "prototype")
public class PrintIssueCommand implements BasicCommand {

    private BasicIssueTO issueTO;

    @Resource(name = DefaultIssueService.RESOURCE_NAME)
    private IssueService issueService;

    public BasicIssueTO getIssueTO() {
        return issueTO;
    }

    public void setIssueTO(final BasicIssueTO issueTO) {
        this.issueTO = issueTO;
    }

    @Override
    public void execute() throws InternalServiceException {

        issueService.printIssue(issueTO);
    }
}
