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

package com.testmile.daksha.core.guiauto.element;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.setu.SetuGuiAutoSvcClient;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.tpi.guiauto.GuiElement;

public class DefaultGuiElement extends DefaultSetuObject implements GuiElement {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/element/action";
	private boolean partial = false;
	private int index = 0;

	public DefaultGuiElement(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}
	
	public DefaultGuiElement(AppAutomator automator, String elemSetuId, int index, String baseUri) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
		this.partial = true;
		this.index = index;
		this.baseActionUri = baseUri;
	}

	public AppAutomator getAutomator() {
		return this.automator;
	}
	
	@Override
	public void setText(String text) throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.SET_TEXT);
		action.addArg("text", text);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void click() throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.CLICK);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void waitUntilClickable() throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.WAIT_UNTIL_CLICKABLE);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void check() throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.CHECK);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public void uncheck() throws Exception {
		GuiElementAction action = new GuiElementAction(this, GuiElementActionType.UNCHECK);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public boolean isPartial() throws Exception {
		return this.partial;
	}

}
