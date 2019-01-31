package com.testmile.daksha.tpi.testng;

import org.testng.ITestContext;

import com.testmile.daksha.core.batteries.context.DakshaTestContext;

public class TestNGSuiteContext extends DakshaTestContext{
	
	public TestNGSuiteContext(ITestContext context) throws Exception {
		super(context.getSuite().getName(), context.getSuite().getXmlSuite().getAllParameters());
	}

}
