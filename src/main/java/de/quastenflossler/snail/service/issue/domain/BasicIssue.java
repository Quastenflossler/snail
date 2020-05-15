package de.quastenflossler.snail.service.issue.domain;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;

public interface BasicIssue {

    String getKey();

    String getSummary();

    BasicEpic getEpic();

    String getDescription();

    String getStakeholder();

    Integer getStoryPoints();

    String getAcceptanceCriteria();

    String getPlannedSprint();

    String getDeadline();

    void validate() throws DataValidationServiceException;
}
