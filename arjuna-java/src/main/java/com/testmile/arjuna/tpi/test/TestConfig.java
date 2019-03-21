package com.testmile.arjuna.tpi.test;

import com.testmile.arjuna.lib.enums.BrowserName;
import com.testmile.arjuna.lib.enums.GuiAutomationContext;
import com.testmile.arjuna.lib.setu.core.requester.connector.SetuManagedObject;
import com.testmile.arjuna.lib.setu.testsession.requester.TestSession;
import com.testmile.arjuna.tpi.enums.ArjunaOption;
import com.testmile.arjuna.tpi.value.Value;

public interface TestConfig extends SetuManagedObject{
	Value getArjunaOptionValue(String option) throws Exception;	
	Value getArjunaOptionValue(ArjunaOption option) throws Exception;
	Value getUserOptionValue(String option) throws Exception;
	String getName();
	TestSession getTestSession();	
	String getLogDir() throws Exception;
	GuiAutomationContext getGuiAutoContext() throws Exception;
	BrowserName getBrowserType() throws Exception;
	String getBrowerVersion() throws Exception;
	String getBrowserBinaryPath() throws Exception;
	String getTestRunEnvName() throws Exception;
	String getScreenshotsDir() throws Exception;
	int getGuiAutoMaxWaitTime() throws Exception;
}
