package daksha;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.ITestContext;

import daksha.core.batteries.config.CentralConfiguration;
import daksha.core.batteries.config.Configuration;
import daksha.core.batteries.config.ContextConfiguration;
import daksha.core.batteries.config.TestContext;
import daksha.core.leaping.enums.OSType;
import daksha.core.leaping.loader.PageDefRepository;
import daksha.tpi.enums.DakshaOption;

public enum DakshaSingleton {
	INSTANCE;

	private Logger logger = null;
	private boolean centralConfFrozen = false;
	private ConsoleAppender console = new ConsoleAppender(); // create appender
	private FileAppender fa = new FileAppender();
	private CentralConfiguration centralConf = null;
	private Map<String, TestContext> contexts = new HashMap<String, TestContext>();
	private PageDefRepository pageRep = PageDefRepository.INSTANCE;
	private static String defString = "default";
	 
	public void init(String rootDir) throws Exception {
		this.centralConf = new CentralConfiguration(rootDir);
		LeapingSingleton.INSTANCE.init();
	}
	
	public void freezeCentralConfig() throws Exception {
		this.centralConfFrozen = true;
		this.centralConf.freeze();
		this.registerContext(new TestContext(defString, new HashMap<String,String>()));
	}
	
	private void validateNotFrozenCentralConfig() throws Exception {
		if (centralConfFrozen) {
			throw new Exception("You can not make central configuration changes after freezing it.");
		}		
	}
	
	public void setOSType(OSType osType) throws Exception {
		validateNotFrozenCentralConfig();
		this.centralConf.add(DakshaOption.OSTYPE, osType.toString());
	}

	private void validateFrozenCentralConfig(String suffix) throws Exception {
		if (!centralConfFrozen) {
			throw new Exception(String.format("You must freeze central configuration before %s.", suffix));
		}		
	}
	
	public void registerContext(TestContext context) throws Exception {
		validateFrozenCentralConfig("registering context");
		ContextConfiguration conf = new ContextConfiguration(this.centralConf, context.getAsMap());
		context.setOptions(conf);
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

	private void setConsoleLogger(Level level, String logName) {
		// configure the appender
		// String PATTERN = "[%-5p]\t%l\t%m%n";
		String PATTERN = null;
		PATTERN = "(%F:%L)\t%m%n";
		// if (Unitee.isInternalBuild()){
		// PATTERN = "(%F:%L)\t%m%n";
		// //"[%-5p]\t%m%n";//"[%-5p]\t%d{yyyy-MM-dd|HH:mm:ss}\t(%F:%L)\t%m%n";//
		// } else {
		// PATTERN = "%m%n";
		// }
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(level);
		console.activateOptions();
		Logger.getLogger(logName).addAppender(console);
		//Console.setCentralLogLevel(level);
	}

	private void setFileLogger(Level level, String logName, String path) {
		fa.setName("FileLogger-" + logName);
		fa.setFile(path + "/" + logName + ".log");
		// if (Unitee.isInternalBuild()){
		fa.setLayout(new PatternLayout("[%-5p]\t%d{yyyy-MM-dd|HH:mm:ss}\t(%F:%L)\t%m%n"));
		// } else {
		// fa.setLayout(new PatternLayout("[%-5p]\t%d{yyyy-MM-dd|HH:mm:ss}
		// %m%n"));
		// }
		fa.setThreshold(level);
		fa.setAppend(false);
		fa.activateOptions();
		Logger.getLogger(logName).addAppender(fa);
	}

	public void configure(Level consoleLevel, Level fileLevel, String logName, String logPath) {
		Logger.getLogger(logName);
		Logger.getLogger(logName).getLoggerRepository().resetConfiguration();
		this.setConsoleLogger(consoleLevel, logName);
		this.setFileLogger(fileLevel, logName, logPath);
	}

	public void changeLogLevel(String name, String level) {
		LogManager.getLogger(name).setLevel(Level.toLevel(level));
	}

}
