package de.quastenflossler.snail.service.userpref.domain;

import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

@Named(value = PropertyFileUserPreferencesDoDao.RESOURCE_NAME)
public class PropertyFileUserPreferencesDoDao implements UserPreferencesDao {

    public static final String RESOURCE_NAME = "PropertyFileUserPreferencesDoDao";

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFileUserPreferencesDoDao.class);
    private static final String SNAIL_PROPERTIES_FILE = "snail.properties";
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_EXPORT_PATH = "exportpath";

    private Properties properties;

    @Override
    public UserPreferencesTO findUserPreferences() throws InternalServiceException {

        try {

            loadPropertiesFromFile();

            UserPreferencesTO preferences = new UserPreferencesTO();
            preferences.setLanguage(loadLocale());
            preferences.setExportPath(properties.getProperty(KEY_EXPORT_PATH));

            return preferences;

        } catch (IOException | URISyntaxException e) {
            throw new InternalServiceException("Loading of user preferences failed", e);
        }
    }

    @Override
    public void saveUserPreferences(final UserPreferencesTO preferences) throws InternalServiceException {

        URL propertyFilePath = this.getClass().getClassLoader().getResource(SNAIL_PROPERTIES_FILE);

        try {

            File propertyFile = new File(propertyFilePath.toURI());

            try (FileOutputStream outputStream = new FileOutputStream(propertyFile.toString())) {

                loadPropertiesFromFile();
                properties.setProperty(KEY_LANGUAGE, preferences.getLanguage().toLanguageTag());
                properties.setProperty(KEY_EXPORT_PATH, preferences.getExportPath());
                properties.store(outputStream, null);
            }

        } catch (URISyntaxException | IOException e) {
            throw new InternalServiceException("Error during saving of user preferences", e);
        }
    }

    private void loadPropertiesFromFile() throws IOException, URISyntaxException {

//        if (properties != null) {
//            return;
//        }

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        URL propertyFileUrl = this.getClass().getClassLoader().getResource(SNAIL_PROPERTIES_FILE);
        File propertyFile = new File(propertyFileUrl.toURI());

        try (InputStream inputStream = new FileInputStream(propertyFile);) {

            properties = new Properties();
            properties.load(inputStream);

            String exportPath = properties.getProperty(KEY_EXPORT_PATH);

            if (StringUtils.isEmpty(exportPath)) {
                properties.setProperty(KEY_EXPORT_PATH, System.getProperty("user.home"));
            }

            stopwatch.stop();
            LOGGER.debug("Properties {} loaded from {} in {}ms", properties, propertyFile.toString(), stopwatch.getTime());
        }
    }

    private Locale loadLocale() {

        String language = properties.getProperty(KEY_LANGUAGE);
        return Locale.forLanguageTag(language);
    }
}
