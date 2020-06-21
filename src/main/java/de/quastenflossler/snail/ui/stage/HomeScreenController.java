package de.quastenflossler.snail.ui.stage;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class HomeScreenController {

    private static HomeScreenController INSTANCE;
    private Map<String, Pane> paneMap = new HashMap<>();
    private Scene mainScene;

    private HomeScreenController() {
        // singleton
    }

    public static HomeScreenController getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new HomeScreenController();
        }

        return INSTANCE;
    }

    public Map<String, Pane> getPaneMap() {
        return paneMap;
    }

    public void setPaneMap(final Map<String, Pane> paneMap) {
        this.paneMap = paneMap;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(final Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void activate(final String name) {
        mainScene.setRoot(paneMap.get(name));
    }
}
