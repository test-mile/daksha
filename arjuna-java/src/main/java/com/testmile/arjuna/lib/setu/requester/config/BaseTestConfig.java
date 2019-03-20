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

package com.testmile.arjuna.lib.setu.requester.config;

import com.testmile.arjuna.lib.enums.SetuOption;
import com.testmile.arjuna.lib.setu.requester.connector.BaseSetuObject;
import com.testmile.arjuna.lib.setu.requester.connector.SetuArg;
import com.testmile.arjuna.lib.setu.requester.connector.SetuResponse;
import com.testmile.arjuna.lib.setu.requester.testsession.TestSession;
import com.testmile.arjuna.lib.state.ArjunaSingleton;
import com.testmile.arjuna.tpi.value.Value;

public class BaseTestConfig extends BaseSetuObject implements SetuTestConfig {
	private String name;
	private TestSession session;

	public BaseTestConfig(TestSession testSession, String name, String setuId) {
		this.session = testSession;
		this.name = name;
		
		this.setSetuId(setuId);
		this.setSelfSetuIdArg("configSetuId");
		this.setTestSessionSetuIdArg(testSession.getSetuId());
	}

	@Override
	public TestSession getTestSession() {
		return this.session;
	}
	
	private Value fetchConfOptionValue(SetuActionType actionType, String option) throws Exception {
		SetuResponse response = this.sendRequest(
				actionType,
				SetuArg.arg("option", option)
		);
		return response.getValue();	
	}
	
	public Value getSetuOptionValue(String option) throws Exception{
		return this.fetchConfOptionValue(
				SetuActionType.CONFIGURATOR_GET_SETU_OPTION_VALUE,
				ArjunaSingleton.INSTANCE.normalizeSetuOption(option).toString()
		);
	}	
	
	public Value getSetuOptionValue(SetuOption option) throws Exception{
		return this.fetchConfOptionValue(
				SetuActionType.CONFIGURATOR_GET_SETU_OPTION_VALUE,
				option.toString()
		);
	}
	
	public Value getUserOptionValue(String option) throws Exception{
		return this.fetchConfOptionValue(
				SetuActionType.CONFIGURATOR_GET_USER_OPTION_VALUE,
				ArjunaSingleton.INSTANCE.normalizeUserOption(option)
		);
	}

	@Override
	public String getName() {
		return this.name;
	}
}
