package com.testmile.setu.agent.core.websvc.guiauto.helpers;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.testmile.daksha.core.value.StringValue;
import com.testmile.daksha.tpi.batteries.container.Value;

public abstract class GuiAutoAction {

	protected static JsonParser parser = new JsonParser();
	protected static Gson gson = new Gson();
	private String action;
	protected Map<String, Value> args;

	public GuiAutoAction(String jsonActionStr) {
		JsonObject o = parser.parse(jsonActionStr).getAsJsonObject();
		this.action = o.get("action").getAsString().toUpperCase();
		if(o.has("args")) {
			String rawArgsStr = o.get("args").toString();	
			args = new HashMap<String, Value>();
			Type type = new TypeToken<Map<String, String>>(){}.getType();
			Map<String, String> config = gson.fromJson(rawArgsStr, type); // contains the whole reviews list
			for (String key: config.keySet()) {
				args.put(key, new StringValue(config.get(key)));
			}
		}
	}
	
	protected String getActionTypeStr() {
		return action;
	}

	public Map<String, Value> getArgs() {
		return args;
	}

}