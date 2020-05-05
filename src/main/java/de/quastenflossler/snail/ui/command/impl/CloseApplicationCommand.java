package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.ui.command.BasicCommand;
import javafx.application.Platform;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class CloseApplicationCommand implements BasicCommand {

    @Override
    public void execute() {

        Platform.exit();
    }

}
