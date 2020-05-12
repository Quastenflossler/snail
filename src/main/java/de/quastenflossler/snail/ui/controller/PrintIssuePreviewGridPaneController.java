package de.quastenflossler.snail.ui.controller;

import de.quastenflossler.snail.ui.model.PrintIssueModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.annotation.Resource;
import javax.inject.Named;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

@Named(value = PrintIssuePreviewGridPaneController.RESOURCE_NAME)
public class PrintIssuePreviewGridPaneController {

    public static final String RESOURCE_NAME = "PrintIssuePreviewGridPaneController";

    @FXML
    public Label previewIssueTitleLabel;

    @FXML
    public ImageView previewIssueLogo;

    @Resource(name = PrintIssueModel.RESOURCE_NAME)
    private PrintIssueModel printIssueModel;

    public void initialize() throws URISyntaxException, IOException {

        previewIssueTitleLabel.textProperty().bindBidirectional(printIssueModel.issueTitleProperty());

        URL ingoFilePath = getClass().getResource("/assets/ingo.png");
        previewIssueLogo.setImage(new Image(ingoFilePath.openStream()));
    }
}
