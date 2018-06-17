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
package daksha.core.leaping.loader;

import java.util.HashMap;
import java.util.Map;

import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.tpi.leaping.loader.PageDefLoader;

public enum PageDefRepository{
	INSTANCE;

	private Map<String, PageDefinition> pageDefMap =  new HashMap<String, PageDefinition>();

	public synchronized boolean isPageDefLoaded(String name){
		return pageDefMap.containsKey(name);
	}

	public boolean hasPageDef(String name) {
		return pageDefMap.containsKey(name.toLowerCase());
	}
	
	public PageDefinition loadPageDef(String page, PageDefLoader loader) throws Exception{
		if(!hasPageDef(page)){
			loader.load();
			this.pageDefMap.put(page.toLowerCase(), loader.getPageDef());
		}
		return pageDefMap.get(page.toLowerCase());
	}
	

	public synchronized PageDefinition getPageDef(String page) throws Exception{
		return pageDefMap.get(page.toLowerCase());
	}

}
