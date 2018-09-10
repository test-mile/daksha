package daksha.tpi;

import java.util.Map;

import daksha.core.batteries.config.Configuration;
import daksha.core.batteries.config.ContextConfiguration;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

public interface TestContext {

	void setOptions(ContextConfiguration config);

	void add(String option, String value);

	Map<String, String> getAsMap();

	String getName();

	Configuration getConfig();

	GuiAutomationContext getAutomationContext() throws Exception;

	void setAutomationContext(GuiAutomationContext context) throws Exception;

	String getAppDir() throws Exception;

}