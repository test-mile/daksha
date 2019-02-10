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
package com.testmile.setu.guiauto;

import java.util.ArrayList;
import java.util.List;

import com.testmile.daksha.tpi.guiauto.enums.GuiElementType;
import com.testmile.trishanku.setu.guiauto.core.enums.SetuMobileNativeLocateBy;
import com.testmile.trishanku.setu.guiauto.core.enums.SetuMobileWebLocateBy;
import com.testmile.trishanku.setu.guiauto.core.enums.SetuNativeLocateBy;
import com.testmile.trishanku.setu.guiauto.core.enums.SetuVisualLocateBy;
import com.testmile.trishanku.setu.guiauto.core.enums.SetuWebLocateBy;
import com.testmile.trishanku.tpi.guiauto.enums.LocatorType;

public enum SetuGuiAutoSingleton {
	INSTANCE;

	// UI Automator
	private static List<String> allowedGenericLocators = null;
	private static List<String> allowedWebLocators = null;
	private static List<String> allowedNativeLocators = null;
	private static List<String> allowedMobileWebLocators = null;
	private static List<String> allowedMobileNativeLocators = null;
	private static List<String> allowedScreenLocators = null;
	private static List<String> allAllowedGuiElementTypes = null;


	public void init() throws Exception{
		allowedGenericLocators = new ArrayList<String>();
		for (LocatorType prop: LocatorType.class.getEnumConstants()){
			allowedGenericLocators.add(prop.toString());
		}

		allAllowedGuiElementTypes = new ArrayList<String>();
		for (GuiElementType prop: GuiElementType.class.getEnumConstants()){
			allAllowedGuiElementTypes.add(prop.toString());
		}

		allowedScreenLocators = new ArrayList<String>();
		for (SetuVisualLocateBy prop: SetuVisualLocateBy.class.getEnumConstants()){
			allowedScreenLocators.add(prop.toString());
		}

		allowedNativeLocators = new ArrayList<String>();
		for (SetuNativeLocateBy prop: SetuNativeLocateBy.class.getEnumConstants()){
			allowedNativeLocators.add(prop.toString());
		}

		allowedMobileNativeLocators = new ArrayList<String>();
		for (SetuMobileNativeLocateBy prop: SetuMobileNativeLocateBy.class.getEnumConstants()){
			allowedMobileNativeLocators.add(prop.toString());
		}

		allowedWebLocators = new ArrayList<String>();
		for (SetuWebLocateBy prop: SetuWebLocateBy.class.getEnumConstants()){
			allowedWebLocators.add(prop.toString());
		}

		allowedMobileWebLocators = new ArrayList<String>();
		for (SetuMobileWebLocateBy prop: SetuMobileWebLocateBy.class.getEnumConstants()){
			allowedMobileWebLocators.add(prop.toString());		
		}
	}

	/*
	 * UI Automator
	 */

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

}
