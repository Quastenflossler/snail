package de.quastenflossler.snail.service.agile;

import de.quastenflossler.snail.service.agile.transfer.GlobalAgileConfigurationTO;

public interface AgileEnvironmentService {

    GlobalAgileConfigurationTO getGlobalConfiguration();

    void updateGlobalConfiguration(GlobalAgileConfigurationTO configuration);
}
