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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.testmile.daksha.Daksha;
import com.testmile.setu.requester.config.DefaultTestConfig;
import com.testmile.setu.requester.config.SetuActionType;
import com.testmile.setu.requester.config.TestConfig;
import com.testmile.setu.requester.connector.BaseSetuObject;
import com.testmile.setu.requester.connector.SetuArg;
import com.testmile.setu.requester.connector.SetuResponse;
import com.testmile.setu.requester.databroker.DataRecordType;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;
import com.testmile.trishanku.tpi.value.Value;

public class DefaultTestSession extends BaseSetuObject implements TestSession {

	@Override
	public TestConfig init(String rootDir) throws Exception {
		SetuResponse response = this.sendRequest(SetuActionType.TESTSESSION_INIT, SetuArg.arg("rootDir", rootDir));
		this.setSetuId(response.getValueForTestSessionSetuId());
		this.setSelfSetuIdArg("testSessionSetuId");
		TestConfig config = new DefaultTestConfig(this, Daksha.DEF_CONF_NAME, response.getValueForConfigSetuId());
		this.setSetuId(response.getValueForTestSessionSetuId());
		return config;
	}

	@Override
	public void finish() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private String registerConfig(boolean hasParent, String parentConfigId, Map<String, String> setuOptions, Map<String, Value> userOptions) throws Exception {
		SetuResponse response = this.sendRequest(
				SetuActionType.TESTSESSION_REGISTER_CONFIG, 
				SetuArg.arg("hasParent", hasParent),
				SetuArg.arg("parentConfigId", parentConfigId),
				SetuArg.arg("setuOptions", setuOptions),
				SetuArg.arg("userOptions", userOptions)
		);
		return response.getValueForConfigSetuId();
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
	public String createFileDataSource(DataRecordType recordType, String fileName, List<SetuArg> argPairs) throws Exception {
		SetuResponse response = this.sendRequest(
				SetuActionType.TESTSESSION_CREATE_FILE_DATA_SOURCE, 
				concat(
						new SetuArg[] {SetuArg.arg("fileName", fileName), SetuArg.arg("recordType", recordType)}, 
						argPairs.toArray(new SetuArg[] {})
				)
		);
		return response.getDataSourceSetuId();
	}
	
	@Override
	public String createGui(GuiAutomator automator, SetuArg... args) throws Exception {
		SetuResponse response = this.sendRequest(
				SetuActionType.TESTSESSION_CREATE_GUI, 
				concat (args, 
						new SetuArg[] {SetuArg.arg("automatorSetuId", automator.getSetuId())}
				)
		);
		return response.getGuiSetuId();
	}
	
	// From Joachim Sauer's Stackoverflow answer: https://stackoverflow.com/a/784842
	public <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
	}


}
