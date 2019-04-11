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

package arjuna.lib.setu.core.requester.connector;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import arjuna.lib.core.value.AnyRefValue;
import arjuna.lib.httpclient.BasicRestClient;
import arjuna.lib.httpclient.SetuHttpException;
import arjuna.lib.setu.core.requester.config.SetuActionType;
import arjuna.tpi.Arjuna;
import arjuna.tpi.value.Value;

public class BaseSetuObject implements SetuManagedObject {
	private String setuId;
	private static SetuRequester setuClient = new DefaultSetuRequester();
	private Map<String, Object> coreArgs = new HashMap<String, Object>();

	@Override
	public String getSetuId() {
		return this.setuId;
	}
	
	protected void setSetuId(String idValue) {
		this.setuId = idValue;
	}
	
	protected void setTestSessionSetuIdArg(String id) {
		this.coreArgs.put("testSessionSetuId", id);
	}
	
	protected void setAutomatorSetuIdArg(String id) {
		this.coreArgs.put("automatorSetuId", id);
	}
	
	protected void setGuiSetuIdArg(String id) {
		this.coreArgs.put("guiSetuId", id);
	}
	
	protected void setSelfSetuIdArg(String idName) {
		this.coreArgs.put(idName, this.setuId);
	}
	
	protected void addArgs(SetuArg... args) {
		for(SetuArg arg: args) {
			coreArgs.put(arg.getName(), arg.getObject());
		}		
	}
	
	private void prepareRequestWithCoreArgs(SetuRequest request) {
		for(String name: coreArgs.keySet()) {
			request.addArg(name, coreArgs.get(name));
		}		
	}
	
	private void prepareRequest(SetuRequest request, SetuArg...args) {
		for(SetuArg arg: args) {
			request.addArg(arg.getName(), arg.getObject());
		}		
	}
	
	protected SetuRequest createRequest(SetuActionType actionType, SetuArg... args) {
		SetuRequest request = new DefaultSetuRequest(actionType);
		this.prepareRequestWithCoreArgs(request);
		this.prepareRequest(request, args);
		return request;
	}
	
	protected SetuResponse sendRequest(SetuActionType actionType) throws Exception {
		SetuRequest request = this.createRequest(actionType);
		return setuClient.post(request);
	}
	
	protected SetuResponse sendRequest(SetuActionType actionType, SetuArg... args) throws Exception {
		SetuRequest request = this.createRequest(actionType, args);
		return setuClient.post(request);
	}

}

interface SetuRequester {

	SetuResponse post(SetuRequest action) throws Exception;

}

enum ResponseCode {
	SUCCESS,
	ERROR
}

class DefaultSetuRequester implements SetuRequester {
	private String setuUrl = "http://localhost:9000";
	private String baseUri = "/setu";
	protected BasicRestClient restClient;

	public DefaultSetuRequester() {
		this.restClient = new BasicRestClient(setuUrl);
	}

	@Override
	public SetuResponse post(SetuRequest action) throws Exception {
		SetuResponse setuResponse = null;
		String response = null;
		try {
			response = this.restClient.post(baseUri, action.asJsonString());
			setuResponse = DefaultSetuResponse.fromJsonStr(response);
		} catch (SetuHttpException e) {
			Arjuna.getLogger().error("---------- Setu Error Response ----------------------");
			Arjuna.getLogger().error("Got Http Error: " + e.getStatusCode());
			setuResponse = DefaultSetuResponse.fromJsonStr(e.getResponse());
			Arjuna.getLogger().error("Error: " +  setuResponse.getMessage());
			Arjuna.getLogger().error("---------- Setu Error Trace -------------------------");
			Arjuna.getLogger().error(setuResponse.getTrace());
			Arjuna.getLogger().error("-----------------------------------------------------");
			throw e;
		}
		return setuResponse;
	}
}

interface SetuRequest {
	void addArg(String name, Object value);
	String asJsonString();
}

class DefaultSetuRequest implements SetuRequest {
	
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private RequestBody actionRequest;
	
	public DefaultSetuRequest(SetuActionType actionType) {
		this.actionRequest = new RequestBody();
		this.actionRequest.setAction(actionType.toString());
	}
	
	public DefaultSetuRequest(String testSessionSetuId) {
		this.actionRequest = new RequestBody();
		this.addArg("testSessionSetuId", testSessionSetuId);
	}

	public void addAllArgs(Map<String, Object> args) {
		for (String argName: args.keySet()) {
			this.addArg(argName, args.get(argName));
		}
	}
	
	public void addArg(String name, Object value) {
		this.getActionRequest().addArg(name, value);
	}
	
	@Override
	public String asJsonString() {
		return gson.toJson(this.getActionRequest(), RequestBody.class);
	}

	protected RequestBody getActionRequest() {
		return actionRequest;
	}
	
	private class RequestBody {
		private String action;
//		private String setuId;
		private Map<String,Object> args = null;
		
		public void addArg(String name, Object value) {
			if (this.args == null) {
				this.args = new HashMap<String, Object>();
			}
			this.args.put(name, value);
		}
		
		public void setAction(String action) {
			this.action = action;
		}
//
//		public String getSetuId() {
//			return setuId;
//		}
//
//		public void setSetuId(String setuId) {
//			this.setuId = setuId;
//		}

	}	
}

class DefaultSetuResponse implements SetuResponse {
	private static final Gson gson = new Gson();
	private ResponseCode result;
	private String emessage;
	private String etrace;
	private Map<String,Object> responseData = null;
	
	@Override
	public ResponseCode getResult() {
		return result;
	}
	
	public void setResult(ResponseCode code) {
		this.result = code;
	}

	@Override
	public String getMessage() {
		return emessage;
	}
	public void setMessage(String message) {
		this.emessage = message;
	}

	@Override
	public String getTrace() {
		return etrace;
	}
	
	public void setTrace(String trace) {
		this.etrace = trace;
	}
	
	@Override
	public Map<String,Object> getData(){
		return this.responseData;
	}
	
	public Value getValueForKey(String keyName) {
		return new AnyRefValue(this.getData().get(keyName));
	}
	
	public Value getValue() {
		return getValueForKey("value");
	}
	
	public String getValueForValueAttr() throws Exception {
		return getValueForKey("value").asString();
	}
	
	public String getValueForText() throws Exception {
		return getValueForKey("text").asString();
	}
	
	public boolean getValueForCheckResult() throws Exception {
		return getValueForKey("checkResult").asBoolean();
	}
	
	@Override
	public String getValueForElementSetuId() throws Exception {
		return getValueForKey("elementSetuId").asString();
	}
	
	@Override
	public String getValueForTestSessionSetuId() throws Exception {
		return getValueForKey("testSessionSetuId").asString();
	}
	
	@Override
	public String getValueForConfigSetuId() throws Exception {
		return getValueForKey("configSetuId").asString();
	}
	
	@Override
	public String getValueForGuiAutomatorSetuId() throws Exception {
		return getValueForKey("automatorSetuId").asString();
	}
	
	@Override
	public String getGuiSetuId() throws Exception {
		return getValueForKey("guiSetuId").asString();
	}
	
	@Override
	public String getDataSourceSetuId() throws Exception {
		return getValueForKey("dataSourceSetuId").asString();
	}
	
	public void addDataItem(String name, Object value) {
		if (this.responseData == null) {
			this.responseData = new HashMap<String, Object>();
		}
		this.responseData.put(name, value);
	}
	
	public static SetuResponse fromJsonStr(String json) {
		return gson.fromJson(json, DefaultSetuResponse.class);
	}
}
