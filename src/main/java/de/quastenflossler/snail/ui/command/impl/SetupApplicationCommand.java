package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.DefaultUserPreferencesService;
import de.quastenflossler.snail.service.userpref.UserPreferenceService;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;
import de.quastenflossler.snail.ui.command.BasicCommand;
import de.quastenflossler.snail.ui.model.UserPreferencesModel;
import de.quastenflossler.snail.ui.stage.SnailStageDirector;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;

@Component
@Scope(value = "prototype")
public class SetupApplicationCommand implements BasicCommand {

    @Resource(name = DefaultUserPreferencesService.RESOURCE_NAME)
    private UserPreferenceService userPreferenceService;

    @Resource(name = UserPreferencesModel.RESOURCE_NAME)
    private UserPreferencesModel userPreferencesModel;

    @Override
    public void execute() throws InternalServiceException {

        try {

            UserPreferencesTO userPreferencesTO = userPreferenceService.findUserPreferences();
            userPreferencesModel.setLocale(userPreferencesTO.getLanguage());
            userPreferencesModel.setExportPath(userPreferencesTO.getExportPath());
            userPreferencesModel.setJiraUrl(userPreferencesTO.getJiraUrl());

            if (userPreferencesTO.getBeginOfFirstSprint() != null) {

                userPreferencesModel.setBeginOfFirstSprint(userPreferencesTO.getBeginOfFirstSprint());
            }

            if (userPreferencesTO.getSprintDuration() != null) {

                userPreferencesModel.setSprintDuration(userPreferencesTO.getSprintDuration());
            }

            if (userPreferencesTO.getSprintChangeDayFlag() != null) {

                userPreferencesModel.setSprintChangeDayFlag(userPreferencesTO.getSprintChangeDayFlag());
            }


            SnailStageDirector.getInstance().setActiveLocale(userPreferencesTO.getLanguage());

            SnailStageDirector.getInstance().init();

        } catch (IOException | URISyntaxException e) {
            throw new InternalServiceException("Application setup failed!", e);
        }
    }
}
