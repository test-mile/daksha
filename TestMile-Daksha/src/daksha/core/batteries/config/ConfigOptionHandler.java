package daksha.core.batteries.config;

import java.util.HashMap;
import java.util.Map;

import daksha.core.guiauto.enums.OSType;
import daksha.core.value.StringValue;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

public class ConfigOptionHandler {
	private ConfigPropertyBuilder builder = new ConfigPropertyBuilder();
	private Map<String, DakshaOption> pathToEnumMap = new HashMap<String, DakshaOption>();
	private Map<DakshaOption, String> enumToPathMap = new HashMap<DakshaOption, String>();

	public ConfigOptionHandler() {
		for (DakshaOption e : DakshaOption.class.getEnumConstants()) {
			String path = Configuration.convertOptionToPath(e);
			pathToEnumMap.put(path.toLowerCase(), e);
			enumToPathMap.put(e, path.toLowerCase());
		}
	}
	
	private DakshaOption codeForPath(String propPath) {
		return pathToEnumMap.get(propPath.toLowerCase());
	}
	
	protected void processCoreDirOption(
			Map<String, ConfigProperty> options,
			String propPath, 
			Value configValue, 
			String purpose, 
			boolean visible) throws Exception {
		ConfigProperty prop = ConfigPropertyBatteries.createCoreDirPath(codeForPath(propPath), propPath,
				configValue, purpose, visible);
		options.put(propPath, prop);
	}
	
	protected void processStringOption(
			Map<String, ConfigProperty> options, 
			String propPath, 
			Value configValue, 
			String purpose, 
			boolean visible) throws Exception {
		ConfigProperty prop = ConfigPropertyBatteries.createStringProperty(codeForPath(propPath), propPath, configValue, purpose, visible);
		options.put(propPath, prop);
	}
	
	protected void processBooleanOption(
			Map<String, ConfigProperty> options, 
			String propPath, 
			Value configValue, 
			String purpose, 
			boolean visible) throws Exception {
		ConfigProperty prop = ConfigPropertyBatteries.createBooleanProperty(codeForPath(propPath), propPath,
				configValue, purpose, visible);
		options.put(propPath, prop);
	}
	
	protected void processIntOption(
			Map<String, ConfigProperty> options,
			String propPath, 
			Value configValue, 
			String purpose, 
			boolean visible) throws Exception{
		ConfigProperty prop = ConfigPropertyBatteries.createNumberProperty(codeForPath(propPath), propPath, configValue, purpose, visible);
		if ((!prop.value().isNull()) && (!prop.value().isNotSet()) && (prop.value().asInt() < 0)){
			System.err.println(String.format("Critical Error. %s property should be a positive integer.", propPath));
			System.err.println(String.format("You provided: %s", prop.value().asInt()));
			System.err.println("Exiting...");
			System.exit(1);	
		}
		options.put(propPath, prop);		
	}
	
	protected void processDoubleOption(
			Map<String, ConfigProperty> options,
			String propPath, 
			Value configValue, 
			String purpose, 
			boolean visible) throws Exception{
		ConfigProperty prop = ConfigPropertyBatteries.createNumberProperty(codeForPath(propPath), propPath, configValue, purpose, visible);
		if ((!prop.value().isNull()) && (!prop.value().isNotSet()) && (prop.value().asDouble() < 0.0)){
			System.err.println(String.format("Critical Error. %s property should be a positive number.", propPath));
			System.err.println(String.format("You provided: %s", prop.value().asInt()));
			System.err.println("Exiting...");
			System.exit(1);	
		}
		options.put(propPath, prop);	
	}
	
	protected void processBrowserNameProperty(
			Map<String, ConfigProperty> options,
			String propPath, 
			Value configValue, 
			String purpose, 
			boolean visible) throws Exception {
		try{
			ConfigProperty prop = ConfigPropertyBatteries.createEnumProperty(codeForPath(propPath), propPath,
					Browser.class, configValue, purpose, visible);
			options.put(propPath, prop);
		} catch (Exception e){
			throw new Exception("Error in processing Browser configuration: " + e.getMessage());
		}
	}
	
