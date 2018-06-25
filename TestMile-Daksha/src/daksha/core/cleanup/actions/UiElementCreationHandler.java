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

import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.element.proxy.UiMultiElementProxy;
import daksha.tpi.cleanup.enums.UiElementType;

public interface UiElementCreationHandler {
	UiElementProxy elementWithId(String id) throws Exception;
	UiElementProxy elementWithName(String name) throws Exception;
	UiElementProxy elementWithClass(String klass) throws Exception;
	UiElementProxy elementWithCss(String cssSelector) throws Exception;
	UiElementProxy elementWithTagName(String string) throws Exception;
	UiElementProxy elementWithLinkText(String text) throws Exception;
	UiElementProxy elementWithPartialLinkText(String textContent) throws Exception;
	UiElementProxy elementWithXPath(String xpath) throws Exception;
	UiElementProxy elementWithXText(String text) throws Exception;
	UiElementProxy elementWithXPartialText(String textContent) throws Exception;
	UiElementProxy elementWithXValue(String value) throws Exception;
	UiElementProxy elementWithXTitle(String value) throws Exception;
	UiElementProxy elementWithXImageSource(String path) throws Exception;
	UiElementProxy elementOfXType(UiElementType type) throws Exception;
	UiElementProxy elementBasedOnImage(String imagePath) throws Exception;
	
	UiMultiElementProxy elementsWithId(String id) throws Exception;
	UiMultiElementProxy elementsWithName(String name) throws Exception;
	UiMultiElementProxy elementsWithClass(String klass) throws Exception;
	UiMultiElementProxy elementsWithCss(String cssSelector) throws Exception;
	UiMultiElementProxy elementsWithTagName(String string) throws Exception;
	UiMultiElementProxy elementsWithLinkText(String text) throws Exception;
	UiMultiElementProxy elementsWithPartialLinkText(String textContent) throws Exception;
	UiMultiElementProxy elementsWithXPath(String xpath) throws Exception;
	UiMultiElementProxy elementsWithXText(String text) throws Exception;
	UiMultiElementProxy elementsWithXPartialText(String textContent) throws Exception;
	UiMultiElementProxy elementsWithXValue(String value) throws Exception;
	UiMultiElementProxy elementsWithXTitle(String value) throws Exception;
	UiMultiElementProxy elementsWithXImageSource(String path) throws Exception;
	UiMultiElementProxy elementsOfXType(UiElementType type) throws Exception;
	UiMultiElementProxy elementsBasedOnImage(String imagePath) throws Exception;
}
