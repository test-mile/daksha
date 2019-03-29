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

import java.util.Map;

import arjuna.tpi.value.Value;

public interface SetuResponse {

	ResponseCode getResult();

	String getMessage();

	String getTrace();

	Map<String, Object> getData();
	
	Value getValueForKey(String keyName) throws Exception;
	
	Value getValue() throws Exception;
	
	String getValueForValueAttr() throws Exception;
	
	String getValueForText() throws Exception;
	
	boolean getValueForCheckResult() throws Exception;

	String getValueForElementSetuId() throws Exception;

	String getValueForTestSessionSetuId() throws Exception;
	
	String getValueForConfigSetuId() throws Exception;
	
	String getValueForGuiAutomatorSetuId() throws Exception;

	String getGuiSetuId() throws Exception;

	String getDataSourceSetuId() throws Exception;

}