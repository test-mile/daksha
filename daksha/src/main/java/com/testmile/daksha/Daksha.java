package com.testmile.daksha;

import org.apache.log4j.Logger;
import org.testng.ITestContext;

import com.testmile.daksha.core.config.ConfigurationBuilder;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.test.TestConfig;

public class Daksha {
	private static DakshaSingleton internal = DakshaSingleton.INSTANCE;
	public static final String DEF_CONF_NAME = "central";
	
	public static TestConfig init(String rootDir) throws Exception{
		return internal.init(rootDir);
	}
	
	public static TestConfig init() throws Exception {
		return init(System.getProperty("user.dir"));
	}
	
	public TestConfig getCentralConfig() throws Exception {
		return internal.getCentralConfig();
	}
	
	public void registerTestContextConfig(TestConfig config) throws Exception {
		internal.registerTestContextConfig(config);
	}
	
	public TestConfig getTestContextConfig(String name) throws Exception {
		return internal.getTestContextConfig(name);		
	}
	
	public ConfigurationBuilder createConfigBuilder(String name) throws Exception {
		return internal.createConfigBuilder(name);
	}
	
	public TestConfig getTestConfig(ITestContext context) throws Exception {
		return internal.getTestConfig(context);
	}
	
	public String getRootDir() {
		return internal.getRootDir();
	}
	
	public Logger getLogger() { 
		return internal.getLogger();
	}

}
