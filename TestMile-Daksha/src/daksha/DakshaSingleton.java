package daksha;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.ITestContext;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigObject;

import daksha.core.batteries.config.CentralConfiguration;
import daksha.core.batteries.config.Configuration;
import daksha.core.batteries.context.CommonTestContext;
import daksha.core.batteries.context.DakshaTestContext;
import daksha.core.batteries.hocon.HoconConfigObjectReader;
import daksha.core.batteries.hocon.HoconFileReader;
import daksha.core.batteries.hocon.HoconReader;
import daksha.core.guiauto.GuiAutoSingleton;
import daksha.core.guiauto.namestore.GuiNameStore;
import daksha.tpi.CentralTestContext;
import daksha.tpi.TestContext;
import daksha.tpi.batteries.console.Console;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.sysauto.utils.SystemUtils;

public enum DakshaSingleton {
	INSTANCE;

	private String version = "1.0.6-b";
	private Logger logger = null;
	private boolean centralConfFrozen = false;
	private ConsoleAppender console = new ConsoleAppender(); // create appender
	private FileAppender fa = new FileAppender();
	private CommonTestContext centralTestContext = null;
	private Map<String, TestContext> contexts = new HashMap<String, TestContext>();
	private GuiNameStore uiRep = GuiNameStore.INSTANCE;
	private static String defString = "default";
	private String rootDir = null;
	 
	public CentralTestContext init(String rootDir) throws Exception {
		this.rootDir = rootDir;
		centralTestContext = new CommonTestContext(new CentralConfiguration(rootDir));
		updateFromProjectConf(centralTestContext);
		return centralTestContext;
	}
	
	private void updateFromProjectConf(TestContext centralContext) throws Exception {
		// Project's Daksha config file
		String dakshConfPath = centralContext.getValue(DakshaOption.PROJECT_CONF_FILE).asString();

		HoconReader reader = new HoconFileReader(dakshConfPath);
		reader.process();
		
		try{
			ConfigObject dakshOptObj = reader.getConfig().getObject("DakshaOptions");
			HoconReader dakshaOptReader = new HoconConfigObjectReader(dakshOptObj);
			dakshaOptReader.process();
			try{
				centralContext.addAll(dakshaOptReader.getProperties());
			} catch (Exception e){
				System.err.println("Fatal Error in processing of Arjuna options in central configuration file");
				System.err.println("Please check the following exception details and modify the configuration.");
				e.printStackTrace();
				SystemUtils.exit();
			}
		} catch (ConfigException e){
			System.out.println(e);
			// config may not be defined. It's ok. It's optional
		}
		
		try{
			ConfigObject userOptions = reader.getConfig().getObject("UserOptions");
			HoconReader userOptionsReader = new HoconConfigObjectReader(userOptions);
			userOptionsReader.process();
			centralContext.addAll(userOptionsReader.getProperties());
		} catch (ConfigException e){
			// userOptions may not be defined. It's ok. It's optional
		}		
	}
	
	public String getRootDir() {
		return rootDir;
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
