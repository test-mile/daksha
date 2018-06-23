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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daksha.core.leaping.enums.AppiumAndroidBrowserType;
import daksha.core.leaping.enums.AppiumIosBrowserType;
import daksha.core.leaping.enums.LocateBy;
import daksha.core.leaping.enums.MobileNativeLocateBy;
import daksha.core.leaping.enums.MobileWebLocateBy;
import daksha.core.leaping.enums.NativeLocateBy;
import daksha.core.leaping.enums.OSType;
import daksha.core.leaping.enums.VisualLocateBy;
import daksha.core.leaping.enums.WebLocateBy;
import daksha.tpi.leaping.enums.GuiAutomationContext;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.sysauto.utils.DataUtils;

public enum LeapingSingleton {
	INSTANCE;

	// UI Automator
	private static List<String> allowedGenericIdentifiers = null;
	private static List<String> allowedWebIdentifiers = null;
	private static List<String> allowedNativeIdentifiers = null;
	private static List<String> allowedMobileWebIdentifiers = null;
	private static List<String> allowedMobileNativeIdentifiers = null;
	private static List<String> allowedScreenIdentifiers = null;
	private static List<String> allAllowedUiElementTypes = null;
	private static Map<GuiAutomationContext, String> automationContextNames = null;
	// Appium
	private static List<String> allowedAppiumPlatforms = new ArrayList<String>();;
	private static List<String> allowedAppiumAndroidBrowsers = new ArrayList<String>();;
	private static List<String> allowedAppiumIosBrowsers = new ArrayList<String>();

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

		allowedGenericIdentifiers = new ArrayList<String>();
		for (LocateBy prop: LocateBy.class.getEnumConstants()){
			allowedGenericIdentifiers.add(prop.toString());
		}

		allAllowedUiElementTypes = new ArrayList<String>();
		for (GuiElementType prop: GuiElementType.class.getEnumConstants()){
			allAllowedUiElementTypes.add(prop.toString());
		}

		allowedScreenIdentifiers = new ArrayList<String>();
		for (VisualLocateBy prop: VisualLocateBy.class.getEnumConstants()){
			allowedScreenIdentifiers.add(prop.toString());
		}

		allowedNativeIdentifiers = new ArrayList<String>();
		for (NativeLocateBy prop: NativeLocateBy.class.getEnumConstants()){
			allowedNativeIdentifiers.add(prop.toString());
		}

		allowedMobileNativeIdentifiers = new ArrayList<String>();
		for (MobileNativeLocateBy prop: MobileNativeLocateBy.class.getEnumConstants()){
			allowedMobileNativeIdentifiers.add(prop.toString());
		}

		allowedWebIdentifiers = new ArrayList<String>();
		for (WebLocateBy prop: WebLocateBy.class.getEnumConstants()){
			allowedWebIdentifiers.add(prop.toString());
		}

		allowedMobileWebIdentifiers = new ArrayList<String>();
		for (MobileWebLocateBy prop: MobileWebLocateBy.class.getEnumConstants()){
			allowedMobileWebIdentifiers.add(prop.toString());		
		}
	}

	/*
	 * UI Automator
	 */

	public String getAutomationContextName(GuiAutomationContext type) {
		return automationContextNames.get(type);
	}

	public List<String> getAllowedGenericIdentifiers(){
		return allowedGenericIdentifiers;
	}

	public List<String> getAllAllowedUiElementTypes(){
		return allAllowedUiElementTypes;
	}

	public List<String> getAllowedScreenIdentifiers(){
		return allowedScreenIdentifiers;
	}

	public List<String> getAllowedNativeIdentifiers() {
		return allowedNativeIdentifiers;
	}

	public List<String> getAllowedMobileNativeIdentifiers() {
		return allowedMobileNativeIdentifiers;
	}

	public List<String> getAllowedWebIdentifiers() {
		return allowedWebIdentifiers;
	}

	public List<String> getAllowedMobileWebIdentifiers() {
		return allowedMobileWebIdentifiers;
	}

	public List<String> getAllowedIdentifiers() throws Exception{
		return getAllowedGenericIdentifiers();
//		switch(context){
//		case PC_WEB: return getAllowedWebIdentifiers();
//		case PC_NATIVE: return getAllowedNativeIdentifiers();
//		case MOBILE_WEB: return getAllowedMobileWebIdentifiers();
//		case MOBILE_NATIVE: return getAllowedMobileNativeIdentifiers();
//		case SCREEN: return getAllowedScreenIdentifiers();
//		case GENERIC: return getAllowedGenericIdentifiers();
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

}
