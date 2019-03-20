package com.testmile.arjuna.tpi.test;

import com.testmile.arjuna.lib.enums.BrowserName;
import com.testmile.arjuna.lib.enums.GuiAutomationContext;
import com.testmile.arjuna.lib.setu.requester.config.SetuTestConfig;

public interface TestConfig extends SetuTestConfig {
	
	String getLogDir() throws Exception;
	GuiAutomationContext getGuiAutoContext() throws Exception;
	BrowserName getBrowserType() throws Exception;
	String getBrowerVersion() throws Exception;
	String getBrowserBinaryPath() throws Exception;
	String getTestRunEnvName() throws Exception;
	String getScreenshotsDir() throws Exception;
	int getGuiAutoMaxWaitTime() throws Exception;
}
