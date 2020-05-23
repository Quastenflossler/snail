package de.quastenflossler.snail.ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JiraIssueModel {

    private final StringProperty issueKey = new SimpleStringProperty();
    private final StringProperty issueTitle = new SimpleStringProperty();

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
}
