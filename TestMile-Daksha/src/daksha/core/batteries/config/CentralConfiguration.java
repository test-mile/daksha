package daksha.core.batteries.config;

import java.io.File;
import java.util.Map;

import daksha.tpi.cleanup.enums.GuiAutomationContext;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;

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
		this.add(DakshaOption.GUIAUTO_MAPS_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "ui_maps");
		this.add(DakshaOption.GUIAUTO_DRIVERS_DIR, this.value(DakshaOption.TOOLS_DIR) + File.separator + "guidrivers");
		this.add(DakshaOption.GUIAUTO_SCROLL_PIXELS, "100");
		this.add(DakshaOption.GUIAUTO_SWIPE_TOP, "0.4");
		this.add(DakshaOption.GUIAUTO_SWIPE_BOTTOM, "0.7");
		this.add(DakshaOption.GUIAUTO_SWIPE_MAX_WAIT, "5");
		this.add(DakshaOption.SCREENSHOTS_DIR, this.value(DakshaOption.ROOT_DIR) + File.separator + "screenshots");
		this.add(DakshaOption.OSTYPE, "windows");
		this.add(DakshaOption.IMAGE_COMPARISON_MIN_SCORE, "0.7");
		this.add(DakshaOption.GUIAUTO_CONTEXT, GuiAutomationContext.PC_WEB.toString());
		this.add(DakshaOption.GUIAUTO_DEFAULT_BROWSER, Browser.CHROME.toString());
		this.add(DakshaOption.GUIAUTO_MAX_WAIT, "60");
	}
}
