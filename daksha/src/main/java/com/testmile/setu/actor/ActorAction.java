package com.testmile.setu.actor;

import java.util.Map;

import com.google.gson.Gson;
import com.testmile.trishanku.tpi.value.AnyRefValue;
import com.testmile.trishanku.tpi.value.Value;

public class ActorAction {
	private static final Gson gson = new Gson();
	private String action;
	private Map<String,Object> args = null;
	private static Value notSetValue = new AnyRefValue("NOT_SET");
	
	private ActorAction() {
	}
	
	public static ActorAction fromJsonStr(String json) {
		return gson.fromJson(json, ActorAction.class);
	}
	
	public String getActionString(){
		return this.action.trim().toUpperCase();
	}
	
	public Map<String,Object> getArgs(){
		return this.args;
	}
	
	public Value value(String key) {
		return new AnyRefValue(this.getArgs().get(key));
	}
	
	public String strValue(String key) throws Exception {
		return value(key).asString();
	}
	
	public int intValue(String key) throws Exception {
		return value(key).asInt();
	}
	
	public Value getOptionalArgValue(String key) {
		if (this.getArgs().containsKey(key)){
			return this.value(key);
		} else {
			return notSetValue;
		}
	}
	
	public boolean isInstanceAction() throws Exception {
		Value val = this.getOptionalArgValue("isInstanceAction");
		if (val.isNotSet()) {
			return false;
		} else {
			return val.asBoolean();
		}
	}

	public int getInstanceIndex() throws Exception {
		if (!isInstanceAction()) {
			throw new Exception("This is not an instnce action.");
		}
		return this.value("instanceIndex").asInt();
	}

	public String getAutomatorId() {
		return (String) getArgs().get("automatorSetuId");
	}
	
	public String getAutomatorName() {
		return (String) getArgs().get("automatorName");
	}

	public String getElementId() {
		return (String) getArgs().get("elementSetuId");
	}
}
