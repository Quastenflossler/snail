package de.quastenflossler.snail.service.userpref;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;

public interface UserPreferenceService {

    UserPreferencesTO findUserPreferences() throws InternalServiceException;

    void saveUserPreferences(UserPreferencesTO preferences) throws InternalServiceException, DataValidationServiceException;
}
