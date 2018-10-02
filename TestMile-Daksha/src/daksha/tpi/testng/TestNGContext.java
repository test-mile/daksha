package daksha.tpi.testng;

import org.testng.ITestContext;

import daksha.core.batteries.config.DakshaTestContext;

public class TestNGContext extends DakshaTestContext{
	
	public TestNGContext(ITestContext context) throws Exception {
		super(context.getName(), context.getCurrentXmlTest().getAllParameters());
	}

}
