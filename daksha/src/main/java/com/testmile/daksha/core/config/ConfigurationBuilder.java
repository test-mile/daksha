package com.testmile.daksha.core.config;

import java.util.HashMap;
import java.util.Map;

import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.OSType;
import com.testmile.trishanku.tpi.enums.SetuOption;

public class ConfigurationBuilder {
	private String name;
	private Map<SetuOption, Object> setuOptions = new HashMap<SetuOption, Object>();
	private Map<String, Object> userOptions = new HashMap<String, Object>();

	public ConfigurationBuilder(String name) {
		this.name = name;
	}
	
	public void addSetuOption(SetuOption option, Object value) throws Exception {
		setuOptions.put(option, value);
	 } 
	
	
	public void addUserOption(String option, Object value) throws Exception {
		userOptions.put(option, value);
	} 
	
	public ConfigurationBuilder automationContext(GuiAutomationContext context) throws Exception {
		addSetuOption(SetuOption.GUIAUTO_CONTEXT, context.toString());
		return this;
	}

	public ConfigurationBuilder browserType(Browser browser) throws Exception {
		addSetuOption(SetuOption.BROWSER_NAME, browser.toString());
		return this;
	}
	
	public ConfigurationBuilder targetPlatform(OSType osType) throws Exception {
		addSetuOption(SetuOption.TESTRUN_TARGET_PLATFORM, osType.toString());
		return this;
	}
	
	public ConfigurationBuilder uuiAutoMaxWaitTime(int seconds) throws Exception {
		addSetuOption(SetuOption.GUIAUTO_MAX_WAIT, String.valueOf(seconds));
		return this;
	}
	
	public TestConfig build() {
		return null;
	}
}
