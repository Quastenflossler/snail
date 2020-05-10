package de.quastenflossler.snail.ui.model;

import javafx.beans.property.*;

import javax.inject.Named;

@Named(value = PrintIssueModel.RESOURCE_NAME)
public class PrintIssueModel {

    public static final String RESOURCE_NAME = "PrintIssueModel";

    private final StringProperty epicKey = new SimpleStringProperty();
    private final StringProperty epicName = new SimpleStringProperty();
    private final StringProperty issueKey = new SimpleStringProperty();
    private final StringProperty issueTitle = new SimpleStringProperty();
    private final StringProperty issueDescription = new SimpleStringProperty();
    private final ObjectProperty<Integer> storyPoints = new SimpleObjectProperty<>();
    private final StringProperty acceptanceCriteria = new SimpleStringProperty();
    private final StringProperty stakeholder = new SimpleStringProperty();
    private final StringProperty plannedSprint = new SimpleStringProperty();
    private final StringProperty deadline = new SimpleStringProperty();

    public String getEpicKey() {
        return epicKey.get();
    }

    public StringProperty epicKeyProperty() {
        return epicKey;
    }

    public void setEpicKey(final String epicKey) {
        this.epicKey.set(epicKey);
    }

    public String getEpicName() {
        return epicName.get();
    }

    public StringProperty epicNameProperty() {
        return epicName;
    }

    public void setEpicName(final String epicName) {
        this.epicName.set(epicName);
    }

    public String getIssueKey() {
        return issueKey.get();
    }

    public StringProperty issueKeyProperty() {
        return issueKey;
    }

    public void setIssueKey(final String issueKey) {
        this.issueKey.set(issueKey);
    }

    public String getIssueTitle() {
        return issueTitle.get();
    }

    public StringProperty issueTitleProperty() {
        return issueTitle;
    }

    public void setIssueTitle(final String issueTitle) {
        this.issueTitle.set(issueTitle);
    }

    public String getIssueDescription() {
        return issueDescription.get();
    }

    public StringProperty issueDescriptionProperty() {
        return issueDescription;
    }

    public void setIssueDescription(final String issueDescription) {
        this.issueDescription.set(issueDescription);
    }

    public Integer getStoryPoints() {
        return storyPoints.get();
    }

    public ObjectProperty<Integer> storyPointsProperty() {
        return storyPoints;
    }

    public void setStoryPoints(final Integer storyPoints) {
        this.storyPoints.set(storyPoints);
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria.get();
    }

    public StringProperty acceptanceCriteriaProperty() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(final String acceptanceCriteria) {
        this.acceptanceCriteria.set(acceptanceCriteria);
    }

    public String getStakeholder() {
        return stakeholder.get();
    }

    public StringProperty stakeholderProperty() {
        return stakeholder;
    }

    public void setStakeholder(final String stakeholder) {
        this.stakeholder.set(stakeholder);
    }

    public String getPlannedSprint() {
        return plannedSprint.get();
    }

    public StringProperty plannedSprintProperty() {
        return plannedSprint;
    }

    public void setPlannedSprint(final String plannedSprint) {
        this.plannedSprint.set(plannedSprint);
    }

    public String getDeadline() {
        return deadline.get();
    }

    public StringProperty deadlineProperty() {
        return deadline;
    }

    public void setDeadline(final String deadline) {
        this.deadline.set(deadline);
    }
}
