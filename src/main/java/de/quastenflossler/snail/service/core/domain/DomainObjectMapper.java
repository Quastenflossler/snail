package de.quastenflossler.snail.service.core.domain;

import de.quastenflossler.snail.service.issue.domain.BasicEpic;
import de.quastenflossler.snail.service.issue.domain.BasicIssue;
import de.quastenflossler.snail.service.issue.domain.SmartIssue;
import de.quastenflossler.snail.service.issue.transfer.BasicEpicTO;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;

public interface DomainObjectMapper {

    BasicEpic createEpic(BasicEpicTO epicTO);

    BasicIssue createIssue(BasicIssueTO issueTO);

    SmartIssue createSmartIssue(BasicIssueTO issueTO);
}
