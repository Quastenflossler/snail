package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.ui.command.SnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.CloseApplicationCommand;
import de.quastenflossler.snail.ui.command.impl.DefaultSnailCommandFactory;
import de.quastenflossler.snail.ui.model.JiraLoginModel;
import de.quastenflossler.snail.ui.stage.HomeScreenController;
import de.quastenflossler.snail.ui.stage.HomeScreenPanes;
import de.quastenflossler.snail.ui.stage.UiHelper;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.annotation.Resource;
import javax.inject.Named;

@Named(MainMenuBarController.RESOURCE_NAME)
public class MainMenuBarController {

    public static final String RESOURCE_NAME = "MenuBarController";

    @FXML
    public MenuItem menuItemExit;

    @Resource(name = DefaultSnailCommandFactory.RESOURCE_NAME)
    private SnailCommandFactory commandFactory;

    @Resource(name = JiraLoginModel.RESOURCE_NAME)
    private JiraLoginModel jiraLoginModel;

    @FXML
    private void handleExitAction() {

        commandFactory.create(CloseApplicationCommand.class).execute();
    }

    @FXML
    public void handlePrintIssueAction() {

        HomeScreenController.getInstance().activate(HomeScreenPanes.PRINT_ISSUE.getName());
    }

    @FXML
    public void handleInfoAction() {

    }

    @FXML
    public void handleSettingsAction() {

        Pane settingsPane = HomeScreenController.getInstance().getPaneMap().get(HomeScreenPanes.USER_SETTINGS.getName());
        Scene scene = new Scene(settingsPane);

        Stage settingsStage = new Stage(StageStyle.DECORATED);
        settingsStage.setTitle("Snail - Settings");
        settingsStage.setResizable(false);
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.setScene(scene);
        settingsStage.toFront();
        settingsStage.show();
        UiHelper.centerOnScreen(settingsStage);
    }

    public void handlePrintJiraIssueAction() {

        HomeScreenController.getInstance().activate(HomeScreenPanes.PRINT_JIRA_ISSUE.getName());
    }

    public void handleSprintCalendarAction() {

        HomeScreenController.getInstance().activate("sprint calendar");
    }
}
