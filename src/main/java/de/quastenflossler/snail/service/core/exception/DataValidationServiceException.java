package de.quastenflossler.snail.service.core.exception;

public class DataValidationServiceException extends Exception {

    DataValidationServiceException(final String message, final Exception e) {
        super(message, e);
    }
}
