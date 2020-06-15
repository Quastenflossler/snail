package de.quastenflossler.snail.service.agile;

import de.quastenflossler.snail.service.agile.transfer.SprintStatisticsTO;
import de.quastenflossler.snail.service.agile.transfer.SprintTO;

import java.util.Collection;

public interface SprintService {

    Collection<SprintTO> findSprints();

    SprintTO getSprint(Long sprintId);

    Long addSprint(SprintTO sprint);

    void removeSprint(Long sprintId);

    void updateSprint(Long sprintId, SprintTO sprint);

    void markSprintAsActive(Long sprintId);

    void unmarkSprintAsActive(Long sprintId);

    void updateSprintStatistics(Long sprintId, SprintStatisticsTO statistics);
}
