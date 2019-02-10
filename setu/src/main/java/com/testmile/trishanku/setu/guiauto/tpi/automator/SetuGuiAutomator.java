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
package com.testmile.trishanku.setu.guiauto.tpi.automator;

import java.util.Map;

import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.setu.guiauto.core.actions.SetuGuiAutomatorAndElementSharedInterface;
import com.testmile.trishanku.setu.guiauto.core.actions.SetuGuiAutomatorInterface;
import com.testmile.trishanku.setu.guiauto.core.identifier.GuiElementMetaData;
import com.testmile.trishanku.setu.guiauto.tpi.element.SetuGuiElement;
import com.testmile.trishanku.setu.guiauto.tpi.element.SetuGuiMultiElement;
import com.testmile.trishanku.tpi.guiauto.automator.ManagedGuiAutomator;
import com.testmile.trishanku.tpi.guiauto.enums.GuiElementLoaderType;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

public interface SetuGuiAutomator extends ManagedGuiAutomator, SetuGuiAutomatorAndElementSharedInterface, SetuGuiAutomatorInterface{

	void setAutomatorContext(GuiAutomationContext screen);
	
	GuiElementLoaderType getElementLoaderType() throws Exception;
	
	void setElementLoaderType(GuiElementLoaderType loaderType) throws Exception;

	void load() throws Exception;

	void setCapabilities(Map<String, ?> caps);

	void init() throws Exception;

	String getName();

	void setWaitTime(int waitTime);

	void setOSType(OSType platformType);

	TestContext getTestContext();

	int getWaitTime();
	
	SetuGuiElement element(GuiElementMetaData eData) throws Exception;
	
	SetuGuiMultiElement elements(GuiElementMetaData eData) throws Exception;

	void validatePageLoad();

}
