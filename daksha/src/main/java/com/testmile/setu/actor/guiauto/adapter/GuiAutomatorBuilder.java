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

package com.testmile.setu.actor.guiauto.adapter;

import com.testmile.setu.actor.guiauto.adapter.driver.SetuDriverConfig;
import com.testmile.setu.actor.guiauto.adapter.driver.SetuGuiAutoActorOption;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;

public class GuiAutomatorBuilder {
	private SetuDriverConfig config;
	
	public GuiAutomatorBuilder(SetuDriverConfig config) {
		this.config = config;
	}
	
	protected SetuDriverConfig getConfig() {
		return this.config;
	}
	
	protected GuiAutomationContext getAutomationContext() throws Exception {
		return config.value(SetuGuiAutoActorOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
	}
}