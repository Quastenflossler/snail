package de.quastenflossler.snail.service.userpref.domain;

import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.*;
import java.util.Locale;
import java.util.Properties;

@Named(value = PropertyFileUserPreferencesDoDao.RESOURCE_NAME)
public class PropertyFileUserPreferencesDoDao implements UserPreferencesDao {

    public static final String RESOURCE_NAME = "PropertyFileUserPreferencesDoDao";

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFileUserPreferencesDoDao.class);
    private static final String DEFAULT_SNAIL_PROPERTIES_FILE = "default-snail.properties";
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_EXPORT_PATH = "exportpath";

    private Properties properties;

    @Override
    public UserPreferencesTO findUserPreferences() throws InternalServiceException {

        try {

            loadPropertiesFromFile();

            UserPreferencesTO preferences = new UserPreferencesTO();
            preferences.setLanguage(getLocale());
            preferences.setExportPath(properties.getProperty(KEY_EXPORT_PATH));

            return preferences;

        } catch (IOException e) {
            throw new InternalServiceException("Loading of user preferences failed", e);
        }
    }

    @Override
    public void saveUserPreferences(final UserPreferencesTO preferences) throws InternalServiceException {

        try {

            File propertyFile = new File("./snail.properties");

            LOGGER.debug("properties will be saved to {}", propertyFile.toString());

            try (FileOutputStream outputStream = new FileOutputStream(propertyFile.toString())) {

                properties.setProperty(KEY_LANGUAGE, preferences.getLanguage().toLanguageTag());
                properties.setProperty(KEY_EXPORT_PATH, preferences.getExportPath());
                properties.store(outputStream, null);
            }

        } catch (IOException e) {
            throw new InternalServiceException("Error during saving of user preferences", e);
        }
    }

    private void loadPropertiesFromFile() throws IOException {

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        File bla = new File("./snail.properties");

        if (bla.exists() && !bla.isDirectory()) {

            try (InputStream inputStream = new FileInputStream("./snail.properties")) {

                properties = new Properties();
                properties.load(inputStream);
            }

            stopwatch.stop();
            LOGGER.debug("User properties {} loaded in {}ms", properties, stopwatch.getTime());
            return;
        }

        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_SNAIL_PROPERTIES_FILE)) {

            properties = new Properties();
            properties.load(inputStream);

            String exportPath = properties.getProperty(KEY_EXPORT_PATH);

            if (StringUtils.isEmpty(exportPath)) {
                properties.setProperty(KEY_EXPORT_PATH, System.getProperty("user.home"));
            }

            stopwatch.stop();
            LOGGER.debug("Default properties {} loaded in {}ms", properties, stopwatch.getTime());
        }
    }

    private Locale getLocale() {

        String language = properties.getProperty(KEY_LANGUAGE);
        return Locale.forLanguageTag(language);
    }
}
