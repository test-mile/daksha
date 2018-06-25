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
package daksha.core.meaningful.actions;

import daksha.core.meaningful.element.proxy.GuiElementProxy;
import daksha.core.meaningful.element.proxy.GuiMultiElementProxy;
import daksha.tpi.meaningful.enums.GuiElementType;

public interface GuiElementCreationHandler {
	GuiElementProxy elementWithId(String id) throws Exception;
	GuiElementProxy elementWithName(String name) throws Exception;
	GuiElementProxy elementWithClass(String klass) throws Exception;
	GuiElementProxy elementWithCss(String cssSelector) throws Exception;
	GuiElementProxy elementWithTagName(String string) throws Exception;
	GuiElementProxy elementWithLinkText(String text) throws Exception;
	GuiElementProxy elementWithPartialLinkText(String textContent) throws Exception;
	GuiElementProxy elementWithXPath(String xpath) throws Exception;
	GuiElementProxy elementWithXText(String text) throws Exception;
	GuiElementProxy elementWithXPartialText(String textContent) throws Exception;
	GuiElementProxy elementWithXValue(String value) throws Exception;
	GuiElementProxy elementWithXTitle(String value) throws Exception;
	GuiElementProxy elementWithXImageSource(String path) throws Exception;
	GuiElementProxy elementOfXType(GuiElementType type) throws Exception;
	GuiElementProxy elementBasedOnImage(String imagePath) throws Exception;
	
	GuiMultiElementProxy elementsWithId(String id) throws Exception;
	GuiMultiElementProxy elementsWithName(String name) throws Exception;
	GuiMultiElementProxy elementsWithClass(String klass) throws Exception;
	GuiMultiElementProxy elementsWithCss(String cssSelector) throws Exception;
	GuiMultiElementProxy elementsWithTagName(String string) throws Exception;
	GuiMultiElementProxy elementsWithLinkText(String text) throws Exception;
	GuiMultiElementProxy elementsWithPartialLinkText(String textContent) throws Exception;
	GuiMultiElementProxy elementsWithXPath(String xpath) throws Exception;
	GuiMultiElementProxy elementsWithXText(String text) throws Exception;
	GuiMultiElementProxy elementsWithXPartialText(String textContent) throws Exception;
	GuiMultiElementProxy elementsWithXValue(String value) throws Exception;
	GuiMultiElementProxy elementsWithXTitle(String value) throws Exception;
	GuiMultiElementProxy elementsWithXImageSource(String path) throws Exception;
	GuiMultiElementProxy elementsOfXType(GuiElementType type) throws Exception;
	GuiMultiElementProxy elementsBasedOnImage(String imagePath) throws Exception;
}
