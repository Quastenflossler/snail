package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.ui.model.PrintIssueModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.annotation.Resource;
import javax.inject.Named;
import java.io.IOException;
import java.net.URL;

@Named(value = PrintIssuePreviewGridPaneController.RESOURCE_NAME)
public class PrintIssuePreviewGridPaneController {

    public static final String RESOURCE_NAME = "PrintIssuePreviewGridPaneController";

    @FXML
    public Label previewIssueTitleLabel;

    @FXML
    public ImageView previewIssueLogo;

    @FXML
    public Label previewIssueKeyLabel;

    @FXML
    public Label previewEpicKeyLabel;

    @FXML
    public Label previewEpicNameLabel;

    @FXML
    public Label previewStakeholderLabel;

    @FXML
    public Label previewPlannedSprintLabel;

    @FXML
    public Label previewDeadlineLabel;

    @FXML
    public Label previewDescriptionLabel;

    @FXML
    public Label previewStoryPointsLabel;

    @FXML
    public Label previewAcceptanceCriteriaLabel;

    @Resource(name = PrintIssueModel.RESOURCE_NAME)
    private PrintIssueModel printIssueModel;

    public void initialize() throws IOException {

        previewIssueTitleLabel.textProperty().bindBidirectional(printIssueModel.issueTitleProperty());

        URL ingoFilePath = getClass().getResource("/assets/ingo.png");
        previewIssueLogo.setImage(new Image(ingoFilePath.openStream()));

        previewIssueKeyLabel.textProperty().bindBidirectional(printIssueModel.issueKeyProperty());
        previewEpicKeyLabel.textProperty().bindBidirectional(printIssueModel.epicKeyProperty());
        previewEpicNameLabel.textProperty().bindBidirectional(printIssueModel.epicNameProperty());
        previewStakeholderLabel.textProperty().bindBidirectional(printIssueModel.stakeholderProperty());
        previewPlannedSprintLabel.textProperty().bindBidirectional(printIssueModel.plannedSprintProperty());
        previewDeadlineLabel.textProperty().bindBidirectional(printIssueModel.deadlineProperty());
        previewDescriptionLabel.textProperty().bindBidirectional(printIssueModel.issueDescriptionProperty());
        previewStoryPointsLabel.textProperty().bindBidirectional(printIssueModel.storyPointsProperty());
        previewAcceptanceCriteriaLabel.textProperty().bindBidirectional(printIssueModel.acceptanceCriteriaProperty());
    }
}
