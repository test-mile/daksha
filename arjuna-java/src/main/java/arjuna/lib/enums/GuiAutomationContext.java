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
package arjuna.lib.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum GuiAutomationContext {
	WEB,
	NATIVE,
	SCREEN,
	ANDROID_WEB,
	IOS_WEB,
	ANDROID_NATIVE,
	IOS_NATIVE;
	
	private static Set<GuiAutomationContext> desktopContexts = new HashSet<GuiAutomationContext>(Arrays.asList(new GuiAutomationContext[]{GuiAutomationContext.NATIVE, GuiAutomationContext.WEB}));
	private static Set<GuiAutomationContext> mobileWebContexts = new HashSet<GuiAutomationContext>(Arrays.asList(new GuiAutomationContext[]{GuiAutomationContext.ANDROID_WEB, GuiAutomationContext.IOS_WEB}));
	private static Set<GuiAutomationContext> allWebContexts = new HashSet<GuiAutomationContext>(Arrays.asList(new GuiAutomationContext[]{GuiAutomationContext.WEB, GuiAutomationContext.ANDROID_WEB, GuiAutomationContext.IOS_WEB}));
	private static Set<GuiAutomationContext> mobileNativeContexts = new HashSet<GuiAutomationContext>(Arrays.asList(new GuiAutomationContext[]{GuiAutomationContext.ANDROID_NATIVE, GuiAutomationContext.IOS_NATIVE}));
	
	public static boolean isDesktopContext(GuiAutomationContext context) {
		return desktopContexts.contains(context);
	}
	
	public static boolean isMobileWebContext(GuiAutomationContext context) {
		return mobileWebContexts.contains(context);
	}
	
	public static boolean isMobileNativeContext(GuiAutomationContext context) {
		return mobileNativeContexts.contains(context);
	}
	
	public static boolean isAnyWebContext(GuiAutomationContext context) {
		return allWebContexts.contains(context);
	}
}