	protected void processTargetPlatformOption(
			Map<String, ConfigProperty> options,
			String propPath, 
			Value configValue, 
			String purpose, 
			boolean visible) throws Exception {
		try{
			ConfigProperty prop = ConfigPropertyBatteries.createEnumProperty(codeForPath(propPath), propPath,
					OSType.class, configValue, purpose, visible);
			options.put(propPath, prop);
		} catch (Exception e){
			throw new Exception("Error in processing Target Platform configuration: " + e.getMessage());
		}
	}
	
	protected void processGuiAutoContextProperty(
			Map<String, ConfigProperty> options,
			String propPath, 
			Value configValue, 
			String purpose, 
			boolean visible) throws Exception {
		try{
			ConfigProperty prop = ConfigPropertyBatteries.createEnumProperty(codeForPath(propPath), propPath,
					GuiAutomationContext.class, configValue, purpose, visible);
			options.put(propPath, prop);
		} catch (Exception e){
			throw new Exception("Error in processing Gui Automation Context configuration: " + e.getMessage());
		}
	}
	
	private void processCustomUserOption(Map<String, ConfigProperty> options, String propPath, Value value) throws Exception {
		ConfigProperty prop = ConfigPropertyBatteries.createSimpleProperty(propPath, value);
		options.put(propPath, prop);
	}
	
	private StringValue asStringValue(String str) {
		return new StringValue(str);
	}
	
