package de.quastenflossler.snail.ui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class UiHelper {

    public static void centerOnScreen(final Window window) {

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
    }

    public static Stage loadStageByFxml(final String fxmlFile, final Class<?> parentClass) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(parentClass.getResource(fxmlFile));

        return fxmlLoader.load();
    }
}
