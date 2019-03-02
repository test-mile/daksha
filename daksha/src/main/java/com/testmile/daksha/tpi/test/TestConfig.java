package com.testmile.daksha.tpi.test;

import com.testmile.daksha.core.setu.SetuManagedObject;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.Value;

public interface TestConfig extends SetuManagedObject {
	
	Value getSetuOptionValue(String option) throws Exception;	
	Value getSetuOptionValue(SetuOption option) throws Exception;
	Value getUserOptionValue(String option) throws Exception;
	
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