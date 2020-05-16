package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.DefaultUserPreferencesService;
import de.quastenflossler.snail.service.userpref.UserPreferenceService;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;
import de.quastenflossler.snail.ui.command.BasicCommand;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Scope(value = "prototype")
public class SaveUserPreferencesCommand implements BasicCommand {

    @Resource(name = DefaultUserPreferencesService.RESOURCE_NAME)
    private UserPreferenceService userPreferenceService;

    private UserPreferencesTO userPreferences;

    public UserPreferencesTO getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(final UserPreferencesTO userPreferences) {
        this.userPreferences = userPreferences;
    }

    @Override
    public void execute() throws InternalServiceException, DataValidationServiceException {

        userPreferenceService.saveUserPreferences(userPreferences);
    }
}
