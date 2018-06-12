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
package daksha.tpi.leaping.factories;

import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.UiAutomator;
import daksha.core.leaping.appium.lib.base.DefaultAppiumBuilder;
import daksha.core.leaping.selenium.lib.base.DefaultSeleniumBuilder;
import daksha.core.leaping.sikuli.SikuliScreenUiDriver;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.interfaces.AppiumBuilder;
import daksha.tpi.leaping.interfaces.SeleniumBuilder;
import daksha.tpi.leaping.interfaces.UiDriver;

public class UiDriverFactory {
	
	public static AppiumBuilder getAppiumBuilder() throws Exception{
		return new DefaultAppiumBuilder();
	}
	
	public static SeleniumBuilder getSeleniumBuilder() throws Exception{
		return new DefaultSeleniumBuilder();
	}
	
	public static UiDriver getSelenium() throws Exception{
		SeleniumBuilder builder = new DefaultSeleniumBuilder();
		return builder.build();
	}
	
	private static UiDriver getAppiumWeb() throws Exception{
		AppiumBuilder builder = new DefaultAppiumBuilder();
		builder.context(UiAutomationContext.MOBILE_WEB);
		return builder.build();
	}
	
	private static UiDriver getAppiumNative() throws Exception{
		AppiumBuilder builder = new DefaultAppiumBuilder();
		builder.context(UiAutomationContext.MOBILE_NATIVE);
		return builder.build();
	}
	
	private static UiDriver getAppiumNative(String appPath) throws Exception{
		AppiumBuilder builder = new DefaultAppiumBuilder();
		builder.context(UiAutomationContext.MOBILE_NATIVE);
		builder.appPath(appPath);
		return builder.build();
	}
	
	private static UiDriver getSikuli() throws Exception{
		return new SikuliScreenUiDriver();
	}
	
	public static UiDriver getWebUiDriver() throws Exception{
		return getSelenium();
	}
	
	public static UiDriver getMobileWebUiDriver() throws Exception{
		return getAppiumWeb();
	}

	public static UiDriver getMobileNativeUiDriver() throws Exception{
		return getAppiumNative();
	}
	
	public static UiDriver getMobileNativeUiDriver(String appPath) throws Exception{
		if (appPath == null){
			throw new Problem(
					Batteries.getComponentName("UI_AUTOMATOR"),
					"UiDriver Factory",
					"getMobileNativeAutomator",
					UiAutomator.problem.FACTORY_AUTOMATOR_MOBILE_NULL_APP_PATH,
					Batteries.getProblemText(UiAutomator.problem.FACTORY_AUTOMATOR_MOBILE_NULL_APP_PATH));
			}
		return getAppiumNative(appPath);
	}
	
	public static UiDriver getScreenUiDriver() throws Exception{
		return getSikuli();
	}

	public static UiDriver getUiDriver(UiAutomationContext context) throws Exception {
		switch(context){
		case PC_WEB: return getSelenium();
		case MOBILE_WEB: return getAppiumWeb();
		case MOBILE_NATIVE: return getAppiumNative();
		case SCREEN: return getScreenUiDriver();
		default: 
			return throwUnsupportedAutomationContextException(context);
		}
	}
	
	public static UiDriver getUiDriver(UiAutomationContext context, String appPath) throws Exception {
		switch(context){
		case PC_WEB: throwAppPathFactoryMethodWronglyUsed(context);
		case MOBILE_WEB: throwAppPathFactoryMethodWronglyUsed(context);
		case MOBILE_NATIVE: return getAppiumNative(appPath);
		case SCREEN: throwAppPathFactoryMethodWronglyUsed(context);
		default: return throwUnsupportedAutomationContextException(context);
		}
	}
	
	public static UiDriver throwAppPathFactoryMethodWronglyUsed(UiAutomationContext context) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
				"UiDriver Factory",
				"getUiDriver",
				UiAutomator.problem.FACTORY_METHOD_APPPATH_NOT_APPLICABLE,
				Batteries.getProblemText(
						UiAutomator.problem.FACTORY_METHOD_APPPATH_NOT_APPLICABLE,
						UiAutomator.getAutomationContextName(context))
			);		
	}
	
	public static UiDriver throwUnsupportedAutomationContextException(UiAutomationContext context) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
				"UiDriver Factory",
				"getUiDriver",
				UiAutomator.problem.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
				Batteries.getProblemText(
						UiAutomator.problem.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
						UiAutomator.getAutomationContextName(context))
			);		
	}
}