	public void process(Map<String, ConfigProperty> options, String option, Value value) throws Exception{
		String propPath = Configuration.convertOptionToPath(option);
		if (pathToEnumMap.containsKey(propPath)){
			switch(pathToEnumMap.get(propPath)){
			// Daksha Directories
			case ROOT_DIR:
				processCoreDirOption(options, propPath, value, "Root Directory", true);
				break;
			case PROJECT_CONF_FILE:
				processCoreDirOption(options, propPath, value, "File Path for project level daksha.conf file.", true);
				break;
			case LOG_DIR:
				processCoreDirOption(options, propPath, value, "Log Directory", true);
				break;
			case DATA_SOURCES_DIR:
				processCoreDirOption(options, propPath, value, "Data Files Directory", true);
				break;
			case TOOLS_DIR:
				processCoreDirOption(options, propPath, value, "Tools Directory", true);
				break;
			case APPS_DIR:
				processCoreDirOption(options, propPath, value, "Apps Directory", true);
				break;
			case GUIAUTO_NAMESPACE_DIR:
				processCoreDirOption(options, propPath, value, "Gui Namespace Files Directory", true);
				break;
			case SCREENSHOTS_DIR:
				processCoreDirOption(options, propPath, value, "Screenshots Directory", true);
				break;

			// Test Run
			case TESTRUN_ENVIRONMENT:
				processStringOption(options, propPath, value, "Test Run Environment Name", true);
				break;
			case TESTRUN_TARGET_PLATFORM:
				processTargetPlatformOption(options, propPath, value, "Test Run Target Platform Version", true);
				break;
			case TESTRUN_TARGET_PLATFORM_VERSION:
				processStringOption(options, propPath, value, "Test Run Target Platform Version", true);
				break;
			
			// Browser (Common)
			case BROWSER_NAME:
				processBrowserNameProperty(options, propPath, value, "Browser Name", true);
				break;
			case BROWSER_VERSION:
				processStringOption(options, propPath, value, "Browser Version", true);
				break;
			case BROWSER_MAXIMIZE:
				processBooleanOption(options, propPath, value, "Should maximize browser?", true);
				break;
			case BROWSER_DIM_WIDTH:
				processIntOption(options, propPath, value, "Browser Width in Pixels", true);
				break;
			case BROWSER_DIM_HEIGHT:
				processIntOption(options, propPath, value, "Browser Height in Pixels", true);
				break;
			case BROWSER_BIN_PATH:
				processCoreDirOption(options, propPath, value, "Browser Binary Path", true);
				break;
			case BROWSER_MOBILE_PROXY_HOST:
				processStringOption(options, propPath, value, "Mobile Web Browser Proxy Host Name", true);
				break;
			case BROWSER_MOBILE_PROXY_ON:
				processBooleanOption(options, propPath, value, "Should enable proxy for Web Browser on Mobile?", true);
				break;
			case BROWSER_MOBILE_PROXY_PORT:
				processIntOption(options, propPath, value, "Mobile Web Browser Proxy Port Number", true);
				break;
			case BROWSER_PC_PROXY_HOST:
				processStringOption(options, propPath, value, "PC Web Browser Proxy Host Name", true);
				break;
			case BROWSER_PC_PROXY_ON:
				processBooleanOption(options, propPath, value, "Should enable proxy for PC Web Browser?", true);
				break;
			case BROWSER_PC_PROXY_PORT:
				processIntOption(options, propPath, value, "PC Web Browser Proxy Port Number", true);
				break;
			
			// Browser Specific
			case FIREFOX_WINDOWNAME:
				processStringOption(options, propPath, value, "Firefox Browser Window Name", true);
				break;
			case CHROME_WINDOWNAME:
				processStringOption(options, propPath, value, "Chrome Browser Window Name", true);
				break;
			case SAFARI_WINDOWNAME:
				processStringOption(options, propPath, value, "Safari Browser Window Name", true);
				break;
			
			// Application
			case APP_URL:
				processStringOption(options, propPath, value, "Web Application URL", true);
				break;
			
			// General Gui Automation
			case GUIAUTO_CONTEXT:
				processGuiAutoContextProperty(options, propPath, value, "Gui Automation Context", true);
				break;
			case GUIAUTO_SCROLL_PIXELS:
				processIntOption(options, propPath, value, "Number of Pixels to Scroll", true);
				break;
			case GUIAUTO_SWIPE_TOP:
				processDoubleOption(options, propPath, value, "Top Mobile Screen Fraction for Swipe", true);
				break;
			case GUIAUTO_SWIPE_BOTTOM:
				processDoubleOption(options, propPath, value, "Bottom Mobile Screen Fraction for Swipe", true);
				break;
			case GUIAUTO_SWIPE_MAX_WAIT:
				processDoubleOption(options, propPath, value, "Max Wait Time for Mobile Swipe Action (seconds)", true);
				break;
			case GUIAUTO_MAX_WAIT:
				processDoubleOption(options, propPath, value, "Max Wait Time for Gui Automation Element Finding and State Readiness (seconds)", true);
				break;
			
			// Mobile automation
			case MOBILE_DEVICE_NAME:
				processStringOption(options, propPath, value, "Mobile Device Name", true);
				break;
			case MOBILE_DEVICE_UDID:
				processStringOption(options, propPath, value, "Mobile Device UDID", true);
				break;
			case MOBILE_APP_FILE_PATH:
				processCoreDirOption(options, propPath, value, "Mobile App File Path", true);
				break;

			// Selenium
			case SELENIUM_DRIVERS_DIR:
				processCoreDirOption(options, propPath, value, "Selenium Drivers Directory", true);
				break;
			case SELENIUM_DRIVER_PATH:
				processCoreDirOption(options, propPath, value, "Selenium Driver Path", true);
				break;

			// Appium
			case APPIUM_HUB_URL:
				processStringOption(options, propPath, value, "Appiu Hub URL", true);
				break;
			case APPIUM_AUTO_LAUNCH:
				processBooleanOption(options, propPath, value, "Should Daksha launch Appium?", true);
				break;
			
			// Image comparison
			case IMAGE_COMPARISON_MIN_SCORE:
				processDoubleOption(options, propPath, value, "Minimum Image Comparison Score to Consider Successful Match.", true);
				break;
			}
		} else {
			processCustomUserOption(options, propPath, value);
		}
	}
	
	public void process(Map<String, ConfigProperty> options, String option, String value) throws Exception {
		this.process(options, option, asStringValue(value));
	}

	public void process(Map<String, ConfigProperty> options, DakshaOption option, String value) throws Exception {
		this.process(options, Configuration.convertOptionToPath(option), value);
	}

}
