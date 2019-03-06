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

package com.testmile.setu.actor.core.websvc.guiauto.helpers;

class ElementAction extends GuiAutoAction{
	private ElementActionType action;
	
	public ElementAction(String jsonActionStr) {
		super(jsonActionStr);
		this.action = ElementActionType.valueOf(this.getActionTypeStr());
	}

	public ElementActionType getActionType() {
		return action;
	}
	
	public boolean isInstanceAction() throws Exception {
		if (this.getArgs().containsKey("isInstanceAction")){
			return this.getArgs().get("isInstanceAction").asBoolean();
		} else {
			return false;
		}
	}

	public int getInstanceIndex() throws Exception {
		if (!isInstanceAction()) {
			throw new Exception("This is not an instnce action.");
		}
		return this.getArgs().get("instanceIndex").asInt();
	}
}