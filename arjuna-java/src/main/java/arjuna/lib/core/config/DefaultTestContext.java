package arjuna.lib.core.config;

import java.util.HashMap;
import java.util.Map;

import arjuna.lib.setu.testsession.requester.TestSession;
import arjuna.tpi.Arjuna;
import arjuna.tpi.test.TestConfig;
import arjuna.tpi.test.TestContext;

public class DefaultTestContext implements TestContext {
	private TestSession testSession;
	private String name;
	private Map<String, TestConfig> configMap = new HashMap<String, TestConfig>();

	public DefaultTestContext(TestSession session, String name) throws Exception {
		this.testSession = session;
		this.name = name;
		ConfigBuilder builder = this.ConfigBuilder();
		builder.sourceConfig(Arjuna.getCentralConfig());
		builder.build(ConfigBuilder.DEFAULT_CONF_NAME);
	}
	
	public DefaultTestContext(TestSession session, String name, TestContext parentContext) {
		this.testSession = session;
		this.name = name;
		this.configMap = parentContext.cloneConfigMap();
	}

	@Override
	public ConfigBuilder ConfigBuilder() {
		return new ConfigBuilder(this.testSession, this.configMap);
	}

	@Override
	public TestConfig getConfig() {
		return this.configMap.get(ConfigBuilder.DEFAULT_CONF_NAME);
	}

	@Override
	public TestConfig getConfig(String name) throws Exception {
		if (name == null) {
			throw new Exception("Config name was passed as null.");
		} else {
			try {
				return this.configMap.get(name.toLowerCase());
			} catch (Exception e) {
				throw new Exception("No context config found with name: " + name);
			}
		}
	}

	@Override
	public String getName() {
		return this.name;
	}
	

	protected void updateOptions(Map<String,String> optionMap) throws Exception {
		for (String confName: this.configMap.keySet()) {
			ConfigBuilder builder = this.ConfigBuilder();
			builder.sourceConfig(this.configMap.get(confName));
			builder.options(optionMap);
			builder.build(confName);
		}
	}

	@Override
	public Map<String, TestConfig> cloneConfigMap() {
		Map<String,TestConfig> outMap = new HashMap<String, TestConfig>();
		outMap.putAll(this.configMap);
		return outMap;
	}
}



