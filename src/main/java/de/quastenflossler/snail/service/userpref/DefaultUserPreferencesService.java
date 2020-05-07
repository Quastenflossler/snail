package de.quastenflossler.snail.service.userpref;

import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.domain.PropertyFileUserPreferencesDoDao;
import de.quastenflossler.snail.service.userpref.domain.UserPreferencesDao;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Named;

@Named(value = DefaultUserPreferencesService.RESOURCE_NAME)
public class DefaultUserPreferencesService implements UserPreferenceService {

    public static final String RESOURCE_NAME = "DefaultUserPreferencesService";

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserPreferencesService.class);

    @Resource(name = PropertyFileUserPreferencesDoDao.RESOURCE_NAME)
    private UserPreferencesDao dao;

    @Override
    public UserPreferencesTO findUserPreferences() throws InternalServiceException {
        return dao.findUserPreferences();
    }

    @Override
    public void saveUserPreferences(UserPreferencesTO preferences) throws InternalServiceException {
        dao.saveUserPreferences(preferences);
        LOGGER.info("User preferences saved: {}", preferences);
    }
}
