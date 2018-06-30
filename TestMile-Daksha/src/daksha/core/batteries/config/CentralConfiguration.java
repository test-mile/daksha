package daksha.core.batteries.config;

import java.io.File;
import java.util.Map;

import daksha.core.uiauto.enums.OSType;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.uiauto.enums.GuiAutomationContext;

public class CentralConfiguration extends BaseConfiguration{
	private boolean frozen = false;
	private String rootDir = null;
	
	 public CentralConfiguration(String rootDir) throws Exception {
		 super();
		 this.rootDir = rootDir;
		 populateDefaults();
	 }
	 
	 public void freeze(){
		 this.frozen = true;
	 }

	private void validateFrozen() throws Exception {
		if (frozen) {
			throw new Exception("You can not add options to central configuration after it is frozen.");
		}
	}
	
	@Override
	public void add(String k, String v) throws Exception {
		validateFrozen();
		super.add(k,v);
	 } 
	 
	@Override
	public void add(Map<String,String> map) throws Exception {
		validateFrozen();
		super.add(map);
	 } 
	
	private void populateDefaults() throws Exception {
		this.add(DakshaOption.ROOT_DIR,  this.rootDir);
		this.add(DakshaOption.LOG_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "log");
		this.add(DakshaOption.TOOLS_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "tools");
		this.add(DakshaOption.APPS_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "apps");
		this.add(DakshaOption.GUIAUTO_NAMESPACE_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "gui_namespace");
		this.add(DakshaOption.GUIAUTO_DRIVERS_DIR, this.value(DakshaOption.TOOLS_DIR) + File.separator + "guidrivers");
		this.add(DakshaOption.GUIAUTO_SCROLL_PIXELS, "100");
		this.add(DakshaOption.GUIAUTO_SWIPE_TOP, "0.1");
		this.add(DakshaOption.GUIAUTO_SWIPE_BOTTOM, "0.5");
		this.add(DakshaOption.GUIAUTO_SWIPE_MAX_WAIT, "5");
		this.add(DakshaOption.SCREENSHOTS_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "screenshots");
		this.add(DakshaOption.OSTYPE, "windows");
		this.add(DakshaOption.IMAGE_COMPARISON_MIN_SCORE, "0.7");
		this.add(DakshaOption.GUIAUTO_CONTEXT, GuiAutomationContext.WEB.toString());
		this.add(DakshaOption.GUIAUTO_DEFAULT_BROWSER, Browser.CHROME.toString());
		this.add(DakshaOption.GUIAUTO_MAX_WAIT, "60");
		this.add(DakshaOption.TEST_PC_PLATFORM, OSType.WINDOWS.toString());
		this.add(DakshaOption.TEST_MOBILE_PLATFORM, OSType.ANDROID.toString());
		this.add(DakshaOption.BROWSER_MOBILE_PROXY_ON, "false");
		this.add(DakshaOption.MOBILE_BROWSER, Browser.CHROME.toString());
		this.add(DakshaOption.APPIUM_HUB_URL, "http://127.0.0.1:4723/wd/hub");
		this.add(DakshaOption.MOBILE_DEVICE_NAME, "Android Emulator");
		this.add(DakshaOption.MOBILE_PLATFORM_VERSION, "9");
		this.add(DakshaOption.APPIUM_AUTO_LAUNCH, "false");
	}
}
