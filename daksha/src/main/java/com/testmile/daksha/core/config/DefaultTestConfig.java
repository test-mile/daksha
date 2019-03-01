package com.testmile.daksha.core.config;

import com.testmile.daksha.Daksha;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.value.AnyRefValue;
import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.SetuOption;

public class DefaultTestConfig extends DefaultSetuObject implements TestConfig {
	private String name;
	private TestSession session;

	public DefaultTestConfig(TestSession testSession, String name, String setuId) {
		this.setSetuId(setuId);
		this.setTestSessionSetuId(testSession.getSetuId());
		this.session = session;
		this.name = name;
	}
	
	public Value value(SetuOption option) throws Exception {
		return new AnyRefValue(this.session.getSetuConfValue(option));
	}
	
	public Value getValue(SetuOption option) throws Exception{
		return this.value(option);
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public GuiAutomationContext getGuiAutoContext() throws Exception {
		return GuiAutomationContext.valueOf(value(SetuOption.GUIAUTO_CONTEXT).asString());
	}
	
	@Override
	public Browser getBrowserType() throws Exception {
		return value(SetuOption.BROWSER_NAME).asEnum(Browser.class);
	}

	@Override
	public String getBrowerVersion() throws Exception {
		return value(SetuOption.BROWSER_VERSION).asString();
	}

	@Override
	public String getBrowserBinaryPath() throws Exception {
		return value(SetuOption.BROWSER_BIN_PATH).asString();
	}
	
	@Override
	public String getTestRunEnvName() throws Exception {
		return value(SetuOption.TESTRUN_ENVIRONMENT).asString();
	}

	@Override
	public String getScreenshotsDir() throws Exception {
		return value(SetuOption.SCREENSHOTS_DIR).asString();
	}
	
	@Override
	public String getLogDir() throws Exception {
		return value(SetuOption.LOG_DIR).asString();
	}
	
	public int getGuiAutoMaxWaitTime() throws Exception {
		return value(SetuOption.GUIAUTO_MAX_WAIT).asInt();
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
