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

package com.testmile.daksha.core.setu;

import com.google.gson.Gson;
import com.testmile.daksha.tpi.test.TestSession;

public abstract class AbstractAction implements SetuRequest {
	
	private static Gson gson = new Gson();
	private RequestBody actionRequest;
	
	public AbstractAction(TestSession session) {
		this.actionRequest = new RequestBody();
		this.addArg("testSessionSetuId", session.getSetuId());
	}
	
	public AbstractAction(SetuManagedObject obj) {
		this.actionRequest = new RequestBody();
		this.addArg("testSessionSetuId", obj.getTestSessionSetuId());
	}
	
	public AbstractAction(String testSessionSetuId) {
		this.actionRequest = new RequestBody();
		this.addArg("testSessionSetuId", testSessionSetuId);
	}
	
	public void addArg(String name, Object value) {
		this.getActionRequest().addArg(name, value);
	}
	
	@Override
	public String asJsonString() {
		return gson.toJson(this.getActionRequest(), RequestBody.class);
	}

	protected RequestBody getActionRequest() {
		return actionRequest;
	}
	
}
