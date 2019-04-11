package arjuna.tpi;

import org.apache.log4j.Logger;
import org.testng.ITestContext;

import arjuna.lib.state.ArjunaSingleton;
import arjuna.tpi.ddauto.DataSourceBuilder;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.GuiDriverExtendedConfig;
import arjuna.tpi.test.TestConfig;
import arjuna.tpi.test.TestContext;

public class Arjuna {
	private static ArjunaSingleton internal = ArjunaSingleton.INSTANCE;
	
	public static TestContext init(String rootDir) throws Exception{
		return internal.init(rootDir);
	}
	
	public static TestContext init() throws Exception {
		return init(System.getProperty("user.dir"));
	}
	
	public static TestConfig getCentralConfig() throws Exception {
		return internal.getCentralConfig();
	}
	
	public static TestContext getTestContext(String name) throws Exception{
		return internal.getTestContext(name);
	}
	
	public static void registerTestContext(TestContext context) throws Exception {
		internal.registerTestContext(context);
	}
	
	public static TestContext createTestContext(String name) throws Exception {
		return internal.createTestContext(name);
	}
	
	public static TestContext createTestNGSuiteContext(TestContext parentContext, ITestContext testngContext) throws Exception {
		return internal.createTestNGSuiteContext(parentContext, testngContext);
	}
	
	public static TestContext createTestNGTestContext(TestContext parentContext, ITestContext testngContext) throws Exception {
		return internal.createTestNGTestContext(parentContext, testngContext);
	}
	
	public String getRootDir() {
		return internal.getRootDir();
	}
	
	public static Logger getLogger() { 
		return internal.getLogger();
	}
	
	public static DataSourceBuilder createDataSourceBuilder() throws Exception {
		return internal.createDataSourceBuilder();
	}
	
	public static GuiAutomator createGuiAutomator(TestConfig config) throws Exception {
		return internal.createGuiAutomator(config);
	}
	
	public static GuiAutomator createGuiAutomator() throws Exception {
		return internal.createGuiAutomator();
	}

	public static GuiAutomator createGuiAutomator(TestConfig config, GuiDriverExtendedConfig extendedConfig) throws Exception {
		return internal.createGuiAutomator(config, extendedConfig);
	}

}
