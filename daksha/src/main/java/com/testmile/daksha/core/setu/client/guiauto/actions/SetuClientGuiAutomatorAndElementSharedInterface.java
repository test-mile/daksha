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

import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiElement;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiMultiElement;
import com.testmile.daksha.tpi.guiauto.enums.GuiElementType;

public interface SetuClientGuiAutomatorAndElementSharedInterface {
	SetuClientGuiElement elementWithId(String id) throws Exception;
	SetuClientGuiElement elementWithName(String name) throws Exception;
	SetuClientGuiElement elementWithClass(String klass) throws Exception;
	SetuClientGuiElement elementWithCss(String cssSelector) throws Exception;
	SetuClientGuiElement elementWithTagName(String tagName) throws Exception;
	SetuClientGuiElement elementWithLinkText(String text) throws Exception;
	SetuClientGuiElement elementWithPartialLinkText(String textContent) throws Exception;
	SetuClientGuiElement elementWithXPath(String xpath) throws Exception;
	SetuClientGuiElement elementWithXText(String text) throws Exception;
	SetuClientGuiElement elementWithXPartialText(String textContent) throws Exception;
	SetuClientGuiElement elementWithXValue(String value) throws Exception;
	SetuClientGuiElement elementWithXTitle(String value) throws Exception;
	SetuClientGuiElement elementWithXImageSource(String path) throws Exception;
	SetuClientGuiElement elementOfXType(GuiElementType eType) throws Exception;
	SetuClientGuiElement elementBasedOnImage(String imagePath) throws Exception;
	
	SetuClientGuiMultiElement elementsWithId(String id) throws Exception;
	SetuClientGuiMultiElement elementsWithName(String name) throws Exception;
	SetuClientGuiMultiElement elementsWithClass(String klass) throws Exception;
	SetuClientGuiMultiElement elementsWithCss(String cssSelector) throws Exception;
	SetuClientGuiMultiElement elementsWithTagName(String tagName) throws Exception;
	SetuClientGuiMultiElement elementsWithLinkText(String text) throws Exception;
	SetuClientGuiMultiElement elementsWithPartialLinkText(String textContent) throws Exception;
	SetuClientGuiMultiElement elementsWithXPath(String xpath) throws Exception;
	SetuClientGuiMultiElement elementsWithXText(String text) throws Exception;
	SetuClientGuiMultiElement elementsWithXPartialText(String textContent) throws Exception;
	SetuClientGuiMultiElement elementsWithXValue(String value) throws Exception;
	SetuClientGuiMultiElement elementsWithXTitle(String value) throws Exception;
	SetuClientGuiMultiElement elementsWithXImageSource(String path) throws Exception;
	SetuClientGuiMultiElement elementsOfXType(GuiElementType eType) throws Exception;
	SetuClientGuiMultiElement elementsBasedOnImage(String imagePath) throws Exception;
}
