package de.quastenflossler.snail.service.agile.transfer;

import de.quastenflossler.snail.service.core.transfer.AbstractTransferObject;

public class AgileTeamMemberTO extends AbstractTransferObject {

    private static final long serialVersionUID = -2085136411616960225L;

    private Long id;
    private String firstName;
    private String lastName;
    private String alias;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }
}
