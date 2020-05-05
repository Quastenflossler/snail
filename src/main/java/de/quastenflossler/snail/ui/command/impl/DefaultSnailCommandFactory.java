package de.quastenflossler.snail.ui.command.impl;

import de.quastenflossler.snail.config.SpringConfig;
import de.quastenflossler.snail.ui.command.BasicCommand;
import de.quastenflossler.snail.ui.command.SnailCommandFactory;

import javax.inject.Named;

@Named(value = DefaultSnailCommandFactory.RESOURCE_NAME)
public class DefaultSnailCommandFactory implements SnailCommandFactory {

    public static final String RESOURCE_NAME = "DefaultSnailCommandFactory";

    @Override
    public <C extends BasicCommand> C create(final Class<C> clazz) {

        return SpringConfig.getBean(clazz);
    }
}
