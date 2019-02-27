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

package com.testmile.setu.agent.guiauto.core.builder;

import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.setu.agent.SetuAgentConfig;

public class GuiAutomatorBuilder {
	private SetuAgentConfig config;
	
	public GuiAutomatorBuilder(SetuAgentConfig config) {
		this.config = config;
	}
	
	protected SetuAgentConfig getConfig() {
		return this.config;
	}
	
	protected GuiAutomationContext getAutomationContext() throws Exception {
		return config.value(DakshaOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
	}
}
