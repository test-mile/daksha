/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
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

package com.testmile.setu.actor;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.testmile.daksha.core.value.AnyRefValue;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.GuiAutomatorName;
import com.testmile.trishanku.tpi.enums.OSType;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.Value;

public class SetuAgentConfig {
	private Map<SetuOption, Value> options = new HashMap<SetuOption, Value>();
	private Map<String, Value> userOptions = new HashMap<String, Value>();
	
	public SetuAgentConfig(String strJsonConfig) throws Exception {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject jObj = parser.parse(strJsonConfig).getAsJsonObject();
		
		JsonElement sOptions = jObj.get("setuOptions").getAsJsonObject();
		JsonElement uOptions = jObj.get("userOptions").getAsJsonObject();
		
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		
		Map<String, Object> sOptionDict = gson.fromJson(sOptions, type);
		for (String key: sOptionDict.keySet()) {
			this.options.put(SetuOption.valueOf(key.toUpperCase()), new AnyRefValue((String) sOptionDict.get(key)));
		}
		
		Map<String, Object> uOptionDict = gson.fromJson(uOptions, type);
		for (String key: uOptionDict.keySet()) {
			this.userOptions.put(key.toUpperCase(), new AnyRefValue((String) sOptionDict.get(key)));
		}
	}
	
	public Value value(SetuOption option) {
		return this.options.get(option);
	}

	public Browser getBrowser() throws Exception {
		return value(SetuOption.BROWSER_NAME).asEnum(Browser.class);
	}

	public int getMaxWaitTime() throws Exception {
		return value(SetuOption.GUIAUTO_MAX_WAIT).asInt();
	}

	public GuiAutomationContext getAutomationContext() throws Exception {
		return value(SetuOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
	}

	public OSType getOSType() throws Exception {
		return value(SetuOption.TESTRUN_TARGET_PLATFORM).asEnum(OSType.class);
	}

	public GuiAutomatorName getAutomatorName() throws Exception {
		return value(SetuOption.GUIAUTO_AUTOMATOR_NAME).asEnum(GuiAutomatorName.class);
	}

}