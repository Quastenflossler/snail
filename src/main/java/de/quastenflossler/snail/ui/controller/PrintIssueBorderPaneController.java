package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.service.domain.DomainObjectMapper;
import de.quastenflossler.snail.service.domain.SmartIssue;
import de.quastenflossler.snail.service.domain.impl.DefaultDomainObjectMapper;
import de.quastenflossler.snail.service.transfer.BasicEpicTO;
import de.quastenflossler.snail.service.transfer.BasicIssueTO;
import de.quastenflossler.snail.ui.command.SnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.DefaultSnailCommandFactory;
import de.quastenflossler.snail.ui.command.impl.HandleExceptionCommand;
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
    public ChoiceBox<Integer> issueStoryPointsChoiceBox;

    @FXML
    public TextArea issueAcceptanceCriteriaTextArea;

    @FXML
    public TextField issuePlannedSprintTextField;

    @FXML
    public TextField issueDeadlineTextField;

    @Resource(name = DefaultSnailCommandFactory.RESOURCE_NAME)
    private SnailCommandFactory commandFactory;

    @Resource(name = DefaultDomainObjectMapper.RESOURCE_NAME)
    private DomainObjectMapper domainObjectMapper;

    public void handlePrintIssueAction() {

        try {

            BasicEpicTO epicTO = new BasicEpicTO();
            epicTO.setKey(issueEpicKeyTextField.getText());
            epicTO.setDescription(issueEpicTextField.getText());

            BasicIssueTO issueTO = new BasicIssueTO();

            issueTO.setKey(issueKeyTextField.getText());
            issueTO.setSummary(issueTitleTextField.getText());
            issueTO.setDescription(issueDescriptionTextArea.getText());
            issueTO.setEpic(epicTO);
            issueTO.setStoryPoints(issueStoryPointsChoiceBox.getValue());
            issueTO.setAcceptanceCriteria(issueAcceptanceCriteriaTextArea.getText());
            issueTO.setStakeholder(issueStakeholderTextField.getText());
            issueTO.setPlannedSprint(issuePlannedSprintTextField.getText());
            issueTO.setDeadline(issueDeadlineTextField.getText());

            SmartIssue issue = domainObjectMapper.createSmartIssue(issueTO);
            issue.printAsPdf();

        } catch (Exception e) {

            commandFactory.create(HandleExceptionCommand.class).execute(e, "Error during pdf creation.");
        }
    }
}
