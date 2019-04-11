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

package arjuna.tpi.testng;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import arjuna.tpi.Arjuna;
import arjuna.tpi.test.TestContext;

public class TestNGBaseTest {
	private ThreadLocal<String> testContextName = new ThreadLocal<String>();
	
	protected void setUpSuite(TestContext suiteContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownSuite(TestContext suiteContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	@BeforeSuite
	public void initArjuna(ITestContext testngContext) throws Exception {
		TestContext centralContext;
		String rootDir = this.getRootDir();
		if (rootDir == null) {
			centralContext = Arjuna.init();
		} else {
			centralContext = Arjuna.init(rootDir);
		}
		
		TestContext suiteContext = Arjuna.createTestNGSuiteContext(centralContext, testngContext);
		testContextName.set(suiteContext.getName());
		this.setUpSuite(suiteContext);
	}
	
	@AfterSuite
	public void cleanUpSession(ITestContext testngContext) throws Exception {		
		this.tearDownSuite(Arjuna.getTestContext(testngContext.getSuite().getXmlSuite().getName()));
	}
	
	protected void setUpTest(TestContext testContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownTest(TestContext testContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	@BeforeTest
	public void initContext(ITestContext testContext) throws Exception {
		String suiteContextName = testContext.getSuite().getXmlSuite().getName();
		TestContext suiteContext = Arjuna.getTestContext(suiteContextName);
		TestContext testngTestContext = Arjuna.createTestNGTestContext(suiteContext, testContext);		
		Arjuna.registerTestContext(testngTestContext);
		testContextName.set(testngTestContext.getName());
		this.setUpTest(testngTestContext);
	}
	
	@AfterTest
	public void endContext(ITestContext testngContext) throws Exception {
		this.tearDownTest(Arjuna.getTestContext(testngContext.getName()));
	}
	
	protected void setUpClass(TestContext classContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownClass(TestContext classContext) throws Exception {
	}
	
	@BeforeClass
	public void initClass(ITestContext testngContext) throws Exception {
		testContextName.set(testngContext.getName());
		this.setUpClass(Arjuna.getTestContext(testngContext.getName()));
	}
	
	@AfterClass
	public void endClass(ITestContext testngContext) throws Exception {
		this.tearDownClass(Arjuna.getTestContext(testngContext.getName()));
	}
	
	protected void setUpMethod(TestContext methodContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	protected void tearDownMethod(TestContext methodContext) throws Exception {
		// Proceed with defaults if not overriden.
	}
	
	@BeforeMethod
	public void initMethod(ITestContext testngContext) throws Exception {
		testContextName.set(testngContext.getName());
		this.setUpMethod(Arjuna.getTestContext(testngContext.getName()));
	}
	
	@AfterMethod
	public void endMethod(ITestContext testngContext) throws Exception {
		this.tearDownMethod(Arjuna.getTestContext(testngContext.getName()));
	}
	
	protected String getRootDir() throws Exception {
		// Proceed with default if null;
		return null;
	}
	
	protected TestContext getTestContext() throws Exception {
		String name = this.testContextName.get();
		if (name == null) {
			String msg = String.format("No context was registered for thread %s", Thread.currentThread().getName());
			throw new Exception(msg);
		} else {
			return Arjuna.getTestContext(name);
		}
	}

}
