package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;
import de.quastenflossler.snail.ui.command.SnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.DefaultSnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.HandleExceptionCommand;
import de.quastenflossler.snail.ui.command.impl.SaveUserPreferencesCommand;
import de.quastenflossler.snail.ui.control.LanguageChoiceBox;
import de.quastenflossler.snail.ui.model.UserPreferencesModel;
import de.quastenflossler.snail.ui.stage.SnailStageDirector;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import javax.annotation.Resource;
import javax.inject.Named;
import java.io.File;

@Named(UserPrefSceneController.RESOURCE_NAME)
public class UserPrefSceneController {

    public static final String RESOURCE_NAME = "UserPrefGridPaneController";

    @FXML
    public LanguageChoiceBox languageChoiceBox;

    @FXML
    public TextField exportPathTextField;

    @Resource(name = DefaultSnailCommandFactory.RESOURCE_NAME)
    private SnailCommandFactory commandFactory;

    @Resource(name = UserPreferencesModel.RESOURCE_NAME)
    private UserPreferencesModel userPreferencesModel;

    public void initialize() {

        languageChoiceBox.valueProperty().bindBidirectional(userPreferencesModel.localeProperty());
        languageChoiceBox.setItems(userPreferencesModel.getSupportedLocales());
        languageChoiceBox.getSelectionModel().select(SnailStageDirector.getInstance().getActiveLocale());

        exportPathTextField.textProperty().bindBidirectional(userPreferencesModel.exportPathProperty());
    }

    @FXML
    public void handleSaveUserPrefAction() {

        try {

            UserPreferencesTO preferencesTO = new UserPreferencesTO();
            preferencesTO.setLanguage(userPreferencesModel.getLocale());
            preferencesTO.setExportPath(userPreferencesModel.getExportPath());

            SaveUserPreferencesCommand saveUserPreferencesCommand = commandFactory.create(SaveUserPreferencesCommand.class);
            saveUserPreferencesCommand.setUserPreferences(preferencesTO);
            saveUserPreferencesCommand.execute();

        } catch (InternalServiceException | DataValidationServiceException e) {
            commandFactory.create(HandleExceptionCommand.class).execute(e, "Error during pdf creation.");
        }
    }

    @FXML
    public void cancelSaveUserPrefAction() {

        SnailStageDirector.getInstance().closeActiveStage();
    }

    public void handleChooseExportPathAction() {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(SnailStageDirector.getInstance().getPrimaryStage());
        userPreferencesModel.setExportPath(selectedDirectory.toString());
    }
}
