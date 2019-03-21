package com.testmile.arjuna.lib.core.config;

import java.util.Map;

import com.testmile.arjuna.lib.core.value.AbstractValueMap;
import com.testmile.arjuna.lib.core.value.StringKeyValueMap;
import com.testmile.arjuna.lib.enums.BrowserName;
import com.testmile.arjuna.lib.enums.GuiAutomationContext;
import com.testmile.arjuna.lib.setu.core.requester.config.DefaultTestConfig;
import com.testmile.arjuna.lib.setu.guiauto.requester.automator.GuiAutomatorName;
import com.testmile.arjuna.lib.setu.testsession.requester.TestSession;
import com.testmile.arjuna.lib.state.ArjunaSingleton;
import com.testmile.arjuna.tpi.enums.ArjunaOption;
import com.testmile.arjuna.tpi.test.TestConfig;
import com.testmile.arjuna.tpi.test.TestContext;

public class DefaultTestContext implements TestContext {
	private String name;
	private String parentConfigSetuId = null;
	private TestSession testSession;
	private SetuOptionContainer setuOptions = new SetuOptionContainer();
	private StringKeyValueMap userOptions = new StringKeyValueMap();

	public DefaultTestContext(TestSession session, String name) {
		this.testSession = session;
		this.name = name;
	}
	
	protected void setParentConfig(TestConfig parentConfig) {
		this.parentConfigSetuId = parentConfig.getSetuId();
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addSetuOption(com.testmile.trishanku.tpi.enums.SetuOption, java.lang.Object)
	 */
	@Override
	public void addSetuOption(ArjunaOption option, Object value) throws Exception {
		setuOptions.addObject(option, value);
	 }
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addUserOption(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addUserOption(String option, Object obj) throws Exception {
		userOptions.addObject(ArjunaSingleton.INSTANCE.normalizeUserOption(option), obj);
	} 
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addOption(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addOption(String option, Object obj) throws Exception {
		String normalizedOption = ArjunaSingleton.INSTANCE.normalizeUserOption(option);
		System.out.println(normalizedOption);
		try {
			ArjunaOption sOption = ArjunaOption.valueOf(normalizedOption);
			this.setuOptions.addObject(sOption, obj);
		} catch (Exception e) {
			userOptions.addObject(option, obj);
		}	
	} 
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addOptions(java.util.Map)
	 */
	@Override
	public void addOptions(Map<String,String> options) throws Exception {
		for(String option: options.keySet()) {
			this.addOption(option,  options.get(option));
		}
	}
	
	public TestContext selenium() throws Exception {
		this.addSetuOption(ArjunaOption.GUIAUTO_AUTOMATOR_NAME, GuiAutomatorName.SELENIUM.toString());
		return this;
	}	
	
	public TestContext appium(GuiAutomationContext context) throws Exception {
		this.addSetuOption(ArjunaOption.GUIAUTO_AUTOMATOR_NAME, GuiAutomatorName.APPIUM.toString());
		this.addSetuOption(ArjunaOption.GUIAUTO_CONTEXT, context);
		return this;
	}
	
	public TestContext chrome() throws Exception {
		this.addSetuOption(ArjunaOption.BROWSER_NAME, BrowserName.CHROME);
		return this;
	}	
	
	public TestContext firefox() throws Exception {
		this.addSetuOption(ArjunaOption.BROWSER_NAME, BrowserName.FIREFOX);
		return this;
	}	
	
	public TestContext headlessMode() throws Exception {
		this.addSetuOption(ArjunaOption.BROWSER_HEADLESS_MODE, true);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#uuiAutoMaxWaitTime(int)
	 */
	@Override
	public TestContext guiAutoMaxWaitTime(int seconds) throws Exception {
		addSetuOption(ArjunaOption.GUIAUTO_MAX_WAIT, String.valueOf(seconds));
		return this;
	}
	
	public TestContext app(String path) throws Exception {
		addSetuOption(ArjunaOption.MOBILE_APP_FILE_PATH, path);
		return this;
	}
	
	public TestContext mobileDeviceName(String name) throws Exception {
		addSetuOption(ArjunaOption.MOBILE_DEVICE_NAME, name);
		return this;
	}
	
	public TestContext mobileDeviceUdid(String udid) throws Exception {
		addSetuOption(ArjunaOption.MOBILE_DEVICE_UDID, udid);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#build()
	 */
	@Override
	public TestConfig build() throws Exception {
		String configSetuId;
		System.out.println(parentConfigSetuId);
		if (this.parentConfigSetuId == null) {
			configSetuId = this.testSession.registerConfig(setuOptions.strItems(), userOptions.items());
		} else {
			configSetuId = this.testSession.registerChildConfig(this.parentConfigSetuId, setuOptions.strItems(), userOptions.items());
		}
		return new DefaultTestConfig(this.testSession, this.name, configSetuId);
	}
}

class SetuOptionContainer extends AbstractValueMap<ArjunaOption> {

	@Override
	protected String formatKeyAsStr(ArjunaOption key) {
		return key.toString();
	}

}

