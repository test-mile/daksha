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
package daksha.core.uiauto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daksha.core.uiauto.enums.AppiumAndroidBrowserType;
import daksha.core.uiauto.enums.AppiumIosBrowserType;
import daksha.core.uiauto.enums.LocateBy;
import daksha.core.uiauto.enums.MobileNativeLocateBy;
import daksha.core.uiauto.enums.MobileWebLocateBy;
import daksha.core.uiauto.enums.NativeLocateBy;
import daksha.core.uiauto.enums.OSType;
import daksha.core.uiauto.enums.VisualLocateBy;
import daksha.core.uiauto.enums.WebLocateBy;
import daksha.core.uiauto.launcher.appium.AppiumDriverServerLauncher;
import daksha.tpi.sysauto.utils.DataUtils;
import daksha.tpi.uiauto.enums.GuiAutomationContext;
import daksha.tpi.uiauto.enums.GuiElementType;

public enum UiAutoSingleton {
	INSTANCE;

	// UI Automator
	private static List<String> allowedGenericLocators = null;
	private static List<String> allowedWebLocators = null;
	private static List<String> allowedNativeLocators = null;
	private static List<String> allowedMobileWebLocators = null;
	private static List<String> allowedMobileNativeLocators = null;
	private static List<String> allowedScreenLocators = null;
	private static List<String> allAllowedGuiElementTypes = null;
	private static Map<GuiAutomationContext, String> automationContextNames = null;
	// Appium
	private static List<String> allowedAppiumPlatforms = new ArrayList<String>();;
	private static List<String> allowedAppiumAndroidBrowsers = new ArrayList<String>();;
	private static List<String> allowedAppiumIosBrowsers = new ArrayList<String>();
	private static AppiumDriverServerLauncher serverLauncher = null;

	public void init() throws Exception{
		/* Appium */
		for (OSType prop: OSType.class.getEnumConstants()){
			allowedAppiumPlatforms.add(prop.toString());
		}

		for (AppiumAndroidBrowserType prop: AppiumAndroidBrowserType.class.getEnumConstants()){
			allowedAppiumAndroidBrowsers.add(prop.toString());
		}

		for (AppiumIosBrowserType prop: AppiumIosBrowserType.class.getEnumConstants()){
			allowedAppiumIosBrowsers.add(prop.toString());
		}

		/* UI Automator */
		automationContextNames = new HashMap<GuiAutomationContext, String>();
		automationContextNames.put(GuiAutomationContext.PC_WEB, "PC Web");
		automationContextNames.put(GuiAutomationContext.PC_NATIVE, "PC Native");
		automationContextNames.put(GuiAutomationContext.MOBILE_WEB, "Mobile Web");
		automationContextNames.put(GuiAutomationContext.MOBILE_NATIVE, "Mobile Native");
		automationContextNames.put(GuiAutomationContext.SCREEN, "Screen");
		automationContextNames.put(GuiAutomationContext.GENERIC, "Generic");

		allowedGenericLocators = new ArrayList<String>();
		for (LocateBy prop: LocateBy.class.getEnumConstants()){
			allowedGenericLocators.add(prop.toString());
		}

		allAllowedGuiElementTypes = new ArrayList<String>();
		for (GuiElementType prop: GuiElementType.class.getEnumConstants()){
			allAllowedGuiElementTypes.add(prop.toString());
		}

		allowedScreenLocators = new ArrayList<String>();
		for (VisualLocateBy prop: VisualLocateBy.class.getEnumConstants()){
			allowedScreenLocators.add(prop.toString());
		}

		allowedNativeLocators = new ArrayList<String>();
		for (NativeLocateBy prop: NativeLocateBy.class.getEnumConstants()){
			allowedNativeLocators.add(prop.toString());
		}

		allowedMobileNativeLocators = new ArrayList<String>();
		for (MobileNativeLocateBy prop: MobileNativeLocateBy.class.getEnumConstants()){
			allowedMobileNativeLocators.add(prop.toString());
		}

		allowedWebLocators = new ArrayList<String>();
		for (WebLocateBy prop: WebLocateBy.class.getEnumConstants()){
			allowedWebLocators.add(prop.toString());
		}

		allowedMobileWebLocators = new ArrayList<String>();
		for (MobileWebLocateBy prop: MobileWebLocateBy.class.getEnumConstants()){
			allowedMobileWebLocators.add(prop.toString());		
		}
		
		serverLauncher = new AppiumDriverServerLauncher();
	}

	/*
	 * UI Automator
	 */

	public String getAutomationContextName(GuiAutomationContext type) {
		return automationContextNames.get(type);
	}

	public List<String> getAllowedGenericLocators(){
		return allowedGenericLocators;
	}

	public List<String> getAllAllowedGuiElementTypes(){
		return allAllowedGuiElementTypes;
	}

	public List<String> getAllowedScreenLocators(){
		return allowedScreenLocators;
	}

	public List<String> getAllowedNativeLocators() {
		return allowedNativeLocators;
	}

	public List<String> getAllowedMobileNativeLocators() {
		return allowedMobileNativeLocators;
	}

	public List<String> getAllowedWebLocators() {
		return allowedWebLocators;
	}

	public List<String> getAllowedMobileWebLocators() {
		return allowedMobileWebLocators;
	}

	public List<String> getAllowedPickByStrings() throws Exception{
		return getAllowedGenericLocators();
//		switch(context){
//		case PC_WEB: return getAllowedWebLocators();
//		case PC_NATIVE: return getAllowedNativeLocators();
//		case MOBILE_WEB: return getAllowedMobileWebLocators();
//		case MOBILE_NATIVE: return getAllowedMobileNativeLocators();
//		case SCREEN: return getAllowedScreenLocators();
//		case GENERIC: return getAllowedGenericLocators();
//		default: throw new Exception("Unknown id context.");
//		}
	}
	
	/*
	 * Appium Inquiry Methods
	 */

	private List<String> getAllowedPlatformsForAppium(){
		return allowedAppiumPlatforms;
	}

	private List<String> getAllowedBrowsersForAppium(OSType platform) throws Exception{
		switch (platform){
		case ANDROID: return allowedAppiumAndroidBrowsers;
		case IOS: return allowedAppiumIosBrowsers;
		default: throw new Exception("Unknown platform: " + platform);
		}
	}

	public boolean isAllowedAppiumPlatform(String platformName){
		return getAllowedPlatformsForAppium().contains(platformName.toUpperCase());
	}

	public boolean isAllowedAppiumBrowser(OSType platform, String browserName) throws Exception{
		return getAllowedBrowsersForAppium(platform).contains(browserName.toUpperCase());
	}

	public String getAppiumPlatformString(OSType platform) throws Exception{
		switch(platform){
		case ANDROID: return "Android";
		case IOS: return "iOS";
		default: return platform.toString();
		}
	}

	public String getAppiumBrowserString(String rawName) throws Exception{
		return DataUtils.toTitleCase(rawName);
	}
	
	public AppiumDriverServerLauncher getDriverServerLauncher() throws Exception{
		return serverLauncher;
	}

}
