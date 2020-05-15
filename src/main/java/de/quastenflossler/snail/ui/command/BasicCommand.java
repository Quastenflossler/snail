package de.quastenflossler.snail.ui.command;

import de.quastenflossler.snail.service.core.exception.DataValidationServiceException;
import de.quastenflossler.snail.service.core.exception.InternalServiceException;

public interface BasicCommand {

    void execute() throws InternalServiceException, DataValidationServiceException;

}