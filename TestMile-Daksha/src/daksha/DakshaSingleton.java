package daksha;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.ITestContext;

import daksha.core.batteries.config.BaseConfiguration;
import daksha.core.batteries.config.CentralConfiguration;
import daksha.core.batteries.config.Configuration;
import daksha.core.batteries.config.ContextConfiguration;
import daksha.core.batteries.container.ValueContainer;
import daksha.core.batteries.context.CommonTestContext;
import daksha.core.batteries.context.DakshaTestContext;
import daksha.core.guiauto.GuiAutoSingleton;
import daksha.core.guiauto.enums.OSType;
import daksha.core.guiauto.namestore.GuiNameStore;
import daksha.tpi.CentralTestContext;
import daksha.tpi.TestContext;
import daksha.tpi.batteries.console.Console;
import daksha.tpi.batteries.container.Value;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public enum DakshaSingleton {
	INSTANCE;

	private String version = "1.0.3-b";
	private Logger logger = null;
	private boolean centralConfFrozen = false;
	private ConsoleAppender console = new ConsoleAppender(); // create appender
	private FileAppender fa = new FileAppender();
	private CommonTestContext centralTestContext = null;
	private Map<String, TestContext> contexts = new HashMap<String, TestContext>();
	private GuiNameStore uiRep = GuiNameStore.INSTANCE;
	private static String defString = "default";
	 
	public CentralTestContext init(String rootDir) throws Exception {
		centralTestContext = new CommonTestContext(new CentralConfiguration(rootDir));
		return centralTestContext;
	}
	
	public void actOnFrozenCentralContext() throws Exception {
		this.centralConfFrozen = true;
		// Finalize logger
		configureLogger(Level.INFO, Level.DEBUG);
		logger = Logger.getLogger("daksha");
		Console.init();
		
		GuiAutoSingleton.INSTANCE.init();
		this.contexts.put(
				defString, 
				new DakshaTestContext(defString));
	}

	private void validateFrozenCentralConfig(String suffix) throws Exception {
		if (!centralConfFrozen) {
			throw new Exception(String.format("You must freeze central configuration before %s.", suffix));
		}		
	}
	
	public CommonTestContext getCentralContext() throws Exception {
		validateFrozenCentralConfig("pulling frozen central context");
		return centralTestContext;
	}
	
	public void registerContext(TestContext context) throws Exception {
		validateFrozenCentralConfig("registering custom test context");
		this.contexts.put(context.getName().toLowerCase(), context);
	}
	
	public TestContext getTestContext(String name) throws Exception {
		validateFrozenCentralConfig("getting context");
		return this.contexts.get(name.toLowerCase());
	}
	
	public TestContext getTestContext(ITestContext context) throws Exception {
		return getTestContext(context.getName());
	}
	
	public TestContext getDefaultTestContext() throws Exception {
		return getTestContext(defString);
	}
	
	public Configuration getTestContextConfig(String name) throws Exception {
		validateFrozenCentralConfig("getting context config");
		return this.getTestContext(name).getConfig();
	}
	
	public Configuration getTestContextConfig(ITestContext context) throws Exception {
		return this.getTestContextConfig(context.getName());
	}
	
	public Configuration getDefaultTestContextConfig() throws Exception {
		return getTestContextConfig(defString);
	}
	 
	public Logger getLogger() { 
		return logger; 
	}

	private void setConsoleLogger(Level level) {
		String PATTERN = null;
		PATTERN = "(%F:%L)\t%m%n";
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(level);
		console.activateOptions();
		Logger.getLogger("daksha").addAppender(console);
		//Console.setCentralLogLevel(level);
	}

	private void setFileLogger(Level level) throws Exception {
		fa.setName("FileLogger");
		fa.setFile(centralTestContext.getLogDir() + File.separator + "daksha.log");
		fa.setLayout(new PatternLayout("[%-5p]\t%d{yyyy-MM-dd|HH:mm:ss}\t(%F:%L)\t%m%n"));
		fa.setThreshold(level);
		fa.setAppend(false);
		fa.activateOptions();
		Logger.getLogger("daksha").addAppender(fa);
	}

	private void configureLogger(Level consoleLevel, Level fileLevel) throws Exception {
		Logger.getLogger("daksha").getLoggerRepository().resetConfiguration();
		this.setConsoleLogger(consoleLevel);
		this.setFileLogger(fileLevel);
	}
}
