package de.quastenflossler.snail.service.userpref.transfer;

import de.quastenflossler.snail.service.core.transfer.AbstractTransferObject;

import java.util.Locale;

public class UserPreferencesTO extends AbstractTransferObject {

    private static final long serialVersionUID = 4882795078626556699L;

    private Locale language;

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(final Locale language) {
        this.language = language;
    }

}
