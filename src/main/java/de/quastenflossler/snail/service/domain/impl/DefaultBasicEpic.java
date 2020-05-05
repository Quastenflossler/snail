package de.quastenflossler.snail.service.domain.impl;

import de.quastenflossler.snail.service.domain.BasicEpic;

public class DefaultBasicEpic implements BasicEpic {

    private String key;
    private String description;

    @Override
    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
