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

package com.testmile.setu.requester.guiauto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testmile.setu.requester.connector.SetuArg;

public class With {
	private String withType;
	private Object withValue;
	private boolean childLocator = false;
	private With child;
	
	private With (WithType withType, Object withValue){
		this.withType = withType.toString();
		if (withValue instanceof With) {
			childLocator = true;
			child = (With) withValue;
		} else {
			this.withValue = withValue;	
		}
	}
	
	public static With id(String id) {
		return new With(WithType.ID, id);
	}
	
	public static With name(String name) {
		return new With(WithType.NAME, name);
	}
	
	public static With className(String name) {
		return new With(WithType.CLASS_NAME, name);
	}
	
	public static With linkText(String text) {
		return new With(WithType.LINK_TEXT, text);
	}
	
	public static With cssSelector(String selector) {
		return new With(WithType.CSS_SELECTOR, selector);
	}
	
	public static With xpath(String xpath) {
		return new With(WithType.XPATH, xpath);
	}
	
	public static With index(int index) {
		return new With(WithType.INDEX, index);
	}
	
	public static With childLocator(With locator) {
		return new With(WithType.CHILD_LOCATOR, locator);
	}
	
	public static With assignedName(String name) {
		return new With(WithType.ASSIGNED_NAME, name);
	}
	
	public List<SetuArg> asSetuArgs(){
		List<SetuArg> args = new ArrayList<SetuArg>();
		Map<String, Object> map = new HashMap<String, Object>();
		args.add(SetuArg.arg("withType", this.withType));
		if (!this.childLocator) {
			args.add(SetuArg.arg("withValue", this.withValue));
		} else {
			map.put("withValue", this.child.asSetuArgs());		
		}
		return args;
	}
}


enum WithType {
	ID,
	NAME,
	CLASS_NAME, 
	LINK_TEXT, 
	XPATH,
	CSS_SELECTOR, 
	
	INDEX,
	CHILD_LOCATOR,
	ASSIGNED_NAME

}
