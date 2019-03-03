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

package com.testmile.setu.requester.guiauto.alert;

import com.testmile.daksha.tpi.guiauto.Alert;
import com.testmile.setu.requester.Response;
import com.testmile.setu.requester.SetuRequest;
import com.testmile.setu.requester.guiauto.GenericElement;
import com.testmile.setu.requester.guiauto.automator.AppAutomator;

public class DefaultAlert extends GenericElement implements Alert {

	public DefaultAlert(AppAutomator automator, String setuId) {
		super(automator, setuId, "/alert/action");
	}

	@Override
	public void confirm() throws Exception {
		takeAction(AlertActionType.CONFIRM.toString());
	}

	@Override
	public void dismiss() throws Exception {
		takeAction(AlertActionType.DISMISS.toString());
	}

	@Override
	public String getText() throws Exception {
		Response response = this.takeAction(AlertActionType.GET_TEXT.toString());
		return (String) response.getData().get("text");
	}

	@Override
	public void sendText(String text) throws Exception {
		SetuRequest request = this.createRequest(AlertActionType.SEND_TEXT.toString());
		request.addArg("text", text);
		this.takeAction(request);
	}



}
