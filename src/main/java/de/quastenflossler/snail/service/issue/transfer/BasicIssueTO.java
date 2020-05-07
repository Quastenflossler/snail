package de.quastenflossler.snail.service.issue.transfer;

import de.quastenflossler.snail.service.core.transfer.AbstractTransferObject;

public class BasicIssueTO extends AbstractTransferObject {

    private static final long serialVersionUID = 7242775773045112714L;

    private String key;
    private String summary;
    private BasicEpicTO epic;
    private String description;
    private String stakeholder;
    private Integer storyPoints;
    private String acceptanceCriteria;
    private String plannedSprint;
    private String deadline;

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    public BasicEpicTO getEpic() {
        return epic;
    }

    public void setEpic(final BasicEpicTO epic) {
        this.epic = epic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(final String stakeholder) {
        this.stakeholder = stakeholder;
    }

    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(final Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(final String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getPlannedSprint() {
        return plannedSprint;
    }

    public void setPlannedSprint(final String plannedSprint) {
        this.plannedSprint = plannedSprint;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(final String deadline) {
        this.deadline = deadline;
    }
}
