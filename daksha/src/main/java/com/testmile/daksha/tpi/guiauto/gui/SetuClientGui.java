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
package com.testmile.daksha.tpi.guiauto.gui;

import com.testmile.daksha.core.setu.client.guiauto.actions.SetuClientGuiAutomatorInterface;
import com.testmile.daksha.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiElement;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiMultiElement;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.guiauto.automator.ManagedGuiAutomator;

public interface SetuClientGui extends ManagedGuiAutomator, SetuClientGuiAutomatorInterface{
	
	String getLabel() throws Exception;
	String getName();

	SetuClientGui getParent() throws Exception;
	
	SetuClientGuiAutomator getAutomator() throws Exception; 
	GuiAutomationContext getAutomatorContext() throws Exception;

	SetuClientGuiElement element(String name) throws Exception;
	SetuClientGuiMultiElement elements(String name) throws Exception;
	
	void addChild(String label, String defFilePath) throws Exception;
	
	SetuClientGui gui(String string) throws Exception;

}
