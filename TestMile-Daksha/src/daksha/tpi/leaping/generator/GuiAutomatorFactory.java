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
package daksha.tpi.leaping.generator;

import daksha.Daksha;
import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.automator.sikuli.SikuliScreenUiDriver;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.generator.appium.AppiumBuilder;
import daksha.tpi.leaping.generator.selenium.SeleniumBuilder;

public class GuiAutomatorFactory {
	
	public static AppiumBuilder getAppiumBuilder() throws Exception{
		return new AppiumBuilder();
	}
	
	public static SeleniumBuilder getSeleniumBuilder() throws Exception{
		return new SeleniumBuilder();
	}
	
	public static GuiAutomator getSelenium() throws Exception{
		SeleniumBuilder builder = new SeleniumBuilder();
		return builder.build();
	}
	
	private static GuiAutomator getAppiumWeb() throws Exception{
		AppiumBuilder builder = new AppiumBuilder();
		builder.context(UiAutomationContext.MOBILE_WEB);
		return builder.build();
	}
	
	private static GuiAutomator getAppiumNative() throws Exception{
		AppiumBuilder builder = new AppiumBuilder();
		builder.context(UiAutomationContext.MOBILE_NATIVE);
		return builder.build();
	}
	
	private static GuiAutomator getAppiumNative(String appPath) throws Exception{
		AppiumBuilder builder = new AppiumBuilder();
		builder.context(UiAutomationContext.MOBILE_NATIVE);
		builder.appPath(appPath);
		return builder.build();
	}
	
	private static GuiAutomator getSikuli() throws Exception{
		return new SikuliScreenUiDriver();
	}
	
	public static GuiAutomator getWebUiDriver() throws Exception{
		return getSelenium();
	}
	
	public static GuiAutomator getMobileWebUiDriver() throws Exception{
		return getAppiumWeb();
	}

	public static GuiAutomator getMobileNativeUiDriver() throws Exception{
		return getAppiumNative();
	}
	
	public static GuiAutomator getMobileNativeUiDriver(String appPath) throws Exception{
		if (appPath == null){
			throw new Problem(
					Batteries.getComponentName("UI_AUTOMATOR"),
					"UiDriver Factory",
					"getMobileNativeAutomator",
					Daksha.problem.FACTORY_AUTOMATOR_MOBILE_NULL_APP_PATH,
					Batteries.getProblemText(Daksha.problem.FACTORY_AUTOMATOR_MOBILE_NULL_APP_PATH));
			}
		return getAppiumNative(appPath);
	}
	
	public static GuiAutomator getScreenUiDriver() throws Exception{
		return getSikuli();
	}

	public static GuiAutomator getUiDriver(UiAutomationContext context) throws Exception {
		switch(context){
		case PC_WEB: return getSelenium();
		case MOBILE_WEB: return getAppiumWeb();
		case MOBILE_NATIVE: return getAppiumNative();
		case SCREEN: return getScreenUiDriver();
		default: 
			return throwUnsupportedAutomationContextException(context);
		}
	}
	
	public static GuiAutomator getUiDriver(UiAutomationContext context, String appPath) throws Exception {
		switch(context){
		case PC_WEB: throwAppPathFactoryMethodWronglyUsed(context);
		case MOBILE_WEB: throwAppPathFactoryMethodWronglyUsed(context);
		case MOBILE_NATIVE: return getAppiumNative(appPath);
		case SCREEN: throwAppPathFactoryMethodWronglyUsed(context);
		default: return throwUnsupportedAutomationContextException(context);
		}
	}
	
	public static GuiAutomator throwAppPathFactoryMethodWronglyUsed(UiAutomationContext context) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
				"UiDriver Factory",
				"getUiDriver",
				Daksha.problem.FACTORY_METHOD_APPPATH_NOT_APPLICABLE,
				Batteries.getProblemText(
						Daksha.problem.FACTORY_METHOD_APPPATH_NOT_APPLICABLE,
						Daksha.getAutomationContextName(context))
			);		
	}
	
	public static GuiAutomator throwUnsupportedAutomationContextException(UiAutomationContext context) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
				"UiDriver Factory",
				"getUiDriver",
				Daksha.problem.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
				Batteries.getProblemText(
						Daksha.problem.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
						Daksha.getAutomationContextName(context))
			);		
	}
}
