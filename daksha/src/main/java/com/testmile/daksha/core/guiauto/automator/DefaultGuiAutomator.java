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

package com.testmile.daksha.core.guiauto.automator;

import com.testmile.daksha.core.guiauto.window.DefaultMainWindow;
import com.testmile.daksha.core.setu.Response;
import com.testmile.daksha.tpi.guiauto.GuiAutomator;
import com.testmile.daksha.tpi.test.TestConfig;

public class DefaultGuiAutomator extends AbstractAppAutomator implements GuiAutomator {
	
	public DefaultGuiAutomator(TestConfig config) throws Exception {
		super("/automator/", config);
	}
	
	@Override
	public void launch() throws Exception {
		GuiAutomatorAction action = new GuiAutomatorAction(this.getTestSessionSetuId(), GuiAutomatorActionType.LAUNCH);
		action.addArg("configSetuId", this.getConfig().getSetuId());
		Response response = this.setuClient.post(baseUri + "launch", action);
		this.setSetuId((String) response.getData().get("automatorSetuId"));
		
		GuiAppAutomatorAction windowAction = new GuiAppAutomatorAction(this, GuiAppAutomatorActionType.GET_MAIN_WINDOW);
		mainWindow = new DefaultMainWindow (this, this.takeElementFindingAction(windowAction));
	}
	
	@Override
	public void quit() throws Exception {
		this.setuClient.post(baseUri + "quit", new GuiAutomatorAction(this, GuiAutomatorActionType.QUIT));
	}
}
