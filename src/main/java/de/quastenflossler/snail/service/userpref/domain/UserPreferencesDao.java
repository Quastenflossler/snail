package de.quastenflossler.snail.service.userpref.domain;

import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;

public interface UserPreferencesDao {

    UserPreferencesTO findUserPreferences() throws InternalServiceException;

    void saveUserPreferences(UserPreferencesTO preferences) throws InternalServiceException;
}
