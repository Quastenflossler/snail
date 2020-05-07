package de.quastenflossler.snail.service.issue.transfer;

import de.quastenflossler.snail.service.core.transfer.AbstractTransferObject;

public class BasicEpicTO extends AbstractTransferObject {

    private static final long serialVersionUID = 6636393324511513709L;

    private String key;
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
