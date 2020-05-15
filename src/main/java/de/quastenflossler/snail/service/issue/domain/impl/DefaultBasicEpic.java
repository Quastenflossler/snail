package de.quastenflossler.snail.service.issue.domain.impl;

import de.quastenflossler.snail.service.issue.domain.BasicEpic;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
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
