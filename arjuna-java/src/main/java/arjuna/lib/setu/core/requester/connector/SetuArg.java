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

package arjuna.lib.setu.core.requester.connector;

public class SetuArg{
	private String name;
	private Object obj;
	
	private SetuArg(String name, Object obj) {
		this.name = name;
		this.obj = obj;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Object getObject() {
		return this.obj;
	}
	
	public static SetuArg arg(String name, Object obj) {
		return new SetuArg(name, obj);
	}
	
	public static SetuArg textArg(Object obj) {
		return new SetuArg("text", obj);
	}

	public static SetuArg valueArg(String value) {
		return new SetuArg("value", value);
	}
	
	public static SetuArg indexArg(int index) {
		return new SetuArg("index", index);
	}
	
	public static SetuArg configArg(String id) {
		return new SetuArg("configSetuId", id);
	}
}