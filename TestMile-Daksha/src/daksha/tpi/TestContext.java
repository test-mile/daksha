package daksha.tpi;

import java.util.Map;

import daksha.core.batteries.config.Configuration;
import daksha.core.guiauto.enums.OSType;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

public interface TestContext {
	
	void add(DakshaOption option, String value) throws Exception;

	Map<DakshaOption, Value> asMap() throws Exception;

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

	Value getOptionValue(DakshaOption option) throws Exception;

	String getScreenshotsDir() throws Exception;

	String getLogDir() throws Exception;

	void setTargetPlatform(OSType mac) throws Exception;

	int getGuiAutoMaxWaitTime() throws Exception;
}