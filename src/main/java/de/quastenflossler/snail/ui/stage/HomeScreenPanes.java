package de.quastenflossler.snail.ui.stage;

public enum HomeScreenPanes {

    HOMESCREEN("homescreen", "homeScreenBorderPane.fxml"),
    SPRINT_CALENDAR("sprint calendar", "sprintcalendar/sprintCalendarBorderPane.fxml"),
    PRINT_JIRA_ISSUE("print jira issue", "printissue/printJiraIssueBorderPane.fxml"),
    PRINT_ISSUE("print issue", "printissue/printIssueBorderPane.fxml"),
    PRINT_ISSUE_WITH_PREVIEW("print issue with preview", "printissue/printIssueWithPreviewBorderPane.fxml"),
    USER_SETTINGS("user settings", "userpref/userprefPane.fxml");

    private String name;
    private String fxmlFile;

    HomeScreenPanes(final String name, final String fxmlFile) {

        this.name = name;
        this.fxmlFile = fxmlFile;
    }

    public String getName() {
        return name;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

    @Override
    public String toString() {
        return name;
    }
}
