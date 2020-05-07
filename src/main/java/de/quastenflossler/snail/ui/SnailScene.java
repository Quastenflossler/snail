package de.quastenflossler.snail.ui;

public enum SnailScene {

    HOMESCREEN("homescreen scene", "homescreenScene.fxml"),
    PRINT_ISSUE("print issue scene", "printIssueScene.fxml"),
    SETTINGS("settings scene", "userprefScene.fxml");

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
