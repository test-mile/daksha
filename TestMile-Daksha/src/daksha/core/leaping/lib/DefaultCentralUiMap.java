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
package daksha.core.leaping.lib;

import java.util.HashMap;
import java.util.Map;

import daksha.core.leaping.interfaces.CentralPageMap;
import daksha.tpi.leaping.interfaces.PageMapper;

public class DefaultCentralUiMap implements CentralPageMap {

	private Map<String, HashMap<String, HashMap<String,String>>> rawMap =  new HashMap<String, HashMap<String, HashMap<String,String>>>();
	

	@Override
	public boolean isRawMapPresent(String uiFullName){
		return rawMap.containsKey(uiFullName);
	}
	
	@Override
	public Map<String, HashMap<String,String>> populateRawPageMap(String uiFullName, PageMapper mapper) throws Exception{
		if(!rawMap.containsKey(uiFullName)){
			Map<String, HashMap<String,String>> pMap = mapper.getPageMap();
			rawMap.put(uiFullName, (HashMap<String, HashMap<String,String>>) pMap);
		}
		return rawMap.get(uiFullName);
	}
	

	@Override
	public Map<String, HashMap<String,String>> getRawMap(String uiFullName) throws Exception{
		return rawMap.get(uiFullName);
	}


	@Override
	public Map<String, HashMap<String, HashMap<String,String>>> getRawMap() {
		return this.rawMap;
	}
	
}
