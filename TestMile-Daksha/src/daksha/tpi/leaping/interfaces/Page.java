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
package daksha.tpi.leaping.interfaces;

import java.util.Map;

import daksha.core.leaping.interfaces.BaseUiDriver;
import daksha.core.leaping.interfaces.CentralPageDefMap;
import daksha.core.leaping.interfaces.appactions.BrowserActionHandler;
import daksha.core.leaping.interfaces.appactions.ElementCreationHandler;
import daksha.core.leaping.interfaces.appactions.ImageComparator;
import daksha.core.leaping.interfaces.appactions.NativeWindowActionHandler;
import daksha.tpi.leaping.enums.UiAutomationContext;

public interface Page extends BaseUiDriver, ElementCreationHandler, 
ImageComparator, NativeWindowActionHandler, BrowserActionHandler{
	CentralPageDefMap getUiMap();
	
	String getName();
	void setName(String name);
	
	void populate(PageDefLoader mapper) throws Exception;
	void addElement(String uiElementName, Map<String, String> elemMap) throws Exception;
//	void processUiProperties(String elementName, Map<String,String> properties) throws Exception;
	
//	String getImagesDirectory();
	
	UiAutomationContext getContext() throws Exception;
	boolean isAutomatorPresent() throws Exception;
	
	Page getParent() throws Exception;;
	void setParent(Page page) throws Exception;
	
	String getLabel() throws Exception;
	void setLabel(String label)  throws Exception;
	
	//IDefaultElement declareElement(IElementMetaData elementMetaData, ElementLoaderType loaderType) throws Exception;
	GuiElement element(String name) throws Exception;
	GuiAutomator getUiDriver() throws Exception;
	
	void setUiDriver(GuiAutomator automator) throws Exception;
	void setContext(UiAutomationContext context) throws Exception;
	
	public void switchToWebContext() throws Exception;
	public void switchToNativeContext() throws Exception;

	void processElementProperties(String elementName, Map<String, String> properties) throws Exception;
//	void processElementPropertiesForLabel(String uiName, String elementName, Map<String, String> properties)
//			throws Exception;
}
