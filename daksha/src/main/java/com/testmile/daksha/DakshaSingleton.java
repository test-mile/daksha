package com.testmile.daksha;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;

import com.testmile.daksha.core.config.CLIConfiguration;
import com.testmile.daksha.core.config.DefaultTestContext;
import com.testmile.daksha.core.testng.TestNGSuiteContext;
import com.testmile.daksha.core.testng.TestNGTestContext;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.daksha.tpi.test.TestContext;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.setu.requester.databroker.DataSourceBuilder;
import com.testmile.setu.requester.testsession.DefaultTestSession;
import com.testmile.trishanku.Trishanku;
import com.testmile.trishanku.tpi.batteries.console.Console;
import com.testmile.trishanku.tpi.enums.SetuOption;

public enum DakshaSingleton {
	INSTANCE;
	private String rootDir = null;
	
	private TestSession session;
	private TestConfig centralConfig;
	private CLIConfiguration cliConfig = null;	
	
	private Logger logger = null;

	
	private Map<String, TestConfig> testContextConfigMap = new HashMap<String, TestConfig>();

	public TestConfig init(String rootDir) throws Exception {
		rootDir = rootDir;
		session = new DefaultTestSession();
		centralConfig = session.init(rootDir);
		cliConfig = new CLIConfiguration();
		
		// Finalize logger
		Trishanku.createLogger("daksha", this.getCentralConfig().getLogDir() + File.separator + "daksha.log");
		logger = Logger.getLogger("daksha");
		Console.init();
		
		return this.centralConfig;
	}
	
	public TestConfig getCentralConfig() throws Exception {
		return centralConfig;
	}
	
	public void registerTestContextConfig(TestConfig config) throws Exception {
		this.testContextConfigMap.put(config.getName().toLowerCase(), config);
	}
	
	public TestConfig getTestContextConfig(String name) throws Exception {
		if (name == null) {
			throw new Exception("Config name was passed as null.");
		} else {
			try {
				return this.testContextConfigMap.get(name.toLowerCase());
			} catch (Exception e) {
				throw new Exception("No context config found with name: " + name);
			}
		}
		
	}
	
	public TestContext createTestContext(String name) throws Exception {
		return new DefaultTestContext(session, name);
	}
	
	public TestContext createTestNGSuiteContext(ITestContext testngContext) throws Exception {
		return new TestNGSuiteContext(session, testngContext);
	}
	
	public TestContext createTestNGTestContext(TestConfig parentConfig, ITestContext testngContext) throws Exception {
		return new TestNGTestContext(session, parentConfig, testngContext);
	}
	
	public TestConfig getTestConfig(ITestContext context) throws Exception {
		return getTestContextConfig(context.getName());
	}
	
	public String getRootDir() {
		return this.rootDir;
	}
	
	public Logger getLogger() { 
		return logger; 
	}
	
	private CLIConfiguration getCliConfig() {
		return this.cliConfig;
	}
	
	public String normalizeUserOption(String option) {
		return option.trim().toUpperCase().replace(".", "_");
	}
	
	public SetuOption normalizeSetuOption(String option) {
		return SetuOption.valueOf(normalizeUserOption(option));
	}
	
	public DataSourceBuilder createDataSourceBuilder() throws Exception {
		return new DataSourceBuilder(session);
	}
}
