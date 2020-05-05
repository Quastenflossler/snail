package de.quastenflossler.snail.service.domain;

import de.quastenflossler.snail.service.transfer.BasicEpicTO;
import de.quastenflossler.snail.service.transfer.BasicIssueTO;

public interface DomainObjectMapper {

    BasicEpic createEpic(BasicEpicTO epicTO);

    BasicIssue createIssue(BasicIssueTO issueTO);

    SmartIssue createSmartIssue(BasicIssueTO issueTO);
}
