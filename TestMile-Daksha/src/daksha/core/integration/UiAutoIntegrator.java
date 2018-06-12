package daksha.core.integration;

import daksha.core.batteries.integration.ComponentConfigurator;
import daksha.core.leaping.lib.config.UiAutomatorConfigurator;

public class UiAutoIntegrator {

	public static ComponentConfigurator getComponentConfigurator() {
		return new UiAutomatorConfigurator();
	}
}
