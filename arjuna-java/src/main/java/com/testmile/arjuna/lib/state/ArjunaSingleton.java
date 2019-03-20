package com.testmile.arjuna.lib.state;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.ITestContext;

import com.testmile.arjuna.lib.batteries.console.Console;
import com.testmile.arjuna.lib.config.CLIConfiguration;
import com.testmile.arjuna.lib.config.DakshaTestContext;
import com.testmile.arjuna.lib.config.DakshaTestSession;
import com.testmile.arjuna.lib.enums.SetuOption;
import com.testmile.arjuna.lib.setu.requester.config.TestConfig;
import com.testmile.arjuna.lib.setu.requester.guiauto.GuiDriverExtendedConfig;
import com.testmile.arjuna.lib.setu.requester.guiauto.automator.DefaultGuiAutomator;
import com.testmile.arjuna.lib.testng.TestNGSuiteContext;
import com.testmile.arjuna.lib.testng.TestNGTestContext;
import com.testmile.arjuna.tpi.ddauto.DakshaDataSourceBuilder;
import com.testmile.arjuna.tpi.setu.requester.guiauto.GuiAutomator;
import com.testmile.arjuna.tpi.test.DakshaTestConfig;
import com.testmile.arjuna.tpi.test.TestContext;

public enum ArjunaSingleton {
	INSTANCE;
	private String rootDir = null;
	
	private DakshaTestSession session;
	private DakshaTestConfig centralConfig;
	private CLIConfiguration cliConfig = null;	
	
	private Logger logger = null;

	
	private Map<String, DakshaTestConfig> testContextConfigMap = new HashMap<String, DakshaTestConfig>();

	public DakshaTestConfig init(String rootDir) throws Exception {
		this.rootDir = rootDir;
		session = new DakshaTestSession();
		centralConfig = session.initSession(rootDir);
		cliConfig = new CLIConfiguration();
		
		// Finalize logger
		createLogger("arjuna", this.getCentralConfig().getLogDir() + File.separator + "arjuna-java.log");
		logger = Logger.getLogger("daksha");
		Console.init();
		
		return this.centralConfig;
	}
	
	public DakshaTestConfig getCentralConfig() throws Exception {
		return centralConfig;
	}
	
	public void registerTestContextConfig(DakshaTestConfig config) throws Exception {
		this.testContextConfigMap.put(config.getName().toLowerCase(), config);
	}
	
	public DakshaTestConfig getTestContextConfig(String name) throws Exception {
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
		return new DakshaTestContext(session, name);
	}
	
	public TestContext createTestNGSuiteContext(ITestContext testngContext) throws Exception {
		return new TestNGSuiteContext(session, testngContext);
	}
	
	public TestContext createTestNGTestContext(TestConfig parentConfig, ITestContext testngContext) throws Exception {
		return new TestNGTestContext(session, parentConfig, testngContext);
	}
	
	public DakshaTestConfig getTestConfig(ITestContext context) throws Exception {
		return getTestContextConfig(context.getName());
	}
	
	public String getRootDir() {
		return this.rootDir;
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
	
	public DakshaDataSourceBuilder createDataSourceBuilder() throws Exception {
		return new DakshaDataSourceBuilder(session);
	}
	
	public GuiAutomator createGuiAutomator(DakshaTestConfig config) throws Exception {
		return new DefaultGuiAutomator(config);
	}
	
	public GuiAutomator createGuiAutomator(DakshaTestConfig config, GuiDriverExtendedConfig extendedConfig) throws Exception {
		return new DefaultGuiAutomator(config, extendedConfig);
	}
	
	public static void createLogger(String loggerName, String logFilePath) {
		Logger.getLogger(loggerName).getLoggerRepository().resetConfiguration();
		
		ConsoleAppender console = new ConsoleAppender(); // create appender
		FileAppender fa = new FileAppender();

		String PATTERN = null;
		PATTERN = "(%F:%L)\t%m%n";
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(Level.INFO);
		console.activateOptions();
		Logger.getLogger(loggerName).addAppender(console);

		fa.setName("FileLogger");
		fa.setFile(logFilePath);
		fa.setLayout(new PatternLayout("[%-5p]\t%d{yyyy-MM-dd|HH:mm:ss}\t(%F:%L)\t%m%n"));
		fa.setThreshold(Level.DEBUG);
		fa.setAppend(false);
		fa.activateOptions();
		Logger.getLogger(loggerName).addAppender(fa);
	}

	public Logger getLogger() {
		if (logger == null) {
			createLogger("trishanku", "trishanku.log");
			logger = Logger.getLogger("trishanku");
		}
		return logger;
	}
}
