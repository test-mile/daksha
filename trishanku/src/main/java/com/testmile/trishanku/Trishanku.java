/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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
package com.testmile.trishanku;

import org.apache.log4j.Logger;
import org.testng.ITestContext;

import com.testmile.daksha.core.batteries.config.Configuration;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;

public class Trishanku {
	private static TrishankuSingleton core = TrishankuSingleton.INSTANCE;
	
	public static TestContext init(String rootDir) throws Exception{
		return core.init(rootDir);
	}
	
	public static TestContext init() throws Exception {
		return init(System.getProperty("user.dir"));
	}
	
	public static TestContext getCentralContext() throws Exception {
		return core.getCentralContext();		
	}
	
	public static void registerContext(TestContext context) throws Exception {
		core.registerContext(context);
	}
	
	public static String getRootDir() {
		return core.getRootDir();
	}
		
	public static TestContext getTestContext(String name) throws Exception {
		return core.getTestContext(name);
	}
	
	public static TestContext getTestContext(ITestContext context) throws Exception {
		return core.getTestContext(context);
	}
	
	public static Configuration getTestContextConfig(String name) throws Exception {
		return core.getTestContextConfig(name);
	}
	
	public static Configuration getTestContextConfig(ITestContext context) throws Exception {
		return core.getTestContextConfig(context);
	}
	 
	public static Logger getLogger() { 
		return core.getLogger();
	}

	public static TestContext createTestContext(String name) throws Exception {
		return core.createTestContext(name);
	}
	
	public static String getAutomationContextName(GuiAutomationContext context) {
		return core.getAutomationContextName(context);
	}
	
}
