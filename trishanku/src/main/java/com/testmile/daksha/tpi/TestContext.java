package com.testmile.daksha.tpi;

import java.util.Map;

import com.testmile.daksha.core.batteries.config.ConfigProperty;
import com.testmile.daksha.core.batteries.config.Configuration;
import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.Browser;
import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

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