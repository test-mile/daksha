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
package daksha.core.leaping.interfaces.appactions;

import daksha.tpi.leaping.enums.UiElementType;
import daksha.tpi.leaping.interfaces.GuiElement;

public interface ElementCreationHandler {
	GuiElement elementWithId(String id) throws Exception;
	GuiElement elementWithName(String name) throws Exception;
	GuiElement elementWithClass(String klass) throws Exception;
	GuiElement elementWithCss(String cssSelector) throws Exception;
	GuiElement elementWithLinkText(String text) throws Exception;
	GuiElement elementWithPartialLinkText(String textContent) throws Exception;
	GuiElement elementWithXPath(String xpath) throws Exception;
	GuiElement elementWithXText(String text) throws Exception;
	GuiElement elementWithXPartialText(String textContent) throws Exception;
	GuiElement elementWithXValue(String value) throws Exception;
	GuiElement elementWithXImageSource(String path) throws Exception;
	GuiElement elementOfXType(UiElementType type) throws Exception;
	GuiElement elementBasedOnImage(String imagePath) throws Exception;
}
