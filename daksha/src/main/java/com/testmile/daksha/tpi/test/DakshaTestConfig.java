package com.testmile.daksha.tpi.test;

import com.testmile.setu.requester.config.TestConfig;
import com.testmile.trishanku.tpi.enums.BrowserName;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;

public interface DakshaTestConfig extends TestConfig {
	
	String getLogDir() throws Exception;
	GuiAutomationContext getGuiAutoContext() throws Exception;
	BrowserName getBrowserType() throws Exception;
	String getBrowerVersion() throws Exception;
	String getBrowserBinaryPath() throws Exception;
	String getTestRunEnvName() throws Exception;
	String getScreenshotsDir() throws Exception;
	int getGuiAutoMaxWaitTime() throws Exception;
}
