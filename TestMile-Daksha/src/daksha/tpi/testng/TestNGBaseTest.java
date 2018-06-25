package daksha.tpi.testng;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

import daksha.Daksha;

public class TestNGBaseTest {
	private ThreadLocal<String> testContextName = new ThreadLocal<String>();
	
	@BeforeSuite
	public void initDaksha() throws Exception {
		String rootDir = this.getRootDir();
		if (rootDir == null) {
			Daksha.init();
		} else {
			Daksha.init(rootDir);
		}
		this.setCentralOptions();
		Daksha.freezeCentralConfig();
	}
	
	protected void setCentralOptions() throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected String getRootDir() throws Exception {
		// Proceed with default if null;
		return null;
	}
	
	@BeforeSuite
	public void initContext(ITestContext testContext) throws Exception {
		if (testContext.getName().toLowerCase().equals("default test")) {
			this.testContextName.set("default");
		} else {
			this.testContextName.set(testContext.getName());
			Daksha.registerContext(new TestNGContext(testContext));
		}
	}
	
	protected String getTestContextName() {
		return this.testContextName.get();
	}

}
