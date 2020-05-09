package de.quastenflossler.snail.service.agile;

import de.quastenflossler.snail.service.agile.transfer.AgileTeamTO;

import java.util.List;

public interface AgileTeamManager {

    List<AgileTeamTO> findTeams();

    Long addTeam(AgileTeamTO team);

    void deleteTeam(Long teamId);

    void updateTeam(Long teamId, AgileTeamTO team);


}
