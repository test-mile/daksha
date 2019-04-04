package arjuna.lib.core.config;

import java.util.Map;

import arjuna.lib.core.value.AbstractValueMap;
import arjuna.lib.core.value.StringKeyValueMap;
import arjuna.lib.enums.BrowserName;
import arjuna.lib.enums.GuiAutomationContext;
import arjuna.lib.setu.core.requester.config.DefaultTestConfig;
import arjuna.lib.setu.guiauto.requester.automator.GuiAutomatorName;
import arjuna.lib.setu.testsession.requester.TestSession;
import arjuna.lib.state.ArjunaSingleton;
import arjuna.tpi.enums.ArjunaOption;
import arjuna.tpi.test.TestConfig;
import arjuna.tpi.test.TestContext;

public class DefaultTestContext implements TestContext {
	private String name;
	private String parentConfigSetuId = null;
	private TestSession testSession;
	private ArjunaOptionMap arjunaOptions = new ArjunaOptionMap();
	private StringKeyValueMap userOptions = new StringKeyValueMap();

	public DefaultTestContext(TestSession session, String name) {
		this.testSession = session;
		this.name = name;
	}
	
	protected void setParentConfig(TestConfig parentConfig) {
		this.parentConfigSetuId = parentConfig.getSetuId();
	}
	
	@Override
	public void addArjunaOption(ArjunaOption option, Object value) throws Exception {
		arjunaOptions.addObject(option, value);
	 }
	
	@Override
	public void addUserOption(String option, Object obj) throws Exception {
		userOptions.addObject(ArjunaSingleton.INSTANCE.normalizeUserOption(option), obj);
	} 

	@Override
	public void addOption(String option, Object obj) throws Exception {
		String normalizedOption = ArjunaSingleton.INSTANCE.normalizeUserOption(option);
		System.out.println(normalizedOption);
		try {
			ArjunaOption sOption = ArjunaOption.valueOf(normalizedOption);
			this.arjunaOptions.addObject(sOption, obj);
		} catch (Exception e) {
			userOptions.addObject(option, obj);
		}	
	} 

	@Override
	public void addOptions(Map<String,String> options) throws Exception {
		for(String option: options.keySet()) {
			this.addOption(option,  options.get(option));
		}
	}
	
	public TestContext selenium() throws Exception {
		this.addArjunaOption(ArjunaOption.GUIAUTO_AUTOMATOR_NAME, GuiAutomatorName.SELENIUM.toString());
		return this;
	}	
	
	public TestContext appium(GuiAutomationContext context) throws Exception {
		this.addArjunaOption(ArjunaOption.GUIAUTO_AUTOMATOR_NAME, GuiAutomatorName.APPIUM.toString());
		this.addArjunaOption(ArjunaOption.GUIAUTO_CONTEXT, context);
		return this;
	}
	
	public TestContext chrome() throws Exception {
		this.addArjunaOption(ArjunaOption.BROWSER_NAME, BrowserName.CHROME);
		return this;
	}	
	
	public TestContext firefox() throws Exception {
		this.addArjunaOption(ArjunaOption.BROWSER_NAME, BrowserName.FIREFOX);
		return this;
	}	
	
	public TestContext headlessMode() throws Exception {
		this.addArjunaOption(ArjunaOption.BROWSER_HEADLESS_MODE, true);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.daksha.tpi.test.TestContext#uuiAutoMaxWaitTime(int)
	 */
	@Override
	public TestContext guiAutoMaxWaitTime(int seconds) throws Exception {
		addArjunaOption(ArjunaOption.GUIAUTO_MAX_WAIT, String.valueOf(seconds));
		return this;
	}
	
	public TestContext app(String path) throws Exception {
		addArjunaOption(ArjunaOption.MOBILE_APP_FILE_PATH, path);
		return this;
	}
	
	public TestContext mobileDeviceName(String name) throws Exception {
		addArjunaOption(ArjunaOption.MOBILE_DEVICE_NAME, name);
		return this;
	}
	
	public TestContext mobileDeviceUdid(String udid) throws Exception {
		addArjunaOption(ArjunaOption.MOBILE_DEVICE_UDID, udid);
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
			configSetuId = this.testSession.registerConfig(arjunaOptions.strItems(), userOptions.items());
		} else {
			configSetuId = this.testSession.registerChildConfig(this.parentConfigSetuId, arjunaOptions.strItems(), userOptions.items());
		}
		return new DefaultTestConfig(this.testSession, this.name, configSetuId);
	}
}

class ArjunaOptionMap extends AbstractValueMap<ArjunaOption> {

	@Override
	protected String formatKeyAsStr(ArjunaOption key) {
		return key.toString();
	}

}

