/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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
package daksha.core.leaping.lib;

import java.util.Map;

import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.lib.base.BaseGuiAutomator;
import daksha.tpi.leaping.enums.UiAutomationContext;

public class DefaultUiAutomator extends BaseGuiAutomator{

	public DefaultUiAutomator(UiAutomationContext context) {
		super(context);
	}
	
	public DefaultUiAutomator(UiAutomationContext context, ElementLoaderType loaderType) {
		super(context, loaderType);
	}

	public DefaultUiAutomator() {
		super();
	}

	@Override
	public void load() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCapabilities(Map<String, ?> caps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
