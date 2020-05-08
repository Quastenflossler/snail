package de.quastenflossler.snail.service.userpref.domain;

import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

@Named(value = PropertyFileUserPreferencesDoDao.RESOURCE_NAME)
public class PropertyFileUserPreferencesDoDao implements UserPreferencesDao {

    public static final String RESOURCE_NAME = "PropertyFileUserPreferencesDoDao";

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFileUserPreferencesDoDao.class);
    private static final String SNAIL_PROPERTIES_FILE = "snail.properties";
    private static final String KEY_LANGUAGE = "language";

    private Properties properties;

    @Override
    public UserPreferencesTO findUserPreferences() throws InternalServiceException {

        try {

            loadPropertiesFromFile();

            UserPreferencesTO preferences = new UserPreferencesTO();
            preferences.setLanguage(loadLocale());

            return preferences;

        } catch (IOException e) {
            throw new InternalServiceException("Loading of user preferences failed", e);
        }
    }

    @Override
    public void saveUserPreferences(final UserPreferencesTO preferences) throws InternalServiceException {

        URL propertyFilePath = this.getClass().getClassLoader().getResource(SNAIL_PROPERTIES_FILE);

        try {

            File test = new File(propertyFilePath.toURI());

            try (FileOutputStream outputStream = new FileOutputStream(test.toString())) {

                loadPropertiesFromFile();
                properties.setProperty(KEY_LANGUAGE, preferences.getLanguage().toLanguageTag());
                properties.store(outputStream, null);

            }

        } catch (URISyntaxException | IOException e) {
            throw new InternalServiceException("Error during saving of user preferences", e);
        }
    }

    private void loadPropertiesFromFile() throws IOException {

        if (properties != null) {
            return;
        }

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(SNAIL_PROPERTIES_FILE)) {

            properties = new Properties();
            properties.load(inputStream);

            stopwatch.stop();
            LOGGER.debug("Properties loaded in {}ms", stopwatch.getTime());
        }
    }

    private Locale loadLocale() {

        String language = properties.getProperty(KEY_LANGUAGE);
        return Locale.forLanguageTag(language);
    }
}
