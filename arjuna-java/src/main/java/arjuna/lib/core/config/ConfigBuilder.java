package arjuna.lib.core.config;

import java.util.Map;

import arjuna.lib.core.value.AbstractValueMap;
import arjuna.lib.enums.BrowserName;
import arjuna.lib.enums.GuiAutomationContext;
import arjuna.lib.setu.core.requester.config.DefaultTestConfig;
import arjuna.lib.setu.guiauto.requester.automator.GuiAutomatorName;
import arjuna.lib.setu.testsession.requester.TestSession;
import arjuna.lib.state.ArjunaSingleton;
import arjuna.tpi.enums.ArjunaOption;
import arjuna.tpi.test.TestConfig;

public class ConfigBuilder {
	public static final String DEFAULT_CONF_NAME = "default_config";
	private TestSession testSession;
	private ConfigContainer configContainer = new ConfigContainer();
	private Map<String, TestConfig> configMap;
	private String sourceConfigSetuId;

	public ConfigBuilder(TestSession session, Map<String, TestConfig> configMap) {
		this.testSession = session;
		this.configMap = configMap;
	}
	
	public ConfigBuilder arjunaOption(ArjunaOption option, Object value) throws Exception {
		this.configContainer.setArjunaOption(option, value);
		return this;
	 }

	public ConfigBuilder userOption(String option, Object obj) throws Exception {
		this.configContainer.setUserOption(ArjunaSingleton.INSTANCE.normalizeUserOption(option), obj);
		return this;
	} 
	
	public ConfigBuilder options(Map<String,String> optionMap) throws Exception {
		this.configContainer.setOptions(optionMap);
		return this;
	}
	
	public ConfigBuilder sourceConfig(TestConfig conf) {
		this.sourceConfigSetuId = conf.getSetuId();
		return this;
	}
	
	public ConfigBuilder selenium() throws Exception {
		this.arjunaOption(ArjunaOption.GUIAUTO_AUTOMATOR_NAME, GuiAutomatorName.SELENIUM.toString());
		return this;
	}	
	
	public ConfigBuilder appium(GuiAutomationContext context) throws Exception {
		this.arjunaOption(ArjunaOption.GUIAUTO_AUTOMATOR_NAME, GuiAutomatorName.APPIUM.toString());
		this.arjunaOption(ArjunaOption.GUIAUTO_CONTEXT, context);
		return this;
	}
	
	public ConfigBuilder chrome() throws Exception {
		this.arjunaOption(ArjunaOption.BROWSER_NAME, BrowserName.CHROME);
		return this;
	}	
	
	public ConfigBuilder firefox() throws Exception {
		this.arjunaOption(ArjunaOption.BROWSER_NAME, BrowserName.FIREFOX);
		return this;
	}	
	
	public ConfigBuilder headlessMode() throws Exception {
		this.arjunaOption(ArjunaOption.BROWSER_HEADLESS_MODE, true);
		return this;
	}

	public ConfigBuilder guiAutoMaxWaitTime(int seconds) throws Exception {
		arjunaOption(ArjunaOption.GUIAUTO_MAX_WAIT, String.valueOf(seconds));
		return this;
	}
	
	public ConfigBuilder app(String path) throws Exception {
		arjunaOption(ArjunaOption.MOBILE_APP_FILE_PATH, path);
		return this;
	}
	
	public ConfigBuilder mobileDeviceName(String name) throws Exception {
		arjunaOption(ArjunaOption.MOBILE_DEVICE_NAME, name);
		return this;
	}
	
	public ConfigBuilder mobileDeviceUdid(String udid) throws Exception {
		arjunaOption(ArjunaOption.MOBILE_DEVICE_UDID, udid);
		return this;
	}

	public void build(String configName) throws Exception {
		String configSetuId;
		if (this.sourceConfigSetuId == null) {
			configSetuId = this.testSession.registerConfig(
					this.configContainer.getArjunaOptions().strItems(), 
					this.configContainer.getUserOptions().items()
			);
		} else {
			configSetuId = this.testSession.registerChildConfig(
					this.sourceConfigSetuId, 
					this.configContainer.getArjunaOptions().strItems(), 
					this.configContainer.getUserOptions().items()
			);
		}
		TestConfig config = new DefaultTestConfig(this.testSession, configName, configSetuId);
		this.configMap.put(config.getName().toLowerCase(), config);
	}
	
	public void build() throws Exception{
		this.build(DEFAULT_CONF_NAME);
	}
}


class ArjunaOptionMap extends AbstractValueMap<ArjunaOption> {

	@Override
	protected String formatKeyAsStr(ArjunaOption key) {
		return key.toString();
	}

}