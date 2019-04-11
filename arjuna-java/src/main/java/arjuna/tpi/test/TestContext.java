package arjuna.tpi.test;

import java.util.Map;

import arjuna.lib.core.config.ConfigBuilder;

public interface TestContext {
	
	ConfigBuilder ConfigBuilder();

	TestConfig getConfig();
	
	TestConfig getConfig(String name) throws Exception;
	
	String getName();

	Map<String, TestConfig> cloneConfigMap();

}