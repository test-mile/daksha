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

package com.testmile.setu.requester.testsession;

import java.util.Map;

import com.testmile.daksha.Daksha;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.setu.requester.DefaultSetuObject;
import com.testmile.setu.requester.Response;
import com.testmile.setu.requester.SetuSvcRequester;
import com.testmile.setu.requester.config.DefaultTestConfig;
import com.testmile.setu.requester.databroker.DataRecordType;
import com.testmile.trishanku.core.value.AnyRefValue;
import com.testmile.trishanku.tpi.value.Value;

public class DefaultTestSession extends DefaultSetuObject implements TestSession {
	private SetuSvcRequester setuRequester;
	private String baseActionUri = "/action";

	public DefaultTestSession() {
		setSetuRequester(new SetuTestSessionRequester());
	}
	
	@Override
	public SetuSvcRequester getSetuRequester() {
		return this.setuRequester;
	}

	@Override
	public TestConfig init(String rootDir) throws Exception {
		TestSessionAction action = new TestSessionAction(this, TestSessionActionType.INIT);
		action.addArg("rootDir", rootDir);
		Response response = this.setuRequester.post("/init", action);
		this.setSetuId((String) response.getData().get("testSessionSetuId"));
		
		TestSessionAction actionProjectConf = new TestSessionAction(this, TestSessionActionType.LOAD_PROJECT_CONF);
		Response confResponse = this.setuRequester.post(baseActionUri, actionProjectConf);		
		TestConfig config = new DefaultTestConfig(this, Daksha.DEF_CONF_NAME, (String) confResponse.getData().get("configSetuId"));
		config.setTestSessionSetuId(this.getSetuId());
		return config;
	}

	@Override
	public void finish() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setSetuRequester(SetuSvcRequester setuRequester) {
		this.setuRequester = setuRequester;
	}
	
	private String registerConfig(boolean hasParent, String parentConfigId, Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception {
		TestSessionAction action = new TestSessionAction(this, TestSessionActionType.REGISTER_CONFIG);
		action.addArg("hasParent", hasParent);
		action.addArg("parentConfigId", parentConfigId);
		action.addArg("setuOptions", setuOptions);
		action.addArg("userOptions", userOptions);
		Response response = this.setuRequester.post(baseActionUri, action);
		return (String) response.getData().get("configSetuId");
	}
	
	@Override
	public String registerConfig(Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception {
		return registerConfig(false, null, setuOptions, userOptions);
	}
	
	@Override
	public String registerConfig(String parentConfigId, Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception {
		return registerConfig(true, parentConfigId, setuOptions, userOptions);
	}
	
	@Override
	public String createFileDataSource(DataRecordType recordType, String fileName, Map<String, Object> argPairs) throws Exception {
		TestSessionAction action = new TestSessionAction(this, TestSessionActionType.CREATE_DATA_SOURCE);
		action.addArg("sourceType", "FILE");
		action.addArg("fileName", fileName);
		action.addArg("recordType", recordType);
		for(String arg: argPairs.keySet()) {
			action.addArg(arg, argPairs.get(arg));	
		}
		Response response = this.setuRequester.post(baseActionUri, action);
		return (String) response.getData().get("dataSourceSetuId");
	}


}
