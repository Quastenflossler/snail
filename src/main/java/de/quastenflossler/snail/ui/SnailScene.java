package de.quastenflossler.snail.ui;

public enum SnailScene {

    MAIN("main scene", "mainScene.fxml");

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
