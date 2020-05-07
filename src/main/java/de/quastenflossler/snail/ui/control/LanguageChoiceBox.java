package de.quastenflossler.snail.ui.control;

import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;

import java.util.Locale;

public class LanguageChoiceBox extends ChoiceBox<Locale> {

    public LanguageChoiceBox(){

        setConverter(new StringConverter<>() {

            @Override
            public String toString(final Locale locale) {
                return locale.getDisplayLanguage(locale);
            }

            @Override
            public Locale fromString(final String language) {
                return Locale.forLanguageTag(language);
            }
        });

    }

}
