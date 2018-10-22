package daksha.tpi.testng;

import org.testng.ITestContext;

import daksha.core.batteries.context.DakshaTestContext;
import daksha.tpi.TestContext;

public class TestNGTestContext extends DakshaTestContext{
	
	public TestNGTestContext(TestContext parentContext, ITestContext context) throws Exception {
		super(context.getName(), parentContext, context.getCurrentXmlTest().getAllParameters());
	}

}
