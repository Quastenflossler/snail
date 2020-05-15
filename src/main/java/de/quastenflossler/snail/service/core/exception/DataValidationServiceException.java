package de.quastenflossler.snail.service.core.exception;

public class DataValidationServiceException extends Exception {

    public DataValidationServiceException(final String message) {
        super(message);
    }

    public DataValidationServiceException(final String message, final Exception e) {
        super(message, e);
    }
}
