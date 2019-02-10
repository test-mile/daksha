package com.testmile.daksha.tpi.sysauto.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

	public JsonObject strToJsonObject(String inStr){
		JsonElement root = (new JsonParser()).parse(inStr);
		return root.getAsJsonObject();		
	}
}
