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
package com.testmile.daksha.core.setu.client.guiauto.actions;

import java.util.List;

import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiElement;

public interface SetuClientGuiMultiElementInterface{
	SetuClientGuiElement getInstanceAtIndex(int index) throws Exception;
	SetuClientGuiElement get(int index) throws Exception;
	SetuClientGuiElement getInstanceAtOrdinal(int ordinal) throws Exception;
	SetuClientGuiElement getRandomInstance() throws Exception;
	SetuClientGuiElement getFirstInstance() throws Exception;
	SetuClientGuiElement getLastInstance() throws Exception;
	int getElementCount() throws Exception;
	SetuClientGuiElement getInstanceByText(String text) throws Exception;
	SetuClientGuiElement getInstanceByTextContent(String text) throws Exception;
	List<SetuClientGuiElement> getAllInstances() throws Exception;
}
