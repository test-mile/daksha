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
	private final String DEFAULT_CONTEXT_NAME = "default_context";
	private String rootDir = null;
	
	private TestSession session;
	private TestConfig centralConfig;
	private CliArgsConfig cliConfig = null;	
	
	private Logger logger = null;

	private Map<String, TestContext> testContextMap = new HashMap<String, TestContext>();

	public TestContext init(String rootDir) throws Exception {
		this.rootDir = rootDir;
		cliConfig = new CliArgsConfig();
		
		// Finalize logger
		createLogger("arjuna", rootDir + File.separator + "log" + File.separator + "arjuna-java.log");
		logger = Logger.getLogger("arjuna");
		Console.init();
		
		session = new DefaultTestSession();
		centralConfig = session.init(rootDir);
		
		TestContext context = this.createTestContext(DEFAULT_CONTEXT_NAME);
		this.testContextMap.put(DEFAULT_CONTEXT_NAME, context);
		return context;
	}
	
	public CliArgsConfig getCliArgsConfig() {
		return this.cliConfig;
	}
	
	public TestConfig getCentralConfig() throws Exception {
		return centralConfig;
	}
	
	public void registerTestContext(TestContext context) throws Exception {
		this.testContextMap.put(context.getName().toLowerCase(), context);
	}
	
	public TestContext getTestContext(String name) throws Exception {
		if (name == null) {
			throw new Exception("Context name was passed as null.");
		} else {
			try {
				return this.testContextMap.get(name.toLowerCase());
			} catch (Exception e) {
				throw new Exception("No context found with name: " + name);
			}
		}
	}
	
	public TestContext createTestContext(String name) throws Exception {
		return new DefaultTestContext(session, name);
	}
	
	public TestContext createTestNGSuiteContext(TestContext parentContext, ITestContext testngContext) throws Exception {
		TestContext context = new TestNGSuiteContext(session, parentContext, testngContext);
		registerTestContext(context);
		return context;
	}
	
	public TestContext createTestNGTestContext(TestContext parentContext, ITestContext testngContext) throws Exception {
		TestContext context =  new TestNGTestContext(session, parentContext, testngContext);
		registerTestContext(context);
		return context;
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
	
	public ArjunaOption normalizeArjunaOption(String option) {
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
//		if (logger == null) {
//			createLogger("trishanku", "trishanku.log");
//			logger = Logger.getLogger("trishanku");
//		}
		return logger;
	}

}
