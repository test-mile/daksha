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

package daksha.ex.uiauto.sampleusage.appium;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import daksha.Daksha;
import daksha.core.batteries.config.TestContext;
import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.uiauto.enums.OSType;
import daksha.ex.config.AppConfig;
import daksha.tpi.testng.TestNGBaseTest;
import daksha.tpi.uiauto.enums.GuiAutomationContext;
import daksha.tpi.uiauto.maker.GuiAutomatorFactory;
import daksha.tpi.uiauto.maker.appium.AppiumBuilder;

public class NativeAppAppiumAndroid extends TestNGBaseTest{
	private ThreadLocal<GuiAutomatorProxy> threadWiseAutomator = new ThreadLocal<GuiAutomatorProxy>();
	
	protected void setCentralOptions() throws Exception {
		Daksha.setOSType(OSType.MAC);
	}
	
	@BeforeClass
	public void createAutomator(ITestContext testNGContext) throws Exception {
		TestContext context = Daksha.getTestContext(this.getTestContextName());
		context.setAutomationContext(GuiAutomationContext.ANDROID_NATIVE);
		AppiumBuilder builder = GuiAutomatorFactory.getAppiumBuilder(context);
		builder.appPath(context.getAppDir() + "WordPress.apk");
		builder.appPackage(AppConfig.APP_PKG);
		builder.appActivity(AppConfig.APP_ACTIVITY);
		this.threadWiseAutomator.set(builder.build());
	}
	
	@Test
	public void test() throws Exception{
		GuiAutomatorProxy automator = this.threadWiseAutomator.get();
		automator.elementWithXPath("//android.widget.Button[@text='LOG IN']").click();
		automator.elementWithXPath("//android.widget.TextView[contains(@text,'your site address.')]").click();
		automator.elementWithXPath("//android.widget.EditText[@text='Site address']").enterText(AppConfig.WP_URL);
		automator.elementWithXPath("//android.widget.Button[@text='NEXT']").click();
		automator.elementWithXPath("//android.widget.EditText[@text='Username']").enterText(AppConfig.USER_NAME);
		automator.elementWithXPath("//android.widget.EditText[@text='Password']").enterText(AppConfig.PASSWORD);
		automator.elementWithXPath("//android.widget.Button[@text='NEXT']").click();	
		automator.elementWithXPath("//android.widget.Button[@text='CONTINUE']").click();
	}
	
	@AfterClass
	public void closeAutomator(ITestContext testContext) throws Exception {
		//this.threadWiseAutomator.get().close();
	}
}
