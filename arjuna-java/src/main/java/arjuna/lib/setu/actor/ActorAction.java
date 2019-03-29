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

package arjuna.lib.setu.actor;

import java.util.Map;

import com.google.gson.Gson;

import arjuna.lib.core.value.AnyRefValue;
import arjuna.tpi.value.Value;

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
