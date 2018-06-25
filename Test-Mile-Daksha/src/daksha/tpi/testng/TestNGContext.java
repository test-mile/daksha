package daksha.tpi.testng;

import org.testng.ITestContext;

import daksha.core.batteries.config.TestContext;

public class TestNGContext extends TestContext{
	
	public TestNGContext(ITestContext context) {
		super(context.getName(), context.getCurrentXmlTest().getAllParameters());
	}

}
