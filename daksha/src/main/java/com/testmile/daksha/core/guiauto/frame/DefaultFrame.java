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
package com.testmile.daksha.core.guiauto.frame;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.guiauto.setu.SetuGuiAutoSvcClient;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.tpi.guiauto.Frame;

public class DefaultFrame extends DefaultSetuObject implements Frame {
	private AppAutomator automator;
	private SetuGuiAutoSvcClient setuClient;
	private String baseActionUri = "/frame/action";

	public DefaultFrame(AppAutomator automator, String elemSetuId) {
		this.automator = automator;
		this.setSetuId(elemSetuId);
		setuClient = this.automator.getSetuClient();
	}

	@Override
	public void jump() throws Exception {
		FrameAction action = new FrameAction(this, FrameActionType.JUMP);
		this.setuClient.post(baseActionUri, action);
	}
	
	@Override
	public void jumpToParent() throws Exception {
		FrameAction action = new FrameAction(this, FrameActionType.JUMP_TO_PARENT);
		this.setuClient.post(baseActionUri, action);
	}
	
	@Override
	public void jumpToRoot() throws Exception {
		FrameAction action = new FrameAction(this, FrameActionType.JUMP_TO_ROOT);
		this.setuClient.post(baseActionUri, action);
	}

	@Override
	public AppAutomator getAutomator() {
		return this.automator;
	}

}
