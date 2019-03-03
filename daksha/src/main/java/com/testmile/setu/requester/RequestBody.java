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

package com.testmile.setu.requester;

import java.util.HashMap;
import java.util.Map;

public class RequestBody {
	private String action;
	private String setuId;
	private Map<String,Object> args = null;
	
	public void addArg(String name, Object value) {
		if (this.args == null) {
			this.args = new HashMap<String, Object>();
		}
		this.args.put(name, value);
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	public String getSetuId() {
		return setuId;
	}

	public void setSetuId(String setuId) {
		this.setuId = setuId;
	}

}
