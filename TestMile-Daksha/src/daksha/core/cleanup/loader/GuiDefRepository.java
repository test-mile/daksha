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

import daksha.tpi.cleanup.constructor.loader.GuiDefLoader;

public enum GuiDefRepository{
	INSTANCE;

	private Map<String, GuiDefinition> guiDefMap =  new HashMap<String, GuiDefinition>();

	public synchronized boolean isGuiDefLoader(String name){
		return guiDefMap.containsKey(name);
	}

	public synchronized boolean hasGuiDef(String name) {
		return guiDefMap.containsKey(name.toLowerCase());
	}
	
	public synchronized GuiDefinition loadGuiDef(String name, GuiDefLoader loader) throws Exception{
		if(!hasGuiDef(name)){
			loader.load();
			this.guiDefMap.put(name.toLowerCase(), loader.getGuiDef());
		}
		return guiDefMap.get(name.toLowerCase());
	}
	

	public synchronized GuiDefinition getGuiDef(String name) throws Exception{
		return guiDefMap.get(name.toLowerCase());
	}

}
