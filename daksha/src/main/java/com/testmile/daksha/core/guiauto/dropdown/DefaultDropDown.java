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

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.setu.SetuGuiAutoSvcClient;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.setu.Response;
import com.testmile.daksha.tpi.guiauto.DropDown;

public class DefaultDropDown extends DefaultSetuObject implements DropDown {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/dropdown/action";

	public DefaultDropDown(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	@Override
	public boolean hasVisibleTextSelected(String text) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.HAS_VISIBLE_TEXT_SELECTED);
		action.addArg("text", text);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public boolean hasValueSelected(String value) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.HAS_VALUE_SELECTED);
		action.addArg("value", value);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public boolean hasIndexSelected(int index) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.HAS_INDEX_SELECTED);
		action.addArg("index", index);
		Response response = this.setuClient.post(baseActionUri, action);
		return (boolean) response.getData().get("checkResult");
	}

	@Override
	public String getFirstSelectedOptionText() throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.GET_FIRST_SELECTED_OPTION_TEXT);
		Response response = this.setuClient.post(baseActionUri, action);
		return (String) response.getData().get("text");
	}

	@Override
	public void selectByValue(String value) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.SELECT_BY_VALUE);
		action.addArg("value", value);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void selectByVisibleText(String text) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.SELECT_BY_VISIBLE_TEXT);
		action.addArg("text", text);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void selectByIndex(int index) throws Exception {
		DropDownAction action = new DropDownAction(this, DropDownActionType.SELECT_BY_INDEX);
		action.addArg("index", index);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public AppAutomator getAutomator() {
		return this.automator;
	}

}
