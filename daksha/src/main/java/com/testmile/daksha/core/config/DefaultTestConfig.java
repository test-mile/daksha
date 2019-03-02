package com.testmile.daksha.core.config;

import com.testmile.daksha.DakshaSingleton;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.value.AnyRefValue;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.Value;

public class DefaultTestConfig extends DefaultSetuObject implements TestConfig {
	private String name;
	private TestSession session;

	public DefaultTestConfig(TestSession testSession, String name, String setuId) {
		this.setSetuId(setuId);
		this.setTestSessionSetuId(testSession.getSetuId());
		this.session = testSession;
		this.name = name;
	}
	
	public Value getSetuOptionValue(String option) throws Exception{
		return this.session.getSetuOptionValue(
				this.getSetuId(),
				DakshaSingleton.INSTANCE.normalizeSetuOption(option).toString()
		);
	}	
	
	public Value getSetuOptionValue(SetuOption option) throws Exception{
		return this.session.getSetuOptionValue(
				this.getSetuId(),
				option.toString()
		);
	}
	
	public Value getUserOptionValue(String option) throws Exception{
		return this.session.getUserOptionValue(
				this.getSetuId(),
				DakshaSingleton.INSTANCE.normalizeUserOption(option)
		);
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public GuiAutomationContext getGuiAutoContext() throws Exception {
		return GuiAutomationContext.valueOf(getSetuOptionValue(SetuOption.GUIAUTO_CONTEXT).asString());
	}
	
	@Override
	public Browser getBrowserType() throws Exception {
		return getSetuOptionValue(SetuOption.BROWSER_NAME).asEnum(Browser.class);
	}

	@Override
	public String getBrowerVersion() throws Exception {
		return getSetuOptionValue(SetuOption.BROWSER_VERSION).asString();
	}

	@Override
	public String getBrowserBinaryPath() throws Exception {
		return getSetuOptionValue(SetuOption.BROWSER_BIN_PATH).asString();
	}
	
	@Override
	public String getTestRunEnvName() throws Exception {
		return getSetuOptionValue(SetuOption.TESTRUN_ENVIRONMENT).asString();
	}

	@Override
	public String getScreenshotsDir() throws Exception {
		return getSetuOptionValue(SetuOption.SCREENSHOTS_DIR).asString();
	}
	
	@Override
	public String getLogDir() throws Exception {
		return getSetuOptionValue(SetuOption.LOG_DIR).asString();
	}
	
	public int getGuiAutoMaxWaitTime() throws Exception {
		return getSetuOptionValue(SetuOption.GUIAUTO_MAX_WAIT).asInt();
	}
	
	// For Setu
//	public String getAppDir() throws Exception {
//		GuiAutomationContext aContext = getValue(SetuOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
//		Value testOS = this.getValue(SetuOption.TESTRUN_TARGET_PLATFORM);
//		return getValue(SetuOption.APPS_DIR).asString() 
//				+ File.separator 
//				+ testOS.asString().toLowerCase() + File.separator; 
//	}
	
//	@Override
//	public String getSeleniumDriverPath(Browser browser) throws Exception {
//		Value driverPathValue = getValue(SetuOption.SELENIUM_DRIVER_PATH);
//		if (!driverPathValue.isNotSet()) {
//			return driverPathValue.asString();
//		}
//		
//		// Validate a wrongly made driver path call
////		Trishanku.getSeleniumDriverPathSystemProperty(browser);
//		
//		// Construct and return the path
//		String driversDir = getValue(SetuOption.SELENIUM_DRIVERS_DIR).asString();
//		String os = getValue(SetuOption.TESTRUN_TARGET_PLATFORM).asString().toLowerCase();
////		String binName = modifyExeNameForWindows(Trishanku.getDriverName(browser));
////		String driverPath = driversDir + File.separator + os + File.separator + binName;
//		//return driverPath;
//		return null;
//	}
	
//	private String modifyExeNameForWindows(String name) {
//		if (System.getProperty("os.name").toLowerCase().contains("win")) {
//			return name + ".exe";
//		} else {
//			return name;
//		}
//	}

}
