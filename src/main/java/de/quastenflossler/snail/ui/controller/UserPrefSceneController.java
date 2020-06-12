package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;
import de.quastenflossler.snail.service.userpref.transfer.UserPreferencesTO;
import de.quastenflossler.snail.ui.command.SnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.DefaultSnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.HandleExceptionCommand;
import de.quastenflossler.snail.ui.command.impl.SaveUserPreferencesCommand;
import de.quastenflossler.snail.ui.control.LanguageChoiceBox;
import de.quastenflossler.snail.ui.control.LeftSidedTextCheckBox;
import de.quastenflossler.snail.ui.model.JiraLoginModel;
import de.quastenflossler.snail.ui.model.UserPreferencesModel;
import de.quastenflossler.snail.ui.stage.SnailStageDirector;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
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

    @FXML
    public TextField jiraUrlTextField;

    @FXML
    public Label jiraOnlineStatus;

    @FXML
    public DatePicker beginOfFirstSprintDatePicker;

    @FXML
    public Spinner<Integer> sprintDurationSpinner;

    @FXML
    public LeftSidedTextCheckBox sprintChangeDayCheckBox;

    @Resource(name = DefaultSnailCommandFactory.RESOURCE_NAME)
    private SnailCommandFactory commandFactory;

    @Resource(name = UserPreferencesModel.RESOURCE_NAME)
    private UserPreferencesModel userPreferencesModel;

    @Resource(name = JiraLoginModel.RESOURCE_NAME)
    private JiraLoginModel jiraLoginModel;

    public void initialize() {

        languageChoiceBox.valueProperty().bindBidirectional(userPreferencesModel.localeProperty());
        languageChoiceBox.setItems(userPreferencesModel.getSupportedLocales());
        languageChoiceBox.getSelectionModel().select(SnailStageDirector.getInstance().getActiveLocale());

        exportPathTextField.textProperty().bindBidirectional(userPreferencesModel.exportPathProperty());
        jiraUrlTextField.textProperty().bindBidirectional(userPreferencesModel.jiraUrlProperty());

        jiraOnlineStatus.textProperty().bind(Bindings.when(jiraLoginModel.sessionIdProperty().isNotEmpty())
                                                     .then(jiraLoginModel.sessionIdProperty())
                                                     .otherwise("offline"));

       /* jiraOnlineStatus.textFillProperty().bind(Bindings.when(jiraLoginModel.sessionIdProperty().isEqualTo("offline"))
                                                         .then(Color.RED)
                                                         .otherwise(Color.GREEN));*/

       beginOfFirstSprintDatePicker.valueProperty().bindBidirectional(userPreferencesModel.beginOfFirstSprintProperty());
       sprintDurationSpinner.getValueFactory().valueProperty().bindBidirectional(userPreferencesModel.sprintDurationProperty().asObject());
       sprintChangeDayCheckBox.selectedProperty().bindBidirectional(userPreferencesModel.sprintChangeDayFlagProperty());
    }

    @FXML
    public void handleSaveUserPrefAction() {

        try {

            UserPreferencesTO preferencesTO = new UserPreferencesTO();
            preferencesTO.setLanguage(userPreferencesModel.getLocale());
            preferencesTO.setExportPath(userPreferencesModel.getExportPath());
            preferencesTO.setJiraUrl(userPreferencesModel.getJiraUrl());
            preferencesTO.setBeginOfFirstSprint(userPreferencesModel.getBeginOfFirstSprint());
            preferencesTO.setSprintDuration(userPreferencesModel.getSprintDuration());
            preferencesTO.setSprintChangeDayFlag(userPreferencesModel.isSprintChangeDayFlag());

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

    @FXML
    public void okUserPrefAction() {

        try {

            UserPreferencesTO preferencesTO = new UserPreferencesTO();
            preferencesTO.setLanguage(userPreferencesModel.getLocale());
            preferencesTO.setExportPath(userPreferencesModel.getExportPath());
            preferencesTO.setJiraUrl(userPreferencesModel.getJiraUrl());
            preferencesTO.setBeginOfFirstSprint(userPreferencesModel.getBeginOfFirstSprint());
            preferencesTO.setSprintDuration(userPreferencesModel.getSprintDuration());
            preferencesTO.setSprintChangeDayFlag(userPreferencesModel.isSprintChangeDayFlag());

            SaveUserPreferencesCommand saveUserPreferencesCommand = commandFactory.create(SaveUserPreferencesCommand.class);
            saveUserPreferencesCommand.setUserPreferences(preferencesTO);
            saveUserPreferencesCommand.execute();

            SnailStageDirector.getInstance().closeActiveStage();

        } catch (InternalServiceException | DataValidationServiceException e) {
            commandFactory.create(HandleExceptionCommand.class).execute(e, "Error during pdf creation.");
        }
    }

    public void handleConnectToJiraAction() {

    }
}
