package de.quastenflossler.snail.service.domain;

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
}
