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
package daksha;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.ITestContext;

import daksha.core.batteries.config.Configuration;
import daksha.core.batteries.config.TestContext;
import daksha.core.meaningful.UiAutoSingleton;
import daksha.core.meaningful.enums.OSType;
import daksha.tpi.meaningful.enums.GuiAutomationContext;

public class Daksha {
	private static DakshaSingleton core = DakshaSingleton.INSTANCE;
	
	public static void init(String rootDir) throws Exception{
		core.init(rootDir);
	}
	
	public static void init() throws Exception {
		init(System.getProperty("user.dir"));
	}
	
	public static void setOSType(OSType osType) throws Exception {
		core.setOSType(osType);
	}
	
	public static void freezeCentralConfig() throws Exception {
		core.freezeCentralConfig();
	}
	
	public static void registerContext(TestContext context) throws Exception {
		core.registerContext(context);
	}
	
	public static TestContext getDefaultTestContext() throws Exception{
		return core.getDefaultTestContext();
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
	
	public static String getAutomationContextName(GuiAutomationContext context) {
		return UiAutoSingleton.INSTANCE.getAutomationContextName(context);
	}

	public static List<String> getAllowedPickByStrings() throws Exception {
		return UiAutoSingleton.INSTANCE.getAllowedPickByStrings() ;
	}

	public static List<String> getAllAllowedGuiElementTypes() {
		return UiAutoSingleton.INSTANCE.getAllAllowedGuiElementTypes();
	}
	
	public static String getComponentName(){
		return "UI Automator";
	}
	
	public static boolean isAllowedAppiumPlatform(String platformName){
		return UiAutoSingleton.INSTANCE.isAllowedAppiumPlatform(platformName);
	}
	
	public static String getAppiumPlatformString(OSType platform) throws Exception{
		return UiAutoSingleton.INSTANCE.getAppiumPlatformString(platform);
	}
	
	public static String getAppiumBrowserString(String rawName) throws Exception{
		return UiAutoSingleton.INSTANCE.getAppiumBrowserString(rawName);
	}

	public static boolean isAllowedAppiumBrowser(OSType platform, String browser) throws Exception {
		return UiAutoSingleton.INSTANCE.isAllowedAppiumBrowser(platform, browser);
	}
	
}
