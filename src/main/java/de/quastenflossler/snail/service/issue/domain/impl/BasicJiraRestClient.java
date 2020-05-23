package de.quastenflossler.snail.service.issue.domain.impl;

import de.quastenflossler.snail.service.issue.domain.JiraRestClient;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@Named(value = BasicJiraRestClient.RESOURCE_NAME)
public class BasicJiraRestClient implements JiraRestClient {

    public static final String RESOURCE_NAME = "BasicJiraRestClient";

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicJiraRestClient.class);

    private static final String LOGIN_URL = "auth/1/session";
    private static final int RESPONSE_CODE_OK = 200;
    private static final int RESPONSE_CODE_UNAUTHORIZED = 401;

    private static final String TOKEN = "qcVIEOeB4XgzfEapiKzi8571";

    private String baseUrl;
    private String sessionId;

    @Override
    public void setUrl(final String jiraUrl) {
        this.baseUrl = jiraUrl;
    }

    @Override
    public String login(final String username, final String password) {

        String loginResponse = null;

        try {

            URL jiraLoginUrl = new URL(baseUrl + LOGIN_URL);
            LOGGER.debug("jira url for login is {}", jiraLoginUrl);

            HttpURLConnection connection = (HttpURLConnection) jiraLoginUrl.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String userCredentials = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
            connection.setRequestProperty ("Authorization", basicAuth);

            connection.setUseCaches(false);
            connection.setDoInput(true);

//            String input = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
//
//            OutputStream outputStream = connection.getOutputStream();
//            outputStream.write(input.getBytes());
//            outputStream.flush();

            int responseCode = connection.getResponseCode();
            LOGGER.debug("Response code: {}", responseCode);

            if (responseCode == RESPONSE_CODE_OK) {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                String output;
                while ((output = bufferedReader.readLine()) != null) {
                    loginResponse += output;
                }
            }

            connection.disconnect();

        } catch (Exception e) {
            LOGGER.error("holy shit!", e);
            loginResponse = "ERROR";
        }

        LOGGER.debug("Response: {}", loginResponse);

        return parseSessionIdFromResponse(loginResponse);
    }

    @Override
    public BasicIssueTO findIssue(final String issueKey, final String username, final String password) {

        String loginResponse = null;

        try {

            URL jiraLoginUrl = new URL(baseUrl + "/rest/api/latest/issue/" + issueKey);
            LOGGER.debug("jira url for login is {}", jiraLoginUrl);

            HttpURLConnection connection = (HttpURLConnection) jiraLoginUrl.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            String userCredentials = username + ":" + password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
            connection.setRequestProperty("Authorization", basicAuth);

            connection.setUseCaches(false);
            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            LOGGER.debug("Response code: {}", responseCode);

            if (responseCode == RESPONSE_CODE_OK) {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                String output;
                while ((output = bufferedReader.readLine()) != null) {
                    loginResponse += output;
                }
            }

            connection.disconnect();

            if (loginResponse != null) {

                JSONObject parser = new JSONObject(loginResponse);
                JSONObject foundIssueKey = (JSONObject) parser.get("key");

                BasicIssueTO issueTO = new BasicIssueTO();
                issueTO.setKey((String) foundIssueKey.get("value"));
                issueTO.setSummary("So geil!");

                return  issueTO;
            }

        } catch (Exception e) {

            LOGGER.error("holy shit!", e);
            loginResponse = "ERROR";
        }

        return null;
    }

    private static String parseSessionIdFromResponse(final String input) {

        try {

            JSONObject parser = new JSONObject(input);
            JSONObject session = (JSONObject) parser.get("session");
            return (String) session.get("value");

        } catch (Exception e) {
            return null;
        }
    }
}
