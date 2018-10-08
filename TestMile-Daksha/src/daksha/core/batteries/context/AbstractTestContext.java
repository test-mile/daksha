package daksha.core.batteries.context;

import java.io.File;
import java.util.Map;

import daksha.Daksha;
import daksha.core.batteries.config.ConfigProperty;
import daksha.core.batteries.config.Configuration;
import daksha.core.batteries.config.ContextConfiguration;
import daksha.core.guiauto.enums.OSType;
import daksha.tpi.TestContext;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

public class AbstractTestContext implements TestContext {
	protected String name;
	protected ContextConfiguration config;
	private boolean frozen = false;

	public AbstractTestContext(String name) {
		this.name = name;
	}
	
	protected void setContextConfig(ContextConfiguration config) {
		this.config = config;
	}
	
	 protected void setFrozen(){
		 this.frozen = true;
	 }

	private void validateFrozen() throws Exception {
		if (frozen) {
			throw new Exception("You can not add/modify options of a context after it is frozen.");
		}
	}

	@Override
	public void add(DakshaOption option, String value) throws Exception {
		validateFrozen();
		this.config.add(option, value);
	}

	@Override
	public Map<String, ConfigProperty> asMap() throws Exception {
		return this.config.getAllOptions();
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
		return GuiAutomationContext.valueOf(getOptionValue(DakshaOption.GUIAUTO_CONTEXT).asString());
	}

	@Override
	public void setAutomationContext(GuiAutomationContext context) throws Exception {
		add(DakshaOption.GUIAUTO_CONTEXT, context.toString());
	}

	@Override
	public String getAppDir() throws Exception {
		GuiAutomationContext aContext = getOptionValue(DakshaOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
		Value testOS = this.getOptionValue(DakshaOption.TESTRUN_TARGET_PLATFORM);
		return getOptionValue(DakshaOption.APPS_DIR).asString() 
				+ File.separator 
				+ testOS.asString().toLowerCase() + File.separator; 
	}

	@Override
	public Browser getBrowserType() throws Exception {
		return this.config.value(DakshaOption.BROWSER_NAME).asEnum(Browser.class);
	}

	@Override
	public Value getOptionValue(DakshaOption option) throws Exception {
		return this.config.value(option);
	}

	@Override
	public void setBrowserType(Browser browser) throws Exception {
		this.add(DakshaOption.BROWSER_NAME, browser.toString());
	}

	@Override
	public String getBrowerVersion() throws Exception {
		return getOptionValue(DakshaOption.BROWSER_VERSION).asString();
	}

	@Override
	public String getBrowserBinaryPath() throws Exception {
		return getOptionValue(DakshaOption.BROWSER_BIN_PATH).asString();
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
		Value driverPathValue = getOptionValue(DakshaOption.SELENIUM_DRIVER_PATH);
		if (!driverPathValue.isNotSet()) {
			return driverPathValue.asString();
		}
		
		// Validate a wrongly made driver path call
		Daksha.getSeleniumDriverPathSystemProperty(browser);
		
		// Construct and return the path
		String driversDir = getOptionValue(DakshaOption.SELENIUM_DRIVERS_DIR).asString();
		String os = getOptionValue(DakshaOption.TESTRUN_TARGET_PLATFORM).asString().toLowerCase();
		String binName = modifyExeNameForWindows(Daksha.getDriverName(browser));
		String driverPath = driversDir + File.separator + os + File.separator + binName;
		return driverPath;
	}

	@Override
	public String getTestRunEnvName() throws Exception {
		return getOptionValue(DakshaOption.TESTRUN_ENVIRONMENT).asString();
	}

	@Override
	public String getScreenshotsDir() throws Exception {
		return getOptionValue(DakshaOption.SCREENSHOTS_DIR).asString();
	}
	
	@Override
	public String getLogDir() throws Exception {
		return getOptionValue(DakshaOption.LOG_DIR).asString();
	}

	@Override
	public void setTargetPlatform(OSType osType) throws Exception {
		this.add(DakshaOption.TESTRUN_TARGET_PLATFORM, osType.toString());
	}

	@Override
	public int getGuiAutoMaxWaitTime() throws Exception {
		return getOptionValue(DakshaOption.GUIAUTO_MAX_WAIT).asInt();
	}

}