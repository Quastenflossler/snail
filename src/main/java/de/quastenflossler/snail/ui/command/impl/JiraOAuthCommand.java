package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.service.issue.domain.impl.Command;
import de.quastenflossler.snail.service.issue.domain.impl.JiraOAuthClient;
import de.quastenflossler.snail.service.issue.domain.impl.OAuthClient;
import de.quastenflossler.snail.service.issue.domain.impl.PropertiesClient;
import de.quastenflossler.snail.ui.command.BasicCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Scope(value = "prototype")
public class JiraOAuthCommand implements BasicCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(JiraOAuthCommand.class);

    @Override
    public void execute() {

        try {

            LOGGER.debug("[START] Get Jira OAuth Access Token");

            PropertiesClient propertiesClient = new PropertiesClient();
            JiraOAuthClient jiraOAuthClient = new JiraOAuthClient(propertiesClient);

            new OAuthClient(propertiesClient, jiraOAuthClient).execute(Command.REQUEST_TOKEN,Collections.emptyList());

            LOGGER.debug("[END] Get Jira OAuth Access Token");

        } catch (Exception e) {

            LOGGER.error("Fuck", e);
        }
    }
}
