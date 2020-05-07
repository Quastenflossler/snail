package de.quastenflossler.snail.service.core.exception;

public class InternalServiceException extends Exception {

    public InternalServiceException(final String message, final Exception e) {
        super(message, e);
    }
}
