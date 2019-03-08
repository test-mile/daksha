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

package com.testmile.setu.actor.core.guiauto.translator;

import java.util.List;

public class GuiMultiElement<T,E> {
	private List<DriverElementDispatcher<T,E>> elements;

	public GuiMultiElement(List<DriverElementDispatcher<T,E>> elements) {
		super();
		this.elements = elements;
	}

	public int getInstanceCount() throws Exception {
		return this.elements.size();
	}
	
	public DriverElementDispatcher<T,E> getInstanceAtIndex(int index) throws Exception {
		return this.elements.get(index);
	}

}