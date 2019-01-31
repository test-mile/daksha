package com.testmile.daksha.tpi.testng;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.testmile.daksha.Daksha;
import com.testmile.daksha.tpi.TestContext;

public class TestNGBaseTest {
	private static boolean onceSetupPerExecutionDone = false;
	private static boolean onceTearDownPerExecutionDone = false;
	private ThreadLocal<String> testContextName = new ThreadLocal<String>();
	
	protected void tweakCentralContext(TestContext centralContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void setUpOnce(TestContext centralContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownOnce(TestContext centralContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tweakSuiteContext(TestContext suiteContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void setUpSuite(TestContext suiteContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownSuite(TestContext suiteContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	@BeforeSuite
	public void initDaksha(ITestContext context) throws Exception {
		if (!onceSetupPerExecutionDone) {
			String rootDir = this.getRootDir();
			TestContext centralContext;
			if (rootDir == null) {
				centralContext = Daksha.init();
			} else {
				centralContext = Daksha.init(rootDir);
			}
			this.tweakCentralContext(centralContext);
			centralContext.freeze();
			this.setUpOnce(centralContext);
			onceSetupPerExecutionDone = true;
		}
		
		TestContext suiteContext = new TestNGSuiteContext(context);
		this.tweakSuiteContext(suiteContext);
		Daksha.registerContext(suiteContext);
		testContextName.set(suiteContext.getName());
		
		this.setUpSuite(suiteContext);
	}
	
	@AfterSuite
	public void cleanUpDaksha(ITestContext context) throws Exception {		
		this.tearDownSuite(Daksha.getTestContext(context.getSuite().getXmlSuite().getName()));
		// To Do: How can it be determined that it's the last suite?
		// When Daksha on line TestNG would support suite of suites, calling the following after last suite's tear down 
		// would be critical.
		this.tearDownOnce(Daksha.getCentralContext());
	}
	
	protected void tweakTestContext(TestContext centralContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void setUpTest(TestContext testContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownTest(TestContext testContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	@BeforeTest
	public void initContext(ITestContext testContext) throws Exception {
		TestContext parentContext = Daksha.getTestContext(testContext.getSuite().getXmlSuite().getName());
		TestContext testNGContext = new TestNGTestContext(parentContext, testContext);		
		this.tweakTestContext(testNGContext);
		Daksha.registerContext(testNGContext);
		this.setUpTest(testNGContext);
		testContextName.set(testNGContext.getName());
	}
	
	@AfterTest
	public void endContext(ITestContext context) throws Exception {
		this.tearDownTest(Daksha.getTestContext(context.getName()));
	}
	
	protected void setUpClass(TestContext testContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownClass(TestContext testContext) throws Exception {
	}
	
	@BeforeClass
	public void initClass(ITestContext context) throws Exception {
		this.testContextName.set(context.getName());
		this.setUpClass(Daksha.getTestContext(context.getName()));
	}
	
	@AfterClass
	public void endClass(ITestContext context) throws Exception {
		this.tearDownClass(Daksha.getTestContext(context.getName()));
	}
	
	protected void setUpMethod(TestContext testContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownMethod(TestContext testContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	@BeforeMethod
	public void initMethod(ITestContext context) throws Exception {
		this.testContextName.set(context.getName());
		this.setUpMethod(Daksha.getTestContext(context.getName()));
	}
	
	@AfterMethod
	public void endMethod(ITestContext context) throws Exception {
		this.tearDownMethod(Daksha.getTestContext(context.getName()));
	}
	
	protected String getRootDir() throws Exception {
		// Proceed with default if null;
		return null;
	}
	
	private String getTestContextName() throws Exception {
		String name = this.testContextName.get();
		if (name == null) {
			String msg = String.format("No context was registered for thread %s", Thread.currentThread().getName());
			throw new Exception(msg);
		} else {
			return name;
		}
	}
	
	protected TestContext getContext() throws Exception {
		return Daksha.getTestContext(this.getTestContextName());
	}

}
