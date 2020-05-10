package de.quastenflossler.snail.ui.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class SnailStageDirector {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnailStageDirector.class);
    private static final String FXML_DIR = "/fxml/";
    private static final String I18N_BASE_NAME = "lang.snail_lang";

    private static SnailStageDirector INSTANCE;

    private ConfigurableApplicationContext applicationContext;

    private Stage primaryStage;
    private Stage activeStage;
    private SnailScene activeScene;
    private Locale activeLocale;
    private Map<String, Scene> scenes = new HashMap<>();

    private SnailStageDirector() {
        // singleton ...
    }

    public void setApplicationContext(final ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public SnailScene getActiveScene() {
        return activeScene;
    }

    public Locale getActiveLocale() {
        return activeLocale;
    }

    public void setActiveLocale(final Locale locale) {

        activeLocale = locale;
        LOGGER.debug("Active locale is {}", activeLocale);
    }

    public static SnailStageDirector getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new SnailStageDirector();
        }

        return INSTANCE;
    }

    public Scene getScene(final SnailScene scene) {
        return scenes.get(scene.getName());
    }

    public void init() throws IOException {

        LOGGER.debug("[START] initialization of control manager");

        loadScenesByFxml();

        LOGGER.debug("[END] initialization of control manager");
    }

    public void showScene(final Stage stage, final SnailScene scene) {

        stage.setScene(scenes.get(scene.getName()));
        stage.sizeToScene();
        stage.show();
        stage.toFront();
        UiHelper.centerOnScreen(stage);

        primaryStage = stage;
        activeScene = scene;
    }

    public void showScene(final SnailScene scene) {

        primaryStage.setScene(scenes.get(scene.getName()));
        primaryStage.sizeToScene();
        activeScene = scene;
    }

    public void showStage(final SnailStage stage, final SnailScene scene) {

        Stage stageObject = new Stage(stage.getStyle());
        stageObject.setTitle(stage.getTitle());
        stageObject.setResizable(false);
        stageObject.initModality(stage.getModality());
        stageObject.setMinWidth(stage.getMinWidth());
        stageObject.setMinHeight(stage.getMinHeight());

        showScene(stageObject, scene);
        activeStage = stageObject;
    }

    public void closeActiveStage() {

        this.activeStage.close();
        this.activeStage = primaryStage;
    }

    public void replaceStage(final Stage newStage, final SnailScene scene, final Locale newLocale) throws IOException {

        setActiveLocale(newLocale);
        loadScenesByFxml();
        replaceStage(newStage, scene);
    }

    public void replaceStage(final Stage newStage, final SnailScene scene) {

        newStage.setScene(scenes.get(scene.getName()));
        newStage.sizeToScene();
        newStage.show();
        newStage.toFront();
        UiHelper.centerOnScreen(newStage);

        primaryStage.close();
        primaryStage = newStage;
        activeScene = scene;
    }

    private void loadScenesByFxml() throws IOException {

        LOGGER.debug("[START] preloading scenes...");

        scenes.clear();

        for (SnailScene scene : SnailScene.values()) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_DIR + scene.getFxmlFile()));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            fxmlLoader.setResources(ResourceBundle.getBundle(I18N_BASE_NAME, activeLocale));
            scenes.put(scene.getName(), fxmlLoader.load());

            LOGGER.debug("Scene \"{}\" loaded successfully", scene);
        }

        LOGGER.debug("[END] preloading scenes...");
    }
}
