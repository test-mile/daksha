package daksha.tpi;

import java.util.Map;

import daksha.core.batteries.config.ConfigProperty;
import daksha.core.batteries.config.Configuration;
import daksha.core.guiauto.enums.OSType;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

public interface TestContext {
	
	void setOption(DakshaOption option, String value) throws Exception;
	Map<String, ConfigProperty> asMap() throws Exception;
	Map<String, String> asRawMap() throws Exception;
	
	Value getValue(DakshaOption option) throws Exception;
	Value getValue(String string) throws Exception;

	String getName();

	Configuration getConfig();

	GuiAutomationContext getGuiAutoContext() throws Exception;

	void setAutomationContext(GuiAutomationContext context) throws Exception;

	String getAppDir() throws Exception;

	Browser getBrowserType() throws Exception;
	void setBrowserType(Browser browser) throws Exception;

	String getBrowserBinaryPath() throws Exception;

	String getSeleniumDriverPath(Browser browser) throws Exception;

	String getBrowerVersion() throws Exception;

	String getTestRunEnvName() throws Exception;

	String getScreenshotsDir() throws Exception;

	String getLogDir() throws Exception;

	void setTargetPlatform(OSType mac) throws Exception;

	int getGuiAutoMaxWaitTime() throws Exception;
	
	void addAll(Map<String, Value> properties) throws Exception;
	
	void setOption(String option, String value) throws Exception;
	
	public void setGuiAutoMaxWaitTime(int seconds) throws Exception;
	void freeze() throws Exception;

}