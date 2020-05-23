package de.quastenflossler.snail.ui.command;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public @interface SnailCmd {

}
