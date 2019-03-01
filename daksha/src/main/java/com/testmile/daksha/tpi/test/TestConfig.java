package com.testmile.daksha.tpi.test;

import com.testmile.daksha.core.setu.SetuManagedObject;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.Browser;

public interface TestConfig extends SetuManagedObject {

	String getLogDir() throws Exception;
	String getName();
	GuiAutomationContext getGuiAutoContext() throws Exception;
	Browser getBrowserType() throws Exception;
	String getBrowerVersion() throws Exception;
	String getBrowserBinaryPath() throws Exception;
	String getTestRunEnvName() throws Exception;
	String getScreenshotsDir() throws Exception;
	int getGuiAutoMaxWaitTime() throws Exception;

}