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

package com.testmile.daksha.core.guiauto.dropdown;

import com.testmile.daksha.core.guiauto.alert.AlertActionType;
import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.setu.GenericElement;
import com.testmile.daksha.core.guiauto.setu.SelectableMultiElement;
import com.testmile.daksha.core.guiauto.setu.SeletableMEActionType;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.setu.Response;
import com.testmile.daksha.core.setu.SetuRequest;
import com.testmile.daksha.core.setu.SetuSvcRequester;
import com.testmile.daksha.tpi.guiauto.DropDown;

public class DefaultDropDown extends SelectableMultiElement implements DropDown {

	public DefaultDropDown(AppAutomator automator, String setuId) {
		super(automator, setuId, "/dropdown/action");
	}
	
	public String getFirstSelectedOptionText() throws Exception {
		Response response = this.takeAction(DropDownActionType.GET_FIRST_SELECTED_OPTION_TEXT.toString());
		return (String) response.getData().get("text");
	}

	@Override
	public boolean hasVisibleTextSelected(String text) throws Exception {
		SetuRequest request = this.createRequest(DropDownActionType.HAS_VISIBLE_TEXT_SELECTED.toString());
		request.addArg("text", text);
		Response response = this.takeAction(request);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public void selectByVisibleText(String text) throws Exception {
		SetuRequest request = this.createRequest(DropDownActionType.SELECT_BY_VISIBLE_TEXT.toString());
		request.addArg("text", text);
		this.takeAction(request);
	}
}
