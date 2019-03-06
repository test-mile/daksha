/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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
import com.testmile.daksha.tpi.test.DakshaTestConfig;
import com.testmile.daksha.tpi.test.TestContext;
import com.testmile.setu.requester.config.TestConfig;

public class TestNGBaseTest {
	private static boolean onceSetupPerExecutionDone = false;
	private static boolean onceTearDownPerExecutionDone = false;
	private ThreadLocal<String> testConfigName = new ThreadLocal<String>();
	
	protected void setUpOnce(TestConfig centralConfig) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownOnce(TestConfig suiteConfig) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tweakSuiteContext(TestContext suiteContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void setUpSuite(TestConfig suiteContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownSuite(TestConfig suiteConfig) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	@BeforeSuite
	public void initDaksha(ITestContext context) throws Exception {
		if (!onceSetupPerExecutionDone) {
			String rootDir = this.getRootDir();
			TestConfig centralConfig;
			if (rootDir == null) {
				centralConfig = Daksha.init();
			} else {
				centralConfig = Daksha.init(rootDir);
			}
			this.setUpOnce(centralConfig);
			onceSetupPerExecutionDone = true;
		}
		
		TestContext suiteContext = Daksha.createTestNGSuiteContext(context);
		this.tweakSuiteContext(suiteContext);
		DakshaTestConfig suiteConfig = suiteContext.build();
		Daksha.registerTestContextConfig(suiteConfig);
		this.setUpSuite(suiteConfig);
		testConfigName.set(suiteConfig.getName());
	}
	
	@AfterSuite
	public void cleanUpDaksha(ITestContext context) throws Exception {		
		this.tearDownSuite(Daksha.getTestContextConfig(context.getSuite().getXmlSuite().getName()));
		// To Do: How can it be determined that it's the last suite?
		// When Daksha on line TestNG would support suite of suites, calling the following after last suite's tear down 
		// would be critical.
		this.tearDownOnce(Daksha.getCentralConfig());
	}
	
	protected void tweakTestContext(TestContext centralContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void setUpTest(TestConfig testContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownTest(TestConfig testContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	@BeforeTest
	public void initContext(ITestContext testContext) throws Exception {
		DakshaTestConfig parentConfig = Daksha.getTestContextConfig(testContext.getSuite().getXmlSuite().getName());
		TestContext testNGContext = Daksha.createTestNGTestContext(parentConfig, testContext);		
		this.tweakTestContext(testNGContext);
		DakshaTestConfig contextConfig = testNGContext.build();
		Daksha.registerTestContextConfig(contextConfig);
		this.setUpTest(contextConfig);
		testConfigName.set(contextConfig.getName());
	}
	
	@AfterTest
	public void endContext(ITestContext context) throws Exception {
		this.tearDownTest(Daksha.getTestContextConfig(context.getName()));
	}
	
	protected void setUpClass(TestConfig testConfig) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownClass(TestConfig testConfig) throws Exception {
	}
	
	@BeforeClass
	public void initClass(ITestContext context) throws Exception {
		this.testConfigName.set(context.getName());
		this.setUpClass(Daksha.getTestContextConfig(context.getName()));
	}
	
	@AfterClass
	public void endClass(ITestContext context) throws Exception {
		this.tearDownClass(Daksha.getTestContextConfig(context.getName()));
	}
	
	protected void setUpMethod(TestConfig testConfig) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownMethod(TestConfig testConfig) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	@BeforeMethod
	public void initMethod(ITestContext context) throws Exception {
		this.testConfigName.set(context.getName());
		this.setUpMethod(Daksha.getTestContextConfig(context.getName()));
	}
	
	@AfterMethod
	public void endMethod(ITestContext context) throws Exception {
		this.tearDownMethod(Daksha.getTestContextConfig(context.getName()));
	}
	
	protected String getRootDir() throws Exception {
		// Proceed with default if null;
		return null;
	}
	
	private String getTestContextName() throws Exception {
		String name = this.testConfigName.get();
		if (name == null) {
			String msg = String.format("No context was registered for thread %s", Thread.currentThread().getName());
			throw new Exception(msg);
		} else {
			return name;
		}
	}
	
	protected TestConfig getConfig() throws Exception {
		return Daksha.getTestContextConfig(this.getTestContextName());
	}

}
