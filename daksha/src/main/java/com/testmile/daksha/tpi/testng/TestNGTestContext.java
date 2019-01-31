package com.testmile.daksha.tpi.testng;

import org.testng.ITestContext;

import com.testmile.daksha.core.batteries.context.DakshaTestContext;
import com.testmile.daksha.tpi.TestContext;

public class TestNGTestContext extends DakshaTestContext{
	
	public TestNGTestContext(TestContext parentContext, ITestContext context) throws Exception {
		super(context.getName(), parentContext, context.getCurrentXmlTest().getAllParameters());
	}

}
