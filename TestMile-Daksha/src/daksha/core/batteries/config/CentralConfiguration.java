package daksha.core.batteries.config;

import java.io.File;
import java.util.Map;

import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

public class CentralConfiguration extends BaseConfiguration{
	private String rootDir = null;
	
	 public CentralConfiguration(String rootDir) throws Exception {
		 super();
		 this.rootDir = rootDir;
		 populateDefaults();
	 }
	
	@Override
	public void add(String k, String v) throws Exception {
		super.add(k,v);
	 } 
	 
	@Override
	public void add(Map<String,String> map) throws Exception {
		super.add(map);
	 } 
	
	private void populateDefaults() throws Exception {
		this.add(DakshaOption.ROOT_DIR,  this.rootDir);
		this.add("project_conf_file",  this.value(DakshaOption.ROOT_DIR) + File.separator + "config" + File.separator + "project.conf");
		this.add(DakshaOption.LOG_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "log");
		this.add(DakshaOption.TOOLS_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "tools");
		this.add(DakshaOption.APPS_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "apps");
		this.add(DakshaOption.GUIAUTO_NAMESPACE_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "gui_namespace");
		this.add(DakshaOption.SCREENSHOTS_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "screenshots");
		
		this.add(DakshaOption.TESTRUN_ENVIRONMENT, "TEST");
		this.add(DakshaOption.TESTRUN_TARGET_PLATFORM, "windows");
		this.add(DakshaOption.TESTRUN_TARGET_PLATFORM_VERSION, "NOT_SET");
		
		this.add(DakshaOption.BROWSER_NAME, Browser.CHROME.toString());
		this.add(DakshaOption.BROWSER_VERSION, "");
		this.add(DakshaOption.BROWSER_MAXIMIZE, "true");
		this.add(DakshaOption.BROWSER_DIM_HEIGHT, "NOT_SET");
		this.add(DakshaOption.BROWSER_DIM_WIDTH, "NOT_SET");
		this.add(DakshaOption.BROWSER_BIN_PATH, "NOT_SET");
		this.add(DakshaOption.BROWSER_MOBILE_PROXY_ON, "false");
		
		this.add(DakshaOption.BROWSER_DIM_WIDTH, "NOT_SET");
		this.add(DakshaOption.BROWSER_BIN_PATH, "NOT_SET");
		this.add(DakshaOption.BROWSER_MOBILE_PROXY_ON, "false");
		
		this.add(DakshaOption.GUIAUTO_CONTEXT, GuiAutomationContext.WEB.toString());
		this.add(DakshaOption.GUIAUTO_SCROLL_PIXELS, "100");
		this.add(DakshaOption.GUIAUTO_SWIPE_TOP, "0.1");
		this.add(DakshaOption.GUIAUTO_SWIPE_BOTTOM, "0.5");
		this.add(DakshaOption.GUIAUTO_SWIPE_MAX_WAIT, "5");
		this.add(DakshaOption.GUIAUTO_MAX_WAIT, "60");
		
		this.add(DakshaOption.MOBILE_DEVICE_NAME, "Android Emulator");
		this.add(DakshaOption.MOBILE_DEVICE_UDID, "NOT_SET");
		this.add(DakshaOption.MOBILE_APP_FILE_PATH, null);
		
		this.add(DakshaOption.SELENIUM_DRIVERS_DIR, this.value(DakshaOption.TOOLS_DIR) + File.separator + "selenium_drivers");	
		this.add(DakshaOption.SELENIUM_DRIVER_PATH, "NOT_SET");
		
		this.add(DakshaOption.APPIUM_HUB_URL, "http://127.0.0.1:4723/wd/hub");
		this.add(DakshaOption.APPIUM_AUTO_LAUNCH, "false");
		
		this.add(DakshaOption.IMAGE_COMPARISON_MIN_SCORE, "0.7");
	}
}
