package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.ui.command.BasicCommand;
import de.quastenflossler.snail.ui.control.ExceptionDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class HandleExceptionCommand implements BasicCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandleExceptionCommand.class);

    private Throwable throwable;
    private String message;

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(final Throwable throwable) {
        this.throwable = throwable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public void execute() {

        if (message != null) {

            LOGGER.error(message, throwable);
        }

        if (throwable instanceof DataValidationServiceException) {

            showWarningDialog(throwable.getMessage());

        } else {

            showExceptionDialog();
        }
    }

    public void execute(final Throwable throwable, final String message) {

        this.throwable = throwable;
        this.message = message;
        execute();
    }

    private void showExceptionDialog() {

        ExceptionDialog exceptionDialog = new ExceptionDialog(throwable);
        exceptionDialog.showAndWait();

        if (exceptionDialog.getResult() == ButtonType.OK) {

            new CloseApplicationCommand().execute();
        }
    }

    private void showWarningDialog(final String message) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Snail - Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
