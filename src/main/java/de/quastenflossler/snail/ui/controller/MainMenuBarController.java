package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.ui.ControlManager;
import de.quastenflossler.snail.ui.SnailScene;
import de.quastenflossler.snail.ui.command.SnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.CloseApplicationCommand;
import de.quastenflossler.snail.ui.command.impl.DefaultSnailCommandFactory;
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

    @FXML
    private void handleExitAction() {

        commandFactory.create(CloseApplicationCommand.class).execute();
    }

    @FXML
    public void handlePrintIssueAction() {

        ControlManager.getInstance().showScene(SnailScene.PRINT_ISSUE);
    }

    @FXML
    public void handleInfoAction() {
    }
}
