package com.testmile.daksha.core.batteries.context;

import java.io.File;
import java.util.Map;

import com.testmile.daksha.core.batteries.config.ConfigProperty;
import com.testmile.daksha.core.batteries.config.Configuration;
import com.testmile.daksha.core.batteries.config.ContextConfiguration;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.Browser;
import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.Trishanku;
import com.testmile.trishanku.TrishankuSingleton;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

public abstract class BaseTestContext implements TestContext {
	protected String name;
	protected ContextConfiguration config;
	private boolean frozen = false;

	public BaseTestContext(String name) {
		this.name = name;
	}
	
	protected void setContextConfig(ContextConfiguration config) {
		this.config = config;
	}
	
	 public void freeze() throws Exception{
		Map<DakshaOption, String> properties = TrishankuSingleton.INSTANCE.getDakshaCentralCliOptions();
		for (DakshaOption name: properties.keySet()) {
			setOption(name, properties.get(name));
		}
		
		Map<String, String> userProps = TrishankuSingleton.INSTANCE.getUserCentralCliOptions();
		for (String name: userProps.keySet()) {
			setOption(name, userProps.get(name));
		}
		this.frozen = true;
	 }

	protected void validateFrozen() throws Exception {
		if (frozen) {
			throw new Exception("You can not add/modify options of a context after it is frozen.");
		}
	}

	@Override
	public void setOption(DakshaOption option, String value) throws Exception {
		validateFrozen();
		this.config.add(option, value);
	}
	
	@Override
	public void setOption(String option, String value) throws Exception {
		validateFrozen();
		this.config.add(option, value);
	}
	
	@Override
	public void addAll(Map<String, Value> properties) throws Exception {
		validateFrozen();
		for (String name: properties.keySet()) {
			this.config.add(name, properties.get(name));
		}
	}

	@Override
	public Map<String, ConfigProperty> asMap() throws Exception {
		return this.config.getAllOptions();
	}

	@Override
	public Map<String, String> asRawMap() throws Exception {
		return this.config.getAllItems();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Configuration getConfig() {
		return this.config;
	}

	@Override
	public GuiAutomationContext getGuiAutoContext() throws Exception {
		return GuiAutomationContext.valueOf(getValue(DakshaOption.GUIAUTO_CONTEXT).asString());
	}

	@Override
	public void setAutomationContext(GuiAutomationContext context) throws Exception {
		setOption(DakshaOption.GUIAUTO_CONTEXT, context.toString());
	}

	@Override
	public String getAppDir() throws Exception {
		GuiAutomationContext aContext = getValue(DakshaOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
		Value testOS = this.getValue(DakshaOption.TESTRUN_TARGET_PLATFORM);
		return getValue(DakshaOption.APPS_DIR).asString() 
				+ File.separator 
				+ testOS.asString().toLowerCase() + File.separator; 
	}

	@Override
	public Browser getBrowserType() throws Exception {
		return this.config.value(DakshaOption.BROWSER_NAME).asEnum(Browser.class);
	}

	@Override
	public Value getValue(DakshaOption option) throws Exception {
		return this.config.value(option);
	}
	
	@Override
	public Value getValue(String option) throws Exception {
		return this.config.value(option);
	}

	@Override
	public void setBrowserType(Browser browser) throws Exception {
		this.setOption(DakshaOption.BROWSER_NAME, browser.toString());
	}

	@Override
	public String getBrowerVersion() throws Exception {
		return getValue(DakshaOption.BROWSER_VERSION).asString();
	}

	@Override
	public String getBrowserBinaryPath() throws Exception {
		return getValue(DakshaOption.BROWSER_BIN_PATH).asString();
	}

	private String modifyExeNameForWindows(String name) {
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			return name + ".exe";
		} else {
			return name;
		}
	}

	@Override
	public String getSeleniumDriverPath(Browser browser) throws Exception {
		Value driverPathValue = getValue(DakshaOption.SELENIUM_DRIVER_PATH);
		if (!driverPathValue.isNotSet()) {
			return driverPathValue.asString();
		}
		
		// Validate a wrongly made driver path call
//		Trishanku.getSeleniumDriverPathSystemProperty(browser);
		
		// Construct and return the path
		String driversDir = getValue(DakshaOption.SELENIUM_DRIVERS_DIR).asString();
		String os = getValue(DakshaOption.TESTRUN_TARGET_PLATFORM).asString().toLowerCase();
//		String binName = modifyExeNameForWindows(Trishanku.getDriverName(browser));
//		String driverPath = driversDir + File.separator + os + File.separator + binName;
		//return driverPath;
		return null;
	}

	@Override
	public String getTestRunEnvName() throws Exception {
		return getValue(DakshaOption.TESTRUN_ENVIRONMENT).asString();
	}

	@Override
	public String getScreenshotsDir() throws Exception {
		return getValue(DakshaOption.SCREENSHOTS_DIR).asString();
	}
	
	@Override
	public String getLogDir() throws Exception {
		return getValue(DakshaOption.LOG_DIR).asString();
	}

	@Override
	public void setTargetPlatform(OSType osType) throws Exception {
		this.setOption(DakshaOption.TESTRUN_TARGET_PLATFORM, osType.toString());
	}
	
	@Override
	public void setGuiAutoMaxWaitTime(int seconds) throws Exception {
		this.setOption(DakshaOption.GUIAUTO_MAX_WAIT, String.valueOf(seconds));
	}

	@Override
	public int getGuiAutoMaxWaitTime() throws Exception {
		return getValue(DakshaOption.GUIAUTO_MAX_WAIT).asInt();
	}

}