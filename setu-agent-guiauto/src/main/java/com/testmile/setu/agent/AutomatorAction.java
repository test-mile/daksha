package com.testmile.setu.agent;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.testmile.daksha.core.value.StringValue;
import com.testmile.daksha.tpi.batteries.container.Value;

class AutomatorAction{
	private static JsonParser parser = new JsonParser();
	private static Gson gson = new Gson();
	private AutomatorActionType action;
	private Map<String, Value> args;
	
	public AutomatorAction(String jsonActionStr) {
		JsonObject o = parser.parse(jsonActionStr).getAsJsonObject();
		setAction(AutomatorActionType.valueOf(o.get("action").getAsString().toUpperCase()));
		String rawArgsStr = o.get("args").toString();
		
		args = new HashMap<String, Value>();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String, String> config = gson.fromJson(rawArgsStr, type); // contains the whole reviews list
		for (String key: config.keySet()) {
			args.put(key, new StringValue(config.get(key)));
		}
	}

	public AutomatorActionType getActionType() {
		return action;
	}

	private void setAction(AutomatorActionType action) {
		this.action = action;
	}
	
	public Map<String, Value> getArgs() {
		return args;
	}
}