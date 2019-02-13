package com.testmile.setu.agent.core.websvc.guiauto.helpers;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.testmile.daksha.core.value.StringValue;
import com.testmile.daksha.tpi.batteries.container.Value;

class ElementAction extends GuiAutoAction{
	private ElementActionType action;
	
	public ElementAction(String jsonActionStr) {
		super(jsonActionStr);
		this.action = ElementActionType.valueOf(this.getActionTypeStr());
	}

	public ElementActionType getActionType() {
		return action;
	}
	
	public boolean isInstanceAction() throws Exception {
		if (this.getArgs().containsKey("isInstanceAction")){
			return this.getArgs().get("isInstanceAction").asBoolean();
		} else {
			return false;
		}
	}

	public int getInstanceIndex() throws Exception {
		if (!isInstanceAction()) {
			throw new Exception("This is not an instnce action.");
		}
		return this.getArgs().get("instanceIndex").asInt();
	}
}