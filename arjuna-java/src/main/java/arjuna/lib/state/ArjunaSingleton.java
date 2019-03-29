package arjuna.lib.state;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.ITestContext;

import arjuna.lib.batteries.console.Console;
import arjuna.lib.core.config.CliArgsConfig;
import arjuna.lib.core.config.DefaultTestContext;
import arjuna.lib.setu.guiauto.requester.automator.DefaultGuiAutomator;
import arjuna.lib.setu.testsession.requester.DefaultTestSession;
import arjuna.lib.setu.testsession.requester.TestSession;
import arjuna.lib.testng.TestNGSuiteContext;
import arjuna.lib.testng.TestNGTestContext;
import arjuna.tpi.ddauto.DataSourceBuilder;
import arjuna.tpi.enums.ArjunaOption;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.GuiDriverExtendedConfig;
import arjuna.tpi.test.TestConfig;
import arjuna.tpi.test.TestContext;

public enum ArjunaSingleton {
	INSTANCE;
	private String rootDir = null;
	
	private TestSession session;
	private TestConfig centralConfig;
	private CliArgsConfig cliConfig = null;	
	
	private Logger logger = null;

	
	private Map<String, TestConfig> testContextConfigMap = new HashMap<String, TestConfig>();

	public TestConfig init(String rootDir) throws Exception {
		this.rootDir = rootDir;
		session = new DefaultTestSession();
		centralConfig = session.init(rootDir);
		cliConfig = new CliArgsConfig();
		
		// Finalize logger
		createLogger("arjuna", this.getCentralConfig().getLogDir() + File.separator + "arjuna-java.log");
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
	
	private CliArgsConfig getCliConfig() {
		return this.cliConfig;
	}
	
	public String normalizeUserOption(String option) {
		return option.trim().toUpperCase().replace(".", "_");
	}
	
	public ArjunaOption normalizeSetuOption(String option) {
		return ArjunaOption.valueOf(normalizeUserOption(option));
	}
	
	public DataSourceBuilder createDataSourceBuilder() throws Exception {
		return new DataSourceBuilder(session);
	}
	
	public GuiAutomator createGuiAutomator() throws Exception {
		return new DefaultGuiAutomator(this.centralConfig);
	}
	
	public GuiAutomator createGuiAutomator(TestConfig config) throws Exception {
		return new DefaultGuiAutomator(config);
	}
	
	public GuiAutomator createGuiAutomator(TestConfig config, GuiDriverExtendedConfig extendedConfig) throws Exception {
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
