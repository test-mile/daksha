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
package daksha.core.leaping.automator.appium;

import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.MobileNativeIdentifyBy;
import daksha.tpi.leaping.enums.UiAutomationContext;

public class AppiumNativeUiDriver extends BaseAppiumUiDriver {

	public AppiumNativeUiDriver(ElementLoaderType loaderType) throws Exception{
		super(UiAutomationContext.MOBILE_NATIVE, loaderType);
	}
		
	public AppiumNativeUiDriver() throws Exception{
		this(ElementLoaderType.AUTOMATOR);
	}

	protected boolean checkNullIdentifier(String identifier, String idValue) throws Exception{
		return MobileNativeIdentifyBy.valueOf(identifier) == null;
	}

	@Override
	public void switchToWebContext() throws Exception{
		this.getUnderlyingEngine().context("WEBVIEW");
	}
	
	@Override
	public void switchToNativeContext() throws Exception{
		this.getUnderlyingEngine().context("NATIVE");
	}
}
