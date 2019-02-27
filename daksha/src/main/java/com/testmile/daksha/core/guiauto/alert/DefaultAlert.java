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

package com.testmile.daksha.core.guiauto.alert;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.setu.SetuGuiAutoSvcClient;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.setu.Response;
import com.testmile.daksha.tpi.guiauto.Alert;

public class DefaultAlert extends DefaultSetuObject implements Alert {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/alert/action";

	public DefaultAlert(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	@Override
	public void confirm() throws Exception {
		AlertAction action = new AlertAction(this, AlertActionType.CONFIRM);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void dismiss() throws Exception {
		AlertAction action = new AlertAction(this, AlertActionType.DISMISS);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public String getText() throws Exception {
		AlertAction action = new AlertAction(this, AlertActionType.GET_TEXT);
		Response response = this.setuClient.post(baseActionUri, action);
		return (String) response.getData().get("text");
	}

	@Override
	public void sendText(String text) throws Exception {
		AlertAction action = new AlertAction(this, AlertActionType.SEND_TEXT);
		action.addArg("text", text);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public AppAutomator getAutomator() {
		return this.automator;
	}

}
