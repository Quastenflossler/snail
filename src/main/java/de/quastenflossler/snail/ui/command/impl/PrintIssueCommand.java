package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
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
    private String exportPath;

    @Resource(name = DefaultIssueService.RESOURCE_NAME)
    private IssueService issueService;

    public BasicIssueTO getIssueTO() {
        return issueTO;
    }

    public void setIssueTO(final BasicIssueTO issueTO) {
        this.issueTO = issueTO;
    }

    public String getExportPath() {
        return exportPath;
    }

    public void setExportPath(final String exportPath) {
        this.exportPath = exportPath;
    }

    @Override
    public void execute() throws InternalServiceException, DataValidationServiceException {

        issueService.createPdfFromIssue(issueTO, exportPath);
    }
}
