package de.quastenflossler.snail.service.agile;

import de.quastenflossler.snail.service.agile.transfer.AgileTeamTO;

import java.util.Collection;

public interface AgileTeamService {

    Collection<AgileTeamTO> findTeams();

    AgileTeamTO getTeam(Long teamId);

    Long addTeam(AgileTeamTO team);

    void removeTeam(Long teamId);

    void updateTeam(Long teamId, AgileTeamTO team);

}
