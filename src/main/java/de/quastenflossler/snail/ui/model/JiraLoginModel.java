package de.quastenflossler.snail.ui.model;

import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Named;

@Named(value = JiraLoginModel.RESOURCE_NAME)
public class JiraLoginModel {

    public static final String RESOURCE_NAME = "JiraLoginModel";

    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty sessionId = new SimpleStringProperty();

    private final StringProperty jql = new SimpleStringProperty();
    private ObservableList<JiraIssueModel> issues = FXCollections.observableArrayList();

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(final String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(final String password) {
        this.password.set(password);
    }

    public String getSessionId() {
        return sessionId.get();
    }

    public StringProperty sessionIdProperty() {
        return sessionId;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId.set(sessionId);
    }

    public ObservableList<JiraIssueModel> getIssues() {
        return issues;
    }

    public void setIssues(final ObservableList<JiraIssueModel> issues) {
        this.issues = issues;
    }

    public void addIssue(final BasicIssueTO issueTO) {

        JiraIssueModel model = new JiraIssueModel();
        model.setIssueKey(issueTO.getKey());
        model.setIssueTitle(issueTO.getSummary());

        issues.add(model);
    }

    public String getJql() {
        return jql.get();
    }

    public StringProperty jqlProperty() {
        return jql;
    }

    public void setJql(final String jql) {
        this.jql.set(jql);
    }
}
