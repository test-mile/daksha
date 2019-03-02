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

package com.testmile.daksha.core.testsession;

import java.util.Map;

import com.google.gson.Gson;
import com.testmile.daksha.Daksha;
import com.testmile.daksha.core.config.DefaultTestConfig;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.setu.Response;
import com.testmile.daksha.core.setu.SetuSvcRequester;
import com.testmile.daksha.core.value.AnyRefValue;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.trishanku.tpi.value.Value;

public class DefaultTestSession extends DefaultSetuObject implements TestSession {
	private SetuSvcRequester setuRequester;
	private String baseActionUri = "/action";

	public DefaultTestSession() {
		setSetuRequester(new SetuTestCycleRequester());
	}

	@Override
	public TestConfig init(String rootDir) throws Exception {
		TestSessionAction action = new TestSessionAction(this, TestSessionActionType.INIT);
		action.addArg("rootDir", rootDir);
		Response response = this.setuRequester.post("/init", action);
		this.setSetuId((String) response.getData().get("testSessionSetuId"));
		
		TestSessionAction actionProjectConf = new TestSessionAction(this, TestSessionActionType.LOAD_PROJECT_CONF);
		Response confResponse = this.setuRequester.post("/action", actionProjectConf);		
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
	
	@Override
	public String registerConfig(Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception {
		TestSessionAction action = new TestSessionAction(this, TestSessionActionType.REGISTER_CONFIG);
		action.addArg("setuOptions", setuOptions);
		action.addArg("userOptions", userOptions);
		Response response = this.setuRequester.post("/action", action);
		return (String) response.getData().get("configSetuId");
	}

	private Value fetchConfOptionValue(TestSessionActionType actionType, String configSetuId, String option) throws Exception {
		TestSessionAction action = new TestSessionAction(this, actionType);
		action.addArg("configSetuId", configSetuId);
		action.addArg("option", option);
		Response response = this.setuRequester.post("/action", action);
		return new AnyRefValue(response.getData().get("configSetuId"));		
	}
	
	@Override
	public Value getSetuOptionValue(String configSetuId, String option) throws Exception {
		return this.fetchConfOptionValue(TestSessionActionType.GET_SETU_OPTION_VALUE, configSetuId, option);
	}

	@Override
	public Value getUserOptionValue(String configSetuId, String option) throws Exception {
		return this.fetchConfOptionValue(TestSessionActionType.GET_USER_OPTION_VALUE, configSetuId, option);
	}
}
