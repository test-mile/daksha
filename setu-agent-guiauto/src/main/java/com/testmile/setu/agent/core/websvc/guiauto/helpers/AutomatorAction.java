package com.testmile.setu.agent.core.websvc.guiauto.helpers;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.testmile.daksha.core.value.StringValue;
import com.testmile.daksha.tpi.batteries.container.Value;

class AutomatorAction extends GuiAutoAction{
	private AutomatorActionType action;
	
	public AutomatorAction(String jsonActionStr) {
		super(jsonActionStr);
		this.action = AutomatorActionType.valueOf(this.getActionTypeStr());
	}

	public AutomatorActionType getActionType() {
		return action;
	}

}