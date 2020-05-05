package de.quastenflossler.snail.ui.command;

public interface SnailCommandFactory {

    <C extends BasicCommand> C create(Class<C> clazz);
}
