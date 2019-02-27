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

package com.testmile.daksha.core.guiauto.radiogroup;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.setu.SetuGuiAutoSvcClient;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.setu.Response;
import com.testmile.daksha.tpi.guiauto.RadioGroup;

public class DefaultRadioGroup extends DefaultSetuObject implements RadioGroup {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/radiogroup/action";

	public DefaultRadioGroup(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	@Override
	public boolean hasValueSelected(String value) throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.HAS_VALUE_SELECTED);
		action.addArg("value", value);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public boolean hasIndexSelected(int index) throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.HAS_INDEX_SELECTED);
		action.addArg("index", index);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public String getFirstSelectedOptionValue() throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.GET_FIRST_SELECTED_OPTION_VALUE);
		Response response = this.setuClient.post(baseActionUri, action);
		return (String) response.getData().get("value");
	}

	@Override
	public void selectByValue(String value) throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.SELECT_BY_VALUE);
		action.addArg("value", value);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void selectByIndex(int index) throws Exception {
		RadioGroupAction action = new RadioGroupAction(this, RadioGroupActionType.SELECT_BY_INDEX);
		action.addArg("index", index);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public AppAutomator getAutomator() {
		return this.automator;
	}

}