package de.quastenflossler.snail.service.domain.impl;

import de.quastenflossler.snail.service.domain.BasicEpic;
import de.quastenflossler.snail.service.domain.BasicIssue;
import de.quastenflossler.snail.service.domain.DomainObjectMapper;
import de.quastenflossler.snail.service.domain.SmartIssue;
import de.quastenflossler.snail.service.transfer.BasicEpicTO;
import de.quastenflossler.snail.service.transfer.BasicIssueTO;

import javax.inject.Named;

@Named(value = DefaultDomainObjectMapper.RESOURCE_NAME)
public class DefaultDomainObjectMapper implements DomainObjectMapper {

    public static final String RESOURCE_NAME = "DefaultDomainObjectMapper";

    @Override
    public BasicEpic createEpic(final BasicEpicTO epicTO) {

        DefaultBasicEpic epic = new DefaultBasicEpic();

        epic.setKey(epicTO.getKey());
        epic.setDescription(epicTO.getDescription());

        return epic;
    }

    @Override
    public BasicIssue createIssue(final BasicIssueTO issueTO) {

        DefaultBasicIssue issue = new DefaultBasicIssue();

        issue.setKey(issueTO.getKey());
        issue.setSummary(issueTO.getSummary());
        issue.setEpic(createEpic(issueTO.getEpic()));
        issue.setDescription(issueTO.getDescription());
        issue.setStakeholder(issueTO.getStakeholder());
        issue.setStoryPoints(issueTO.getStoryPoints());
        issue.setAcceptanceCriteria(issueTO.getAcceptanceCriteria());
        issue.setPlannedSprint(issueTO.getPlannedSprint());
        issue.setDeadline(issueTO.getDeadline());

        return issue;
    }

    @Override
    public SmartIssue createSmartIssue(final BasicIssueTO issueTO) {

        DefaultSmartIssue issue = new DefaultSmartIssue();

        issue.setKey(issueTO.getKey());
        issue.setSummary(issueTO.getSummary());
        issue.setEpic(createEpic(issueTO.getEpic()));
        issue.setDescription(issueTO.getDescription());
        issue.setStakeholder(issueTO.getStakeholder());
        issue.setStoryPoints(issueTO.getStoryPoints());
        issue.setAcceptanceCriteria(issueTO.getAcceptanceCriteria());
        issue.setPlannedSprint(issueTO.getPlannedSprint());
        issue.setDeadline(issueTO.getDeadline());

        return issue;
    }
}
