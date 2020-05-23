package de.quastenflossler.snail.ui.stage;

public enum SnailScene {

    HOMESCREEN("homescreen scene", "homescreenScene.fxml"),
    PRINT_ISSUE("print issue scene", "printissue/printIssueScene.fxml"),
    PRINT_ISSUE_WITH_PREVIEW("print issue scene with preview", "printissue/printIssueWithPreviewScene.fxml"),
    PRINT_JIRA_ISSUE("print jira issue scene", "printissue/printJiraIssueScene.fxml"),
    SPRINT_CALENDAR("sprint calendar scene", "sprintcalendar/sprintCalendarScene.fxml"),
    SETTINGS("settings scene", "userpref/userprefScene.fxml");

    private String name;
    private String fxmlFile;

    SnailScene(final String name, final String fxmlFile) {

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
