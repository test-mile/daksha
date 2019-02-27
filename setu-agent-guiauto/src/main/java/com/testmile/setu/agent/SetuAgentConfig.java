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

package com.testmile.setu.agent;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testmile.daksha.core.value.StringValue;
import com.testmile.daksha.tpi.batteries.container.Value;
import com.testmile.daksha.tpi.enums.Browser;
import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.guiauto.enums.GuiAutomatorName;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

public class SetuAgentConfig {
	private Map<DakshaOption, Value> options = new HashMap<DakshaOption, Value>();
	
	public SetuAgentConfig(String strJsonConfig) throws Exception {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String, String> config = gson.fromJson(strJsonConfig, type); // contains the whole reviews list
		for (String key: config.keySet()) {
			this.options.put(DakshaOption.valueOf(key.toUpperCase()), new StringValue(config.get(key)));
		}
	}
	
	public Value value(DakshaOption option) {
		return this.options.get(option);
	}

	public Browser getBrowser() throws Exception {
		return value(DakshaOption.BROWSER_NAME).asEnum(Browser.class);
	}

	public int getMaxWaitTime() throws Exception {
		return value(DakshaOption.GUIAUTO_MAX_WAIT).asInt();
	}

	public GuiAutomationContext getAutomationContext() throws Exception {
		return value(DakshaOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
	}

	public OSType getOSType() throws Exception {
		return value(DakshaOption.TESTRUN_TARGET_PLATFORM).asEnum(OSType.class);
	}

	public GuiAutomatorName getAutomatorName() throws Exception {
		return value(DakshaOption.GUI_AUTOMATOR_NAME).asEnum(GuiAutomatorName.class);
	}

}