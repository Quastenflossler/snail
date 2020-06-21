package de.quastenflossler.snail.ui.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class SnailFxmlManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnailFxmlManager.class);
    private static final String FXML_DIR = "/fxml/";
    private static final String I18N_BASE_NAME = "lang.snail_lang";
    private static final String GLOBAL_STYLESHEET_FILE = "/css/style.css";

    private static SnailFxmlManager INSTANCE;

    private ConfigurableApplicationContext applicationContext;
    private Locale activeLocale;

    private SnailFxmlManager() {
        // singleton ...
    }

    public void setApplicationContext(final ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Locale getActiveLocale() {
        return activeLocale;
    }

    public void setActiveLocale(final Locale locale) {

        activeLocale = locale;
        LOGGER.debug("Active locale is {}", activeLocale);
    }

    public static SnailFxmlManager getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new SnailFxmlManager();
        }

        return INSTANCE;
    }

    public Map<String, Pane> loadPanesByFxml() throws IOException {

        LOGGER.debug("[START] preloading panes...");

        Map<String, Pane> panesByName = new HashMap<>();

        for (HomeScreenPanes panes : HomeScreenPanes.values()) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_DIR + panes.getFxmlFile()));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            fxmlLoader.setResources(ResourceBundle.getBundle(I18N_BASE_NAME, activeLocale));

            Pane loadedPane = fxmlLoader.load();
            loadedPane.getStylesheets().add(getClass().getResource(GLOBAL_STYLESHEET_FILE).toExternalForm());

            panesByName.put(panes.getName(), loadedPane);

            LOGGER.debug("Scene \"{}\" loaded successfully", panes);
        }

        LOGGER.debug("[END] preloading panes...");
        return panesByName;
    }
}
