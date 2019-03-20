package com.testmile.arjuna;

import org.apache.log4j.Logger;
import org.testng.ITestContext;

import com.testmile.arjuna.lib.setu.requester.config.TestConfig;
import com.testmile.arjuna.lib.setu.requester.guiauto.GuiDriverExtendedConfig;
import com.testmile.arjuna.lib.state.ArjunaSingleton;
import com.testmile.arjuna.tpi.ddauto.DakshaDataSourceBuilder;
import com.testmile.arjuna.tpi.setu.requester.guiauto.GuiAutomator;
import com.testmile.arjuna.tpi.test.DakshaTestConfig;
import com.testmile.arjuna.tpi.test.TestContext;

public class Arjuna {
	private static ArjunaSingleton internal = ArjunaSingleton.INSTANCE;
	public static final String DEF_CONF_NAME = "central";
	
	public static DakshaTestConfig init(String rootDir) throws Exception{
		return internal.init(rootDir);
	}
	
	public static DakshaTestConfig init() throws Exception {
		return init(System.getProperty("user.dir"));
	}
	
	public static DakshaTestConfig getCentralConfig() throws Exception {
		return internal.getCentralConfig();
	}
	
	public static void registerTestContextConfig(DakshaTestConfig config) throws Exception {
		internal.registerTestContextConfig(config);
	}
	
	public static DakshaTestConfig getTestContextConfig(String name) throws Exception {
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
	
	public static DakshaTestConfig getTestConfig(ITestContext context) throws Exception {
		return internal.getTestConfig(context);
	}
	
	public String getRootDir() {
		return internal.getRootDir();
	}
	
	public Logger getLogger() { 
		return internal.getLogger();
	}
	
	public static DakshaDataSourceBuilder createDataSourceBuilder() throws Exception {
		return internal.createDataSourceBuilder();
	}
	
	public static GuiAutomator createGuiAutomator(DakshaTestConfig config) throws Exception {
		return internal.createGuiAutomator(config);
	}

	public static GuiAutomator createGuiAutomator(DakshaTestConfig config, GuiDriverExtendedConfig extendedConfig) throws Exception {
		return internal.createGuiAutomator(config, extendedConfig);
	}
}
