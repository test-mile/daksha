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

package daksha.ex.testng.guiauto.sampleusage.appium;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testmile.daksha.core.guiauto.maker.appium.AppiumBuilder;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.testng.TestNGBaseTest;

public class NativeAppAppiumAndroid extends TestNGBaseTest{
	private ThreadLocal<SetuClientGuiAutomator> threadWiseAutomator = new ThreadLocal<SetuClientGuiAutomator>();
	
	protected void tweakTestContext(TestContext testContext) throws Exception {
		testContext.setAutomationContext(GuiAutomationContext.ANDROID_NATIVE);
	}
	
	@BeforeClass
	public void createAutomator(ITestContext testNGContext) throws Exception {
		TestContext context = this.getContext();
		
		AppiumBuilder builder = new AppiumBuilder(this.getContext());
		builder.appPath(context.getAppDir() + "WordPress.apk");
		builder.appPackage(context.getValue("app.pkg").asString());
		builder.appActivity(context.getValue("app.activity").asString());
		this.threadWiseAutomator.set(builder.build());
	}
	
	@Test
	public void test() throws Exception{
		SetuClientGuiAutomator automator = this.threadWiseAutomator.get();
		automator.elementWithXPath("//android.widget.Button[@text='LOG IN']").click();
		automator.elementWithXPath("//android.widget.TextView[contains(@text,'your site address.')]").click();
		automator.elementWithXPath("//android.widget.EditText[@text='Site address']").enterText(this.getContext().getValue("wp.admin.url").asString());
		automator.elementWithXPath("//android.widget.Button[@text='NEXT']").click();
		automator.elementWithXPath("//android.widget.EditText[@text='Username']").enterText(this.getContext().getValue("wp.username").asString());
		automator.elementWithXPath("//android.widget.EditText[@text='Password']").enterText(this.getContext().getValue("wp.password").asString());
		automator.elementWithXPath("//android.widget.Button[@text='NEXT']").click();	
		automator.elementWithXPath("//android.widget.Button[@text='CONTINUE']").click();
	}
	
	public void tearDownClass(TestContext testContext) throws Exception {
		this.threadWiseAutomator.get().close();
	}
}
