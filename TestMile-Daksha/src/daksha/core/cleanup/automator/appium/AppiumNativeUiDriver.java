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
package daksha.core.cleanup.automator.appium;

import java.io.File;
import java.util.Set;

import daksha.core.batteries.config.TestContext;
import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.enums.MobileNativeLocateBy;
import daksha.core.cleanup.enums.MobileView;
import daksha.core.cleanup.enums.OSType;
import daksha.tpi.cleanup.enums.GuiAutomationContext;

public class AppiumNativeUiDriver extends BaseAppiumUiDriver {

	public AppiumNativeUiDriver(TestContext testContext, ElementLoaderType loaderType) throws Exception{
		super(testContext, GuiAutomationContext.MOBILE_NATIVE, loaderType);
	}
		
	public AppiumNativeUiDriver(TestContext testContext) throws Exception{
		this(testContext, ElementLoaderType.AUTOMATOR);
	}
	
	private void switchToView(String view) throws Exception {
		this.getUnderlyingEngine().context(view);
	}

	@Override
	public void switchToNativeView() throws Exception {
		switchToView(MobileView.NATIVE_APP.toString());
	}

	@Override
	public void switchToWebView() throws Exception {
		Set<String> contextNames = this.getUnderlyingEngine().getContextHandles();
		for(String context: contextNames) {
			if (context.contains(MobileView.WEBVIEW.toString())) {
				switchToView(context);
				break;
			}
		}
	}
	
	@Override
	public void switchToWebView(String pkg) throws Exception {
		switchToView(String.format("%s_%s", MobileView.WEBVIEW.toString(), pkg));
	}

	@Override
	public File takeScreenshot() throws Exception {
		if (this.getOSType().equals(OSType.ANDROID)) {
			String context = this.getUnderlyingEngine().getContext();
			if (context.contains(MobileView.NATIVE_APP.toString())) {
				return super.takeScreenshot();
			} else {
				switchToNativeView();
				File f = super.takeScreenshot();
				switchToWebView();
				return f;
			}
		} else {
			return super.takeScreenshot();
		}
	}
}
