package com.testmile.daksha.tpi.test;

import java.util.Map;

import com.testmile.daksha.DakshaSingleton;
import com.testmile.daksha.core.config.SetuOptionContainer;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.OSType;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.StringKeyValueMap;

public class TestContext {
	private String name;
	private String parentConfigSetuId = null;
	private SetuOptionContainer setuOptions = new SetuOptionContainer();
	private StringKeyValueMap userOptions = new StringKeyValueMap();

	public TestContext(String name) {
		this.name = name;
	}
	
	protected void setParentConfig(TestConfig parentConfig) {
		this.parentConfigSetuId = parentConfig.getSetuId();
	}
	
	public void addSetuOption(SetuOption option, Object value) throws Exception {
		setuOptions.addObject(option, value);
	 }
	
	public void addUserOption(String option, Object obj) throws Exception {
		userOptions.addObject(DakshaSingleton.INSTANCE.normalizeUserOption(option), obj);
	} 
	
	public void addOption(String option, Object obj) throws Exception {
		String normalizedOption = DakshaSingleton.INSTANCE.normalizeUserOption(option);
		try {
			SetuOption sOption = SetuOption.valueOf(normalizedOption);
			this.setuOptions.addObject(sOption, obj);
		} catch (Exception e) {
			userOptions.addObject(option, obj);
		}	
	} 
	
	public void addOptions(Map<String,String> options) throws Exception {
		for(String option: options.keySet()) {
			this.addOption(option,  options.get(option));
		}
	}
	
	public TestContext automationContext(GuiAutomationContext context) throws Exception {
		addSetuOption(SetuOption.GUIAUTO_CONTEXT, context.toString());
		return this;
	}

	public TestContext browserType(Browser browser) throws Exception {
		addSetuOption(SetuOption.BROWSER_NAME, browser.toString());
		return this;
	}
	
	public TestContext targetPlatform(OSType osType) throws Exception {
		addSetuOption(SetuOption.TESTRUN_TARGET_PLATFORM, osType.toString());
		return this;
	}
	
	public TestContext uuiAutoMaxWaitTime(int seconds) throws Exception {
		addSetuOption(SetuOption.GUIAUTO_MAX_WAIT, String.valueOf(seconds));
		return this;
	}
	
	public TestConfig build() {
		return null;
	}
}
