package de.quastenflossler.snail.service.agile.transfer;

import de.quastenflossler.snail.service.core.transfer.AbstractTransferObject;

import java.util.ArrayList;
import java.util.List;

public class AgileTeamTO extends AbstractTransferObject {

    private static final long serialVersionUID = 6073295956045421326L;

    private Long id;
    private String name;
    private List<AgileTeamMemberTO> members = new ArrayList<>();
    private SprintDuration sprintDuration;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<AgileTeamMemberTO> getMembers() {
        return members;
    }

    public void setMembers(final List<AgileTeamMemberTO> members) {
        this.members = members;
    }

    public SprintDuration getSprintDuration() {
        return sprintDuration;
    }

    public void setSprintDuration(final SprintDuration sprintDuration) {
        this.sprintDuration = sprintDuration;
    }
}
