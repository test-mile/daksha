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
package daksha.core.cleanup.actions;

import daksha.core.cleanup.element.proxy.GuiElementProxy;
import daksha.core.cleanup.element.proxy.MultiGuiElementProxy;
import daksha.tpi.cleanup.enums.GuiElementType;

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
	
	MultiGuiElementProxy elementsWithId(String id) throws Exception;
	MultiGuiElementProxy elementsWithName(String name) throws Exception;
	MultiGuiElementProxy elementsWithClass(String klass) throws Exception;
	MultiGuiElementProxy elementsWithCss(String cssSelector) throws Exception;
	MultiGuiElementProxy elementsWithTagName(String string) throws Exception;
	MultiGuiElementProxy elementsWithLinkText(String text) throws Exception;
	MultiGuiElementProxy elementsWithPartialLinkText(String textContent) throws Exception;
	MultiGuiElementProxy elementsWithXPath(String xpath) throws Exception;
	MultiGuiElementProxy elementsWithXText(String text) throws Exception;
	MultiGuiElementProxy elementsWithXPartialText(String textContent) throws Exception;
	MultiGuiElementProxy elementsWithXValue(String value) throws Exception;
	MultiGuiElementProxy elementsWithXTitle(String value) throws Exception;
	MultiGuiElementProxy elementsWithXImageSource(String path) throws Exception;
	MultiGuiElementProxy elementsOfXType(GuiElementType type) throws Exception;
	MultiGuiElementProxy elementsBasedOnImage(String imagePath) throws Exception;
}
