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

package com.testmile.daksha.core.guiauto.window;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.setu.SetuGuiAutoSvcClient;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.setu.Response;

public class AbstractBasicWindow extends DefaultSetuObject implements BasicWindow {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/window/action";

	public AbstractBasicWindow(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}
	
	protected SetuGuiAutoSvcClient getSetuClient() {
		return this.setuClient;
	}
	
	protected String getBaseActionUri() {
		return this.baseActionUri;
	}
	
	@Override
	public String getTitle() throws Exception {
		WindowAction action = new WindowAction(this, WindowActionType.GET_TITLE);
		Response response = this.getSetuClient().post(this.getBaseActionUri(), action);
		return (String) response.getData().get("title");
	}
	
	@Override
	public void jump() throws Exception {
		WindowAction action = new WindowAction(this, WindowActionType.JUMP);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public AppAutomator getAutomator() {
		return this.automator;
	}
}
