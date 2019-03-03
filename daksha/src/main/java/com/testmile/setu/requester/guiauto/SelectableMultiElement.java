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

package com.testmile.setu.requester.guiauto;

import com.testmile.setu.requester.Response;
import com.testmile.setu.requester.SetuRequest;
import com.testmile.setu.requester.guiauto.automator.AppAutomator;

public class SelectableMultiElement extends GenericElement {

	public SelectableMultiElement(AppAutomator automator, String setuId, String baseUri) {
		super(automator, setuId, baseUri);
	}

	public boolean hasValueSelected(String value) throws Exception {
		SetuRequest request = this.createRequest(SeletableMEActionType.HAS_VALUE_SELECTED.toString());
		request.addArg("value", value);
		Response response = this.takeAction(request);
		return (boolean) response.getData().get("checkResult");
	}

	public boolean hasIndexSelected(int index) throws Exception {
		SetuRequest request = this.createRequest(SeletableMEActionType.HAS_INDEX_SELECTED.toString());
		request.addArg("index", index);
		Response response = this.takeAction(request);
		return (boolean) response.getData().get("checkResult");
	}

	public void selectByValue(String value) throws Exception {
		SetuRequest request = this.createRequest(SeletableMEActionType.SELECT_BY_VALUE.toString());
		request.addArg("value", value);
		this.takeAction(request);
	}

	public void selectByIndex(int index) throws Exception {
		SetuRequest request = this.createRequest(SeletableMEActionType.SELECT_BY_INDEX.toString());
		request.addArg("index", index);
		this.takeAction(request);
	}

	public String getFirstSelectedOptionValue() throws Exception {
		Response response = this.takeAction(SeletableMEActionType.GET_FIRST_SELECTED_OPTION_VALUE.toString());
		return (String) response.getData().get("text");
	}
}
