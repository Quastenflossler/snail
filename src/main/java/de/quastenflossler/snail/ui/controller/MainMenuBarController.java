package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.ui.command.SnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.CloseApplicationCommand;
import de.quastenflossler.snail.ui.command.impl.DefaultSnailCommandFactory;
import de.quastenflossler.snail.ui.model.JiraLoginModel;
import de.quastenflossler.snail.ui.stage.SnailScene;
import de.quastenflossler.snail.ui.stage.SnailStage;
import de.quastenflossler.snail.ui.stage.SnailStageDirector;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

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

        SnailStageDirector.getInstance().showScene(SnailScene.PRINT_ISSUE);
    }

    @FXML
    public void handleInfoAction() {

    }

    @FXML
    public void handleSettingsAction() {

        SnailStageDirector.getInstance().showStage(SnailStage.SETTINGS, SnailScene.SETTINGS);
    }

    public void handlePrintJiraIssueAction() {

        SnailStageDirector.getInstance().showScene(SnailScene.PRINT_JIRA_ISSUE);
    }

    public void handleSprintCalendarAction() {

        SnailStageDirector.getInstance().showScene(SnailScene.SPRINT_CALENDAR);
    }
}
