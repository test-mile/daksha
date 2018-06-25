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
package daksha.core.cleanup.loader;

import java.util.HashMap;
import java.util.Map;

import daksha.tpi.cleanup.constructor.loader.UiDefLoader;

public enum UiDefRepository{
	INSTANCE;

	private Map<String, UiDefinition> uiDefMap =  new HashMap<String, UiDefinition>();

	public synchronized boolean isUiDefLoader(String name){
		return uiDefMap.containsKey(name);
	}

	public synchronized boolean hasUiDef(String name) {
		return uiDefMap.containsKey(name.toLowerCase());
	}
	
	public synchronized UiDefinition loadUiDef(String name, UiDefLoader loader) throws Exception{
		if(!hasUiDef(name)){
			loader.load();
			this.uiDefMap.put(name.toLowerCase(), loader.getUiDef());
		}
		return uiDefMap.get(name.toLowerCase());
	}
	

	public synchronized UiDefinition getUiDef(String name) throws Exception{
		return uiDefMap.get(name.toLowerCase());
	}

}
