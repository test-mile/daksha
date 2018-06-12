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
package daksha.tpi.leaping.pageobject;

import java.util.HashMap;
import java.util.Map;

import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.Daksha;
import daksha.core.leaping.factories.PageMapperFactory;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.interfaces.App;
import daksha.tpi.leaping.interfaces.Page;
import daksha.tpi.leaping.interfaces.PageDefLoader;
import daksha.tpi.leaping.interfaces.GuiAutomator;

public class BaseApp implements App{
	private String name = null;
	private Map<String, Page> pageMap = new HashMap<String, Page>();

	public BaseApp(String templateName){
		this.setName(templateName);
	}

	public void registerPage(String uiLabel, GuiAutomator uiDriver, String mapPath) throws Exception {
		if (uiDriver != null){
			Page ui = new BasePage(uiLabel, uiDriver);
			ui.populate(PageMapperFactory.getFileMapper(mapPath));
			getPageMap().put(uiLabel.toUpperCase(), ui); // to provide case insensitive access	
		} else {
			throwNullAutomatorException("registerUi", UiAutomationContext.GENERIC);
		}		
	}
	
	public void registerPage(String uiLabel, GuiAutomator uiDriver, PageDefLoader mapper) throws Exception {
		if (uiDriver != null){
			Page ui = new BasePage(uiLabel, uiDriver);
			ui.populate(mapper);
			this.getPageMap().put(uiLabel.toUpperCase(), ui); // to provide case insensitive access	
		} else {
			throwNullAutomatorException("registerUi", UiAutomationContext.GENERIC);
		}		
	}


	protected void throwNullAutomatorException(String method, UiAutomationContext context) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
			this.getName(),
			method,
			Daksha.problem.COMPOSITE_PAGE_NULL_AUTOMATOR,
			Batteries.getProblemText(Daksha.problem.COMPOSITE_PAGE_NULL_AUTOMATOR, Daksha.getAutomationContextName(context) )
		);
	}
	
	protected Page throwUndefinedUiException(String method, String uiLabel) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
			this.getName(),
			method,
			Daksha.problem.COMPOSITE_PAGE_NONEXISTING_LABEL,
			Batteries.getProblemText(Daksha.problem.COMPOSITE_PAGE_NONEXISTING_LABEL, uiLabel, this.getName())
		);
	}
	
	protected Page throwNullUiException(String method) throws Exception{
		throw new Problem(
				Batteries.getComponentName("UI_AUTOMATOR"),
			this.getName(),
			method,
			Daksha.problem.COMPOSITE_PAGE_NULL_LABEL,
			Batteries.getProblemText(Daksha.problem.COMPOSITE_PAGE_NULL_LABEL, this.getName() )
		);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}



	@Override
	public void addElement(String elementName, Map<String,String> elementProps) throws Exception {
		for (String name: getPageMap().keySet()){
			getPageMap().get(name.toUpperCase()).addElement(elementName, elementProps);
		}
	}

	@Override
	public void addElement(String uiLabel, String elementName, Map<String, String> elemMap) throws Exception {
		if (uiLabel == null){
			throwNullUiException("addElement");
		}
		
		if (getPageMap().containsKey(uiLabel.toUpperCase())){
			getPageMap().get(uiLabel.toUpperCase()).addElement(elementName, elemMap);
		} else{
			throwUndefinedUiException("addElement", uiLabel);
		}
	}
	
	public Page page(String uiName) throws Exception {
		if (uiName != null){
			if (getPageMap().containsKey(uiName.toUpperCase())){
				return getPageMap().get(uiName.toUpperCase());
			} else{
				return throwUndefinedUiException("ui", uiName);
			}
		} else {
			return throwNullUiException("ui");
		}
	}

	protected Map<String, Page> getPageMap() {
		return pageMap;
	}

	protected void setPageMap(Map<String, Page> pageMap) {
		this.pageMap = pageMap;
	}
	
}

//test