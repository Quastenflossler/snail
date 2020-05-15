package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.service.issue.transfer.BasicEpicTO;
import de.quastenflossler.snail.service.issue.transfer.BasicIssueTO;
import de.quastenflossler.snail.ui.command.SnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.DefaultSnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.HandleExceptionCommand;
import de.quastenflossler.snail.ui.command.impl.PrintIssueCommand;
import de.quastenflossler.snail.ui.model.PrintIssueModel;
import de.quastenflossler.snail.ui.stage.SnailScene;
import de.quastenflossler.snail.ui.stage.SnailStageDirector;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Named;

@Named(value = PrintIssueBorderPaneController.RESOURCE_NAME)
public class PrintIssueBorderPaneController {

    public static final String RESOURCE_NAME = "Controller";
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintIssueBorderPaneController.class);

    @FXML
    public TextField issueKeyTextField;

    @FXML
    public TextField issueTitleTextField;

    @FXML
    public TextField issueEpicKeyTextField;

    @FXML
    public TextField issueEpicTextField;

    @FXML
    public TextArea issueDescriptionTextArea;

    @FXML
    public TextField issueStakeholderTextField;

    @FXML
    public ChoiceBox<String> issueStoryPointsChoiceBox;

    @FXML
    public TextArea issueAcceptanceCriteriaTextArea;

    @FXML
    public TextField issuePlannedSprintTextField;

    @FXML
    public TextField issueDeadlineTextField;

    @Resource(name = DefaultSnailCommandFactory.RESOURCE_NAME)
    private SnailCommandFactory commandFactory;

    @Resource(name = PrintIssueModel.RESOURCE_NAME)
    private PrintIssueModel printIssueModel;

    public void initialize() {

        issueKeyTextField.textProperty().bindBidirectional(printIssueModel.issueKeyProperty());
        issueTitleTextField.textProperty().bindBidirectional(printIssueModel.issueTitleProperty());
        issueEpicKeyTextField.textProperty().bindBidirectional(printIssueModel.epicKeyProperty());
        issueEpicTextField.textProperty().bindBidirectional(printIssueModel.epicNameProperty());
        issueDescriptionTextArea.textProperty().bindBidirectional(printIssueModel.issueDescriptionProperty());
        issueStakeholderTextField.textProperty().bindBidirectional(printIssueModel.stakeholderProperty());
        issueStoryPointsChoiceBox.valueProperty().bindBidirectional(printIssueModel.storyPointsProperty());
        issueAcceptanceCriteriaTextArea.textProperty().bindBidirectional(printIssueModel.acceptanceCriteriaProperty());
        issuePlannedSprintTextField.textProperty().bindBidirectional(printIssueModel.plannedSprintProperty());
        issueDeadlineTextField.textProperty().bindBidirectional(printIssueModel.deadlineProperty());
    }

    public void handlePrintIssueAction() {

        try {

            BasicEpicTO epicTO = new BasicEpicTO();
            epicTO.setKey(printIssueModel.getEpicKey());
            epicTO.setDescription(printIssueModel.getEpicName());

            BasicIssueTO issueTO = new BasicIssueTO();

            issueTO.setKey(printIssueModel.getIssueKey());
            issueTO.setSummary(printIssueModel.getIssueTitle());
            issueTO.setDescription(printIssueModel.getIssueDescription());
            issueTO.setEpic(epicTO);
            issueTO.setStoryPoints(printIssueModel.getConvertedStoryPoints());
            issueTO.setAcceptanceCriteria(printIssueModel.getAcceptanceCriteria());
            issueTO.setStakeholder(printIssueModel.getStakeholder());
            issueTO.setPlannedSprint(printIssueModel.getPlannedSprint());
            issueTO.setDeadline(printIssueModel.getDeadline());

            PrintIssueCommand printIssueCommand = commandFactory.create(PrintIssueCommand.class);
            printIssueCommand.setIssueTO(issueTO);
            printIssueCommand.execute();

        } catch (Exception e) {

            commandFactory.create(HandleExceptionCommand.class).execute(e, "Error during pdf creation.");
        }
    }

    public void handleShowPreviewAction() {

        SnailStageDirector.getInstance().showScene(SnailScene.PRINT_ISSUE_WITH_PREVIEW);
    }

    public void handleFillWithExampleAction() {

        printIssueModel.setEpicKey("EPC-123");
        printIssueModel.setEpicName("My Project");
        printIssueModel.setIssueKey("EPC-124");
        printIssueModel.setIssueTitle("Some Customer needs an Awesome Feature");
        printIssueModel.setIssueDescription("As user\nI need an awesome feature \nso I can do some awesome things.");
        printIssueModel.setStoryPoints("5");
        printIssueModel.setAcceptanceCriteria("This user story is solved when \n - feature is tested \n - feature is released to production");
        printIssueModel.setStakeholder("THE MANAGER");
        printIssueModel.setPlannedSprint("CW 17");
        printIssueModel.setDeadline("20.04.2020");
    }

    public void handleDeleteAllFieldsAction() {

        printIssueModel.setEpicKey("");
        printIssueModel.setEpicName("");
        printIssueModel.setIssueKey("");
        printIssueModel.setIssueTitle("");
        printIssueModel.setIssueDescription("");
        printIssueModel.setStoryPoints("?");
        printIssueModel.setAcceptanceCriteria("");
        printIssueModel.setStakeholder("");
        printIssueModel.setPlannedSprint("");
        printIssueModel.setDeadline("");
    }
}
