package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.ui.command.BasicCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class JiraOAuthCommand implements BasicCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(JiraOAuthCommand.class);

    @Override
    public void execute() {

        try {

            LOGGER.debug("[START] Get Jira OAuth Access Token");


            LOGGER.debug("[END] Get Jira OAuth Access Token");

        } catch (Exception e) {

            LOGGER.error("Fuck", e);
        }
    }
}
