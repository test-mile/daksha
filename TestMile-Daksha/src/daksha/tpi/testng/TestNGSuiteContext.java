package daksha.tpi.testng;

import org.testng.ITestContext;

import daksha.core.batteries.context.DakshaTestContext;

public class TestNGSuiteContext extends DakshaTestContext{
	
	public TestNGSuiteContext(ITestContext context) throws Exception {
		super(context.getSuite().getName(), context.getSuite().getXmlSuite().getAllParameters());
	}

}
