package de.quastenflossler.snail;

import de.quastenflossler.snail.config.SpringConfig;
import de.quastenflossler.snail.config.SpringContextFactory;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.ui.command.impl.SetupApplicationCommand;
import de.quastenflossler.snail.ui.stage.HomeScreenController;
import de.quastenflossler.snail.ui.stage.HomeScreenPanes;
import de.quastenflossler.snail.ui.stage.SnailFxmlManager;
import de.quastenflossler.snail.ui.stage.UiHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootApplication
@Import(SpringConfig.class)
public class SnailJavaFxClient extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnailJavaFxClient.class);

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws InternalServiceException {

        LOGGER.debug("[START] Client will be initialized...");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        LOGGER.debug("context will be initialized");
        applicationContext = (ConfigurableApplicationContext) SpringContextFactory.getApplicationContext();
        LOGGER.debug("context is initialized");

        SnailFxmlManager.getInstance().setApplicationContext(applicationContext);

        SetupApplicationCommand setupApplicationCommand = SpringConfig.getBean(SetupApplicationCommand.class);
        setupApplicationCommand.execute();

        stopWatch.stop();
        LOGGER.debug("[END] Client will be initialized... | Duration: {}ms", stopWatch.getTime());
    }

    @Override
    public void stop() {

        applicationContext.stop();
    }

    @Override
    public void start(final Stage primaryStage) {

        try {

            Map<String, Pane> loadPanesByFxml = SnailFxmlManager.getInstance().loadPanesByFxml();
            HomeScreenController.getInstance().setPaneMap(loadPanesByFxml);

            Scene scene = new Scene(new BorderPane(), 1024, 768);
            HomeScreenController.getInstance().setMainScene(scene);
            HomeScreenController.getInstance().activate(HomeScreenPanes.HOMESCREEN.getName());

            primaryStage.setTitle("Snail");
            primaryStage.setResizable(true);
            primaryStage.setScene(scene);
            primaryStage.toFront();
            primaryStage.show();
            UiHelper.centerOnScreen(primaryStage);

        } catch (Exception e) {

            LOGGER.error("Error during application startup!", e);
        }
    }

    public static void main(final String[] args) {

        try {

            launch(args);

        } catch (Exception e) {
            LOGGER.error("Error during application startup!", e);
        }
    }
}
