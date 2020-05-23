package de.quastenflossler.snail.ui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LeftSidedTextCheckBox extends HBox {

    @FXML
    private String text;

    private final Label label;
    private final CheckBox checkBox;

    public LeftSidedTextCheckBox(){

        checkBox = new CheckBox();
        label = new Label(text);

        getChildren().addAll(label, checkBox);
        setSpacing(5);
    }

    public String getText() {
        return label.getText();
    }

    public void setText(final String text) {
        label.setText(text);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void addListener(final ChangeListener<? super Boolean> listener) {

        checkBox.selectedProperty().addListener(listener);
    }

    public final BooleanProperty selectedProperty() {
       return checkBox.selectedProperty();
    }
}
