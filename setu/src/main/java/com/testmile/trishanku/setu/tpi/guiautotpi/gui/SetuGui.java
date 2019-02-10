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
package com.testmile.trishanku.setu.tpi.guiautotpi.gui;

import com.testmile.daksha.core.guiauto.namestore.GuiNamespace;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.setu.guiauto.core.actions.SetuGuiAutomatorAndElementSharedInterface;
import com.testmile.trishanku.setu.guiauto.core.actions.SetuGuiAutomatorInterface;
import com.testmile.trishanku.setu.guiauto.tpi.automator.SetuGuiAutomator;
import com.testmile.trishanku.setu.guiauto.tpi.element.SetuGuiElement;
import com.testmile.trishanku.setu.guiauto.tpi.element.SetuGuiMultiElement;
import com.testmile.trishanku.tpi.guiauto.automator.ManagedGuiAutomator;

public interface SetuGui extends ManagedGuiAutomator, SetuGuiAutomatorAndElementSharedInterface, SetuGuiAutomatorInterface{
	
	String getLabel() throws Exception;
	String getName();
	
	GuiNamespace getDefinition() throws Exception;
	SetuGui getParent() throws Exception;
	
	SetuGuiAutomator getAutomator() throws Exception; 
	GuiAutomationContext getAutomatorContext() throws Exception;

	SetuGuiElement element(String name) throws Exception;
	SetuGuiMultiElement elements(String name) throws Exception;
	
	void addChild(String label, String defFilePath) throws Exception;
	
	SetuGui gui(String string) throws Exception;

}
