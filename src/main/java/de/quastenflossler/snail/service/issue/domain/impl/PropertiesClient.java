package de.quastenflossler.snail.service.issue.domain.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesClient {
    public static final String CONSUMER_KEY = "consumer_key";
    public static final String PRIVATE_KEY = "private_key";
    public static final String REQUEST_TOKEN = "request_token";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String SECRET = "secret";
    public static final String JIRA_HOME = "jira_home";


    private final static Map<String, String> DEFAULT_PROPERTY_VALUES = ImmutableMap.<String, String>builder()
            .put(JIRA_HOME, "http://zedasago01.1net.dom:8080")
            .put(CONSUMER_KEY, "OauthKey")
            .put(PRIVATE_KEY, "MIICXAIBAAKBgQC7620od+ihMcJip67aZx8pJqihijYZ+bEK5wattToOAVpl6rLcOtCEzJs5hRFg0L1sky356s9lZQFY4Ap+3qAdwv8btvwcqjKwig0cHgIQkj3KTGL+lVKTUok+PWVfPSYLq5anAeKLYvQ5FEMnLKzesgaYEbNDVariFuhZJcTbywIDAQABAoGASJ0UNQsAjSkZH94DpskqNVCG4MTzWt4tFzVmU0EwCbFkKPWj342qfWZCpNtSd8KxL38/yM5/DYMWHi2UYKnN3IhLI7OIUaUudZwDHT0HJ/51C5SfjNhA1swGki2X88EhZ1tzjzth2SeveJMMb9ngL6JUf8F2rQ90L9C2QBbIcqECQQDrWtDUqNzPpu++5NmLdIH+f8W+jWbDHO57Me8ymmw8zD/h67J/L3Nqz1WPbpb5XP0pLerJOqr3N/PRSUgYAWGvAkEAzGdlYg/pYu9wn+IsAf2N35bvobzU10mWo7DFb9xxwdM4Y9xr7bj96b4ZEi8mo6SJ4mkiKeB9dR7z4isg70T6pQJBAJkGBC+w11kALnNMspF0oCzMANNjdsQ3pGtfTSn2s5dYKdUHfZgqTv3MbtICUpExv7ytiTFrPsvcil+yKPjKtSsCQCojutP/ocbixB5CG36BjmklDdwTOPH1Wtf+ToXkNZOW/w4fWCTXOYmhxjtoexfLiR2jPQbTICowKmlbKNwLJX0CQGuQBZSB9LMHBm5WTGaFvlYjCddK0jeIe+tmg04z5jYE+Wa+SA2AbYK1K0PDWcflJUDqKTx15dSdK+zyJHlYQic=")
            .build();

    private final String fileUrl;
    private final String propFileName = "config.properties";

    public PropertiesClient() throws Exception {
        fileUrl = "./" + propFileName;
    }

    public Map<String, String> getPropertiesOrDefaults() {
        try {
            Map<String, String> map = toMap(tryGetProperties());
            map.putAll(Maps.difference(map, DEFAULT_PROPERTY_VALUES).entriesOnlyOnRight());
            return map;
        } catch (FileNotFoundException e) {
            tryCreateDefaultFile();
            return new HashMap<>(DEFAULT_PROPERTY_VALUES);
        } catch (IOException e) {
            return new HashMap<>(DEFAULT_PROPERTY_VALUES);
        }
    }

    private Map<String, String> toMap(Properties properties) {
        return properties.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(o -> o.getKey().toString(), t -> t.getValue().toString()));
    }

    private Properties toProperties(Map<String, String> propertiesMap) {
        Properties properties = new Properties();
        propertiesMap.entrySet()
                .stream()
                .forEach(entry -> properties.put(entry.getKey(), entry.getValue()));
        return properties;
    }

    private Properties tryGetProperties() throws IOException {
        InputStream inputStream = new FileInputStream(new File(fileUrl));
        Properties prop = new Properties();
        prop.load(inputStream);
        return prop;
    }

    public void savePropertiesToFile(Map<String, String> properties) {
        OutputStream outputStream = null;
        File file = new File(fileUrl);

        try {
            outputStream = new FileOutputStream(file);
            Properties p = toProperties(properties);
            p.store(outputStream, null);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            closeQuietly(outputStream);
        }
    }

    public void tryCreateDefaultFile() {
        System.out.println("Creating default properties file: " + propFileName);
        tryCreateFile().ifPresent(file -> savePropertiesToFile(DEFAULT_PROPERTY_VALUES));
    }

    private Optional<File> tryCreateFile() {
        try {
            File file = new File(fileUrl);
            file.createNewFile();
            return Optional.of(file);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            // ignored
        }
    }
}
