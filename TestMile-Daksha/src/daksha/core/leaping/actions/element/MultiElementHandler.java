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
package daksha.core.leaping.actions.element;

import java.util.List;

import daksha.tpi.leaping.element.GuiElement;

public interface MultiElementHandler {	
	// Get wrapped object from raw object, already identified ones
	GuiElement getInstanceAtIndex(int index) throws Exception;
	GuiElement get(int index) throws Exception;
	GuiElement getInstanceAtOrdinal(int ordinal) throws Exception;
	GuiElement getRandomInstance() throws Exception;
	GuiElement getFirstInstance() throws Exception;
	GuiElement getLastInstance() throws Exception;
	int getElementCount() throws Exception;
	GuiElement getInstanceByText(String text) throws Exception;
	GuiElement getInstanceByTextContent(String text) throws Exception;
}
