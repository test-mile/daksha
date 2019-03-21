package com.testmile.arjuna.tpi;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import com.testmile.arjuna.lib.state.ArjunaSingleton;
import com.testmile.arjuna.tpi.ddauto.DataSourceBuilder;
import com.testmile.arjuna.tpi.guiauto.GuiAutomator;
import com.testmile.arjuna.tpi.guiauto.GuiDriverExtendedConfig;
import com.testmile.arjuna.tpi.test.TestConfig;
import com.testmile.arjuna.tpi.test.TestContext;

public class Arjuna {
	private static ArjunaSingleton internal = ArjunaSingleton.INSTANCE;
	public static final String DEF_CONF_NAME = "central";
	
	public static TestConfig init(String rootDir) throws Exception{
		return internal.init(rootDir);
	}
	
	public static TestConfig init() throws Exception {
		return init(System.getProperty("user.dir"));
	}
	
	public static TestConfig getCentralConfig() throws Exception {
		return internal.getCentralConfig();
	}
	
	public static void registerTestContextConfig(TestConfig config) throws Exception {
		internal.registerTestContextConfig(config);
	}
	
	public static TestConfig getTestContextConfig(String name) throws Exception {
		return internal.getTestContextConfig(name);		
	}
	
	public static TestContext createTestContext(String name) throws Exception {
		return internal.createTestContext(name);
	}
	
	public static TestContext createTestNGSuiteContext(ITestContext testngContext) throws Exception {
		return internal.createTestNGSuiteContext(testngContext);
	}
	
	public static TestContext createTestNGTestContext(TestConfig parentConfig, ITestContext testngContext) throws Exception {
		return internal.createTestNGTestContext(parentConfig, testngContext);
	}
	
	public static TestConfig getTestConfig(ITestContext context) throws Exception {
		return internal.getTestConfig(context);
	}
	
	public String getRootDir() {
		return internal.getRootDir();
	}
	
	public Logger getLogger() { 
		return internal.getLogger();
	}
	
	public static DataSourceBuilder createDataSourceBuilder() throws Exception {
		return internal.createDataSourceBuilder();
	}
	
	public static GuiAutomator createGuiAutomator(TestConfig config) throws Exception {
		return internal.createGuiAutomator(config);
	}

	public static GuiAutomator createGuiAutomator(TestConfig config, GuiDriverExtendedConfig extendedConfig) throws Exception {
		return internal.createGuiAutomator(config, extendedConfig);
	}
}
