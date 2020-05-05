package de.quastenflossler.snail.ui.control;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDialog extends Alert {

    public ExceptionDialog(final Throwable ex) {
        super(AlertType.ERROR);

        setTitle("Unbekannter Fehler");
        setHeaderText("Ein unerwarteter Fehler ist aufgetreten! Programm wird beendet.");
        setContentText("Fehler: " + ex.getMessage());

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(extractStacktrace(ex));
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        getDialogPane().setExpandableContent(expContent);
    }

    private String extractStacktrace(final Throwable ex) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);

        return stringWriter.toString();
    }
}
