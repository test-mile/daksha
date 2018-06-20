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

import daksha.core.leaping.automator.proxy.GuiAutomatorProxy;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;

public class DefaultApp extends BasePage implements App{
	private Map<String, Page> pageMap = new HashMap<String, Page>();

	public DefaultApp(String name, GuiAutomatorProxy automator, String homeMapPath) throws Exception{
		super(name, automator, homeMapPath);
	}

	public void registerPage(String name, String mapPath) throws Exception {
		Page page = new BasePage(name, this.getGuiAutomator(), mapPath);
		getPageMap().put(name.toLowerCase(), page);		
	}
	
	protected Page throwUndefinedUiException(String method, String uiLabel) throws Exception{
		throw new Problem(
			"Page Object Component",
			this.getName(),
			method,
			ErrorType.COMPOSITE_PAGE_NONEXISTING_LABEL,
			String.format(ErrorType.COMPOSITE_PAGE_NONEXISTING_LABEL, uiLabel, this.getName())
		);
	}
	
	protected Page throwNullUiException(String method) throws Exception{
		throw new Problem(
			"Page Object Component",
			this.getName(),
			method,
			ErrorType.COMPOSITE_PAGE_NULL_LABEL,
			String.format(ErrorType.COMPOSITE_PAGE_NULL_LABEL, this.getName() )
		);
	}
	
	public Page page(String uiName) throws Exception {
		if (uiName != null){
			if (getPageMap().containsKey(uiName.toLowerCase())){
				return getPageMap().get(uiName.toLowerCase());
			} else{
				return throwUndefinedUiException("page", uiName);
			}
		} else {
			return throwNullUiException("page");
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