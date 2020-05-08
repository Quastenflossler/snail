package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.DefaultUserPreferencesService;
import de.quastenflossler.snail.service.userpref.UserPreferenceService;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;
import de.quastenflossler.snail.ui.command.SnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.DefaultSnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.HandleExceptionCommand;
import de.quastenflossler.snail.ui.control.LanguageChoiceBox;
import de.quastenflossler.snail.ui.stage.SnailStageDirector;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import javax.annotation.Resource;
import javax.inject.Named;
import java.util.Locale;

@Named(UserPrefSceneController.RESOURCE_NAME)
public class UserPrefSceneController {

    public static final String RESOURCE_NAME = "UserPrefGridPaneController";

    @FXML
    public LanguageChoiceBox languageChoiceBox;

    @Resource(name = DefaultSnailCommandFactory.RESOURCE_NAME)
    private SnailCommandFactory commandFactory;

    @Resource(name = DefaultUserPreferencesService.RESOURCE_NAME)
    private UserPreferenceService userPreferenceService;

    public void initialize() {

        languageChoiceBox.setItems(FXCollections.observableArrayList(Locale.ENGLISH, Locale.GERMANY));
        languageChoiceBox.getSelectionModel().select(SnailStageDirector.getInstance().getActiveLocale());
    }

    @FXML
    public void handleSaveUserPrefAction() {

        try {

            UserPreferencesTO preferencesTO = new UserPreferencesTO();
            preferencesTO.setLanguage(languageChoiceBox.getValue());

            userPreferenceService.saveUserPreferences(preferencesTO);

        } catch (InternalServiceException | DataValidationServiceException e) {
            commandFactory.create(HandleExceptionCommand.class).execute(e, "Error during pdf creation.");
        }
    }

    @FXML
    public void cancelSaveUserPrefAction() {

        SnailStageDirector.getInstance().closeActiveStage();
    }
}
