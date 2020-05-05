package de.quastenflossler.snail.service.transfer;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public abstract class AbstractTransferObject implements Serializable {

    private static final long serialVersionUID = -7093619786553858744L;

    @Override
    public String toString() {

        return ReflectionToStringBuilder.toString(this);
    }
}
