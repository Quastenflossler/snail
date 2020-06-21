package de.quastenflossler.snail.ui.stage;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Window;

public class UiHelper {

    public static void centerOnScreen(final Window window) {

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
    }
}
