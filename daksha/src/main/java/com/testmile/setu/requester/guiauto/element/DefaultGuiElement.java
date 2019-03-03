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

package com.testmile.setu.requester.guiauto.element;

import com.testmile.daksha.tpi.guiauto.GuiElement;
import com.testmile.setu.requester.SetuRequest;
import com.testmile.setu.requester.guiauto.GenericElement;
import com.testmile.setu.requester.guiauto.automator.AppAutomator;

public class DefaultGuiElement extends GenericElement implements GuiElement {
	private boolean partial = false;
	private int index = 0;

	public DefaultGuiElement(AppAutomator automator, String setuId) {
		super(automator, setuId, "/element/action");
	}
	
	public DefaultGuiElement(AppAutomator automator, String setuId, int index, String baseUri) {
		this(automator, setuId);
		this.partial = true;
		this.index = index;
		this.setBaseActionUri(baseUri);
	}
	
	@Override
	public void setText(String text) throws Exception {
		SetuRequest request = this.createRequest(GuiElementActionType.SET_TEXT.toString());
		request.addArg("text", text);
		this.takeAction(request);
	}

	@Override
	public void click() throws Exception {
		this.takeAction(GuiElementActionType.CLICK.toString());
	}

	@Override
	public void waitUntilClickable() throws Exception {
		this.takeAction(GuiElementActionType.WAIT_UNTIL_CLICKABLE.toString());
	}

	@Override
	public void check() throws Exception {
		this.takeAction(GuiElementActionType.CHECK.toString());
	}

	@Override
	public void uncheck() throws Exception {
		this.takeAction(GuiElementActionType.UNCHECK.toString());
	}

	@Override
	public boolean isPartial() throws Exception {
		return this.partial;
	}
	
	public int getIndex() {
		return this.index;
	}

}
