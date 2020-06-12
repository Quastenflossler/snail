package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.ui.command.SnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.DefaultSnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.FindJiraIssueCommand;
import de.quastenflossler.snail.ui.control.LoginDialog;
import de.quastenflossler.snail.ui.model.JiraIssueModel;
import de.quastenflossler.snail.ui.model.JiraLoginModel;
import de.quastenflossler.snail.ui.model.PrintIssueModel;
import de.quastenflossler.snail.ui.model.UserPreferencesModel;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Named;

@Named(value = PrintJiraIssueBorderPaneController.RESOURCE_NAME)
public class PrintJiraIssueBorderPaneController {

    public static final String RESOURCE_NAME = "PrintJiraIssueBorderPaneController";
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintJiraIssueBorderPaneController.class);

    @FXML
    public TextField jqlTextField;

    @FXML
    public TableView<JiraIssueModel> jiraIssuesTableView;

    @FXML
    public TableColumn<JiraLoginModel, String> issueKeyCol;

    @FXML
    public TableColumn<JiraLoginModel, String> issueTitleCol;

    @Resource(name = DefaultSnailCommandFactory.RESOURCE_NAME)
    private SnailCommandFactory commandFactory;

    @Resource(name = PrintIssueModel.RESOURCE_NAME)
    private PrintIssueModel printIssueModel;

    @Resource(name = UserPreferencesModel.RESOURCE_NAME)
    private UserPreferencesModel userPreferencesModel;

    @Resource(name = JiraLoginModel.RESOURCE_NAME)
    private JiraLoginModel jiraLoginModel;

    public void initialize() {

        jiraIssuesTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        jiraIssuesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        issueKeyCol.setCellValueFactory(new PropertyValueFactory<>("issueKey"));
        issueTitleCol.setCellValueFactory(new PropertyValueFactory<>("issueTitle"));
        jiraIssuesTableView.setItems(jiraLoginModel.getIssues());
        jqlTextField.textProperty().bindBidirectional(jiraLoginModel.jqlProperty());
    }

    public void handleSearchJiraIssuesByJqlAction() {

        LoginDialog loginDialog = new LoginDialog();
        jiraLoginModel.setUsername(loginDialog.getUsername());
        jiraLoginModel.setPassword(loginDialog.getPassword());

//        LoginToJiraCommand loginToJiraCommand = commandFactory.create(LoginToJiraCommand.class);
//        loginToJiraCommand.setUsername(jiraLoginModel.getUsername());
//        loginToJiraCommand.setPassword(jiraLoginModel.getPassword());
//        loginToJiraCommand.execute();

//        FindIssuesCommand findIssuesCommand = commandFactory.create(FindIssuesCommand.class);
//        findIssuesCommand.setJql(jqlTextField.getText());
//        findIssuesCommand.execute();

        FindJiraIssueCommand findJiraIssueCommand = commandFactory.create(FindJiraIssueCommand.class);
        findJiraIssueCommand.execute();
    }
}
