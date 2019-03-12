package com.testmile.daksha.core.config;

import java.util.Map;

import com.testmile.daksha.DakshaSingleton;
import com.testmile.daksha.tpi.test.DakshaTestConfig;
import com.testmile.daksha.tpi.test.TestContext;
import com.testmile.setu.requester.config.TestConfig;
import com.testmile.setu.requester.guiauto.automator.GuiAutomatorName;
import com.testmile.setu.requester.testsession.TestSession;
import com.testmile.trishanku.tpi.enums.BrowserName;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.OSType;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.AbstractValueMap;
import com.testmile.trishanku.tpi.value.StringKeyValueMap;

public class DakshaTestContext implements TestContext {
	private String name;
	private String parentConfigSetuId = null;
	private TestSession testSession;
	private SetuOptionContainer setuOptions = new SetuOptionContainer();
	private StringKeyValueMap userOptions = new StringKeyValueMap();

	public DakshaTestContext(TestSession session, String name) {
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
	public void addSetuOption(SetuOption option, Object value) throws Exception {
		setuOptions.addObject(option, value);
	 }
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addUserOption(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addUserOption(String option, Object obj) throws Exception {
		userOptions.addObject(DakshaSingleton.INSTANCE.normalizeUserOption(option), obj);
	} 
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#addOption(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addOption(String option, Object obj) throws Exception {
		String normalizedOption = DakshaSingleton.INSTANCE.normalizeUserOption(option);
		System.out.println(normalizedOption);
		try {
			SetuOption sOption = SetuOption.valueOf(normalizedOption);
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
		this.addSetuOption(SetuOption.GUIAUTO_AUTOMATOR_NAME, GuiAutomatorName.SELENIUM.toString());
		return this;
	}	
	
	public TestContext appium(GuiAutomationContext context) throws Exception {
		this.addSetuOption(SetuOption.GUIAUTO_AUTOMATOR_NAME, GuiAutomatorName.APPIUM.toString());
		this.addSetuOption(SetuOption.GUIAUTO_CONTEXT, context);
		return this;
	}
	
	public TestContext chrome() throws Exception {
		this.addSetuOption(SetuOption.BROWSER_NAME, BrowserName.CHROME);
		return this;
	}	
	
	public TestContext firefox() throws Exception {
		this.addSetuOption(SetuOption.BROWSER_NAME, BrowserName.FIREFOX);
		return this;
	}	
	
	public TestContext headlessMode() throws Exception {
		this.addSetuOption(SetuOption.BROWSER_HEADLESS_MODE, true);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#targetPlatform(com.testmile.trishanku.tpi.enums.OSType)
	 */
	@Override
	public TestContext targetPlatform(OSType osType) throws Exception {
		addSetuOption(SetuOption.TESTRUN_TARGET_PLATFORM_NAME, osType.toString());
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#uuiAutoMaxWaitTime(int)
	 */
	@Override
	public TestContext guiAutoMaxWaitTime(int seconds) throws Exception {
		addSetuOption(SetuOption.GUIAUTO_MAX_WAIT, String.valueOf(seconds));
		return this;
	}
	
	public TestContext app(String path) throws Exception {
		addSetuOption(SetuOption.MOBILE_APP_FILE_PATH, path);
		return this;
	}
	
	public TestContext mobileDeviceName(String name) throws Exception {
		addSetuOption(SetuOption.MOBILE_DEVICE_NAME, name);
		return this;
	}
	
	public TestContext mobileDeviceUdid(String udid) throws Exception {
		addSetuOption(SetuOption.MOBILE_DEVICE_UDID, udid);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#build()
	 */
	@Override
	public DakshaTestConfig build() throws Exception {
		String configSetuId;
		System.out.println(parentConfigSetuId);
		if (this.parentConfigSetuId == null) {
			configSetuId = this.testSession.registerConfig(setuOptions.strItems(), userOptions.items());
		} else {
			configSetuId = this.testSession.registerConfig(this.parentConfigSetuId, setuOptions.strItems(), userOptions.items());
		}
		return new DefaultDakshaTestConfig(this.testSession, this.name, configSetuId);
	}
}

class SetuOptionContainer extends AbstractValueMap<SetuOption> {

	@Override
	protected String formatKeyAsStr(SetuOption key) {
		return key.toString();
	}

}

