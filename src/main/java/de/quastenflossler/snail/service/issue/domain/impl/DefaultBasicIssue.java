package de.quastenflossler.snail.service.issue.domain.impl;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.issue.domain.BasicEpic;
import de.quastenflossler.snail.service.issue.domain.BasicIssue;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class DefaultBasicIssue implements BasicIssue {

    private String key;
    private String summary;
    private BasicEpic epic;
    private String description;
    private String stakeholder;
    private Integer storyPoints;
    private String acceptanceCriteria;
    private String plannedSprint;
    private String deadline;

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    @Override
    public BasicEpic getEpic() {
        return epic;
    }

    public void setEpic(final BasicEpic epic) {
        this.epic = epic;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(final String stakeholder) {
        this.stakeholder = stakeholder;
    }

    @Override
    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(final Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    @Override
    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(final String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    @Override
    public String getPlannedSprint() {
        return plannedSprint;
    }

    public void setPlannedSprint(final String plannedSprint) {
        this.plannedSprint = plannedSprint;
    }

    @Override
    public String getDeadline() {
        return deadline;
    }

    @Override
    public void validate() {

    }

    public void setDeadline(final String deadline) {
        this.deadline = deadline;
    }
}
