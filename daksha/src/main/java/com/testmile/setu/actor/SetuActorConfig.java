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

package com.testmile.setu.actor;

import java.util.HashMap;
import java.util.Map;

import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.GuiAutomatorName;
import com.testmile.trishanku.tpi.enums.OSType;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.AnyRefValue;
import com.testmile.trishanku.tpi.value.Value;

public class SetuActorConfig {
	private Map<SetuOption, Value> options = new HashMap<SetuOption, Value>();
	private Map<String, Value> userOptions = new HashMap<String, Value>();
	
	public SetuActorConfig(Map<String, Object> jsonArgs) throws Exception {
		Map<String, Object> setuOptions = (Map<String, Object>) ((Map<String, Object>) jsonArgs.get("config")).get("setuOptions");
		Map<String, Object> userOptions = (Map<String, Object>) ((Map<String, Object>) jsonArgs.get("config")).get("userOptions");

		for (String key: setuOptions.keySet()) {
			this.options.put(SetuOption.valueOf(key.toUpperCase()), new AnyRefValue(setuOptions.get(key)));
		}
		
		for (String key: setuOptions.keySet()) {
			this.options.put(SetuOption.valueOf(key.toUpperCase()), new AnyRefValue(setuOptions.get(key)));
		}

		for (String key: userOptions.keySet()) {
			this.userOptions.put(key.toUpperCase(), new AnyRefValue(userOptions.get(key)));
		}
	}
	
	public Value value(SetuOption option) {
		return this.options.get(option);
	}

	public Browser getBrowser() throws Exception {
		return value(SetuOption.BROWSER_NAME).asEnum(Browser.class);
	}

	public int getMaxWaitTime() throws Exception {
		return value(SetuOption.GUIAUTO_MAX_WAIT).asInt();
	}

	public GuiAutomationContext getAutomationContext() throws Exception {
		return value(SetuOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
	}

	public OSType getOSType() throws Exception {
		return value(SetuOption.TESTRUN_TARGET_PLATFORM).asEnum(OSType.class);
	}

	public GuiAutomatorName getAutomatorName() throws Exception {
		return value(SetuOption.GUIAUTO_AUTOMATOR_NAME).asEnum(GuiAutomatorName.class);
	}

}