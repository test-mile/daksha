package daksha.core.batteries.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import daksha.Daksha;
import daksha.core.guiauto.enums.OSType;
import daksha.tpi.TestContext;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

public class DakshaTestContext implements TestContext {
	private String name;
	private ContextConfiguration config;
	
	public DakshaTestContext(String name, Map<String, String> map) throws Exception {
		this.name = name;		
		config = new ContextConfiguration(Daksha.getFrozenCentralConfig(), map);
	}
	
	public DakshaTestContext(String name) throws Exception {
		this(name, new HashMap<String, String>());
	}
	
	@Override
	public void add(DakshaOption option, String value) throws Exception {
		this.config.add(option, value);
	}
	
	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getAsMap()
	 */
	@Override
	public Map<DakshaOption, Value> asMap() throws Exception{
		return this.config.getAllOptions().items();
	}
	
	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getConfig()
	 */
	@Override
	public Configuration getConfig() {
		return this.config;
	}

	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getAutomationContext()
	 */
	@Override
	public GuiAutomationContext getGuiAutoContext() throws Exception {
		return GuiAutomationContext.valueOf(getOptionValue(DakshaOption.GUIAUTO_CONTEXT).asString());
	}

	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#setAutomationContext(daksha.tpi.guiauto.enums.GuiAutomationContext)
	 */
	@Override
	public void setAutomationContext(GuiAutomationContext context) throws Exception {
		this.config.add(DakshaOption.GUIAUTO_CONTEXT, context.toString());
	}
	
	/* (non-Javadoc)
	 * @see daksha.core.batteries.config.TestContext#getAppDir()
	 */
	@Override
	public String getAppDir() throws Exception {
		GuiAutomationContext aContext = getOptionValue(DakshaOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
		Value testOS = null;
		if (GuiAutomationContext.isDesktopContext(aContext)) {
			testOS = getOptionValue(DakshaOption.TEST_PC_PLATFORM);
		} else {
			testOS = getOptionValue(DakshaOption.TEST_MOBILE_PLATFORM);
		}
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
		Value driverPathValue = config.value(DakshaOption.SELENIUM_DRIVER_PATH);
		if (!driverPathValue.isNotSet()) {
			return driverPathValue.asString();
		}
		
		// Validate a wrongly made driver path call
		Daksha.getSeleniumDriverPathSystemProperty(browser);
		
		// Construct and return the path
		String driversDir = this.config.value(DakshaOption.SELENIUM_DRIVERS_DIR).asString();
		String os = this.config.value(DakshaOption.OSTYPE).asString().toLowerCase();
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

}
