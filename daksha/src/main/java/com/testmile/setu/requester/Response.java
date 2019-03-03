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

package com.testmile.setu.requester;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Response {
	private static Gson gson = new Gson();
	private ResponseCode result;
	private String emessage;
	private String etrace;
	private Map<String,Object> responseData = null;
	
	public ResponseCode getResult() {
		return result;
	}
	public void setResult(ResponseCode code) {
		this.result = code;
	}
	public String getMessage() {
		return emessage;
	}
	public void setMessage(String message) {
		this.emessage = message;
	}
	public String getTrace() {
		return etrace;
	}
	public void setTrace(String trace) {
		this.etrace = trace;
	}
	
	public Map<String,Object> getData(){
		return this.responseData;
	}
	
	public void addDataItem(String name, Object value) {
		if (this.responseData == null) {
			this.responseData = new HashMap<String, Object>();
		}
		this.responseData.put(name, value);
	}
	
	public static Response fromJsonStr(String json) {
		return gson.fromJson(json, Response.class);
	}

}
