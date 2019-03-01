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

package com.testmile.setu.agent.core.websvc.guiauto.helpers;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.testmile.daksha.core.value.StringValue;
import com.testmile.trishanku.tpi.value.Value;

public abstract class GuiAutoAction {

	protected static JsonParser parser = new JsonParser();
	protected static Gson gson = new Gson();
	private String action;
	protected Map<String, Value> args;

	public GuiAutoAction(String jsonActionStr) {
		JsonObject o = parser.parse(jsonActionStr).getAsJsonObject();
		this.action = o.get("action").getAsString().toUpperCase();
		
		String rawArgsStr = o.get("args").toString();	
		args = new HashMap<String, Value>();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String, String> config = gson.fromJson(rawArgsStr, type); // contains the whole reviews list
		for (String key: config.keySet()) {
			args.put(key, new StringValue(config.get(key)));
		}
	}
	
	protected String getActionTypeStr() {
		return action;
	}

	public Map<String, Value> getArgs() {
		return args;
	}

}