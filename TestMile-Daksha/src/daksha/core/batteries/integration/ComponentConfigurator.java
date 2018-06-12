package daksha.core.batteries.integration;

import java.util.Map;

import daksha.core.lib.ComponentIntegrator;
import daksha.tpi.batteries.interfaces.Value;

public interface ComponentConfigurator {
	void processDefaults() throws Exception;

	void processConfigProperties(Map<String, Value> map) throws Exception;

	void loadComponent() throws Exception;

	String getBaseDir();

	void setBaseDir(String dir);

	ComponentIntegrator getIntegrator();

	void setIntegrator(ComponentIntegrator integrator);

	String getComponentName();
}
