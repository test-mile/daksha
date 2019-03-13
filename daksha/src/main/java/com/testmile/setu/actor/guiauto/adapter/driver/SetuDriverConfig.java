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

package com.testmile.setu.actor.guiauto.adapter.driver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;

import com.testmile.trishanku.tpi.enums.BrowserName;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.enums.GuiAutomatorName;
import com.testmile.trishanku.tpi.enums.MobileOSName;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.value.AnyRefValue;
import com.testmile.trishanku.tpi.value.Value;

public class SetuDriverConfig {
	private Map<SetuGuiAutoActorOption, Value> options = new HashMap<SetuGuiAutoActorOption, Value>();
	private Map<String, Value> userOptions = new HashMap<String, Value>();
	private Map<String, String> driverCapabilities = new HashMap<String, String>();
	private Map<String, Object> browserPreferences = new HashMap<String, Object>();
	private List<String> browserArgs;
	private List<File> browserExtensions = new ArrayList<File>();
	
	public SetuDriverConfig(Map<String, Object> jsonArgs) throws Exception {
		Map<String, Object> sOptions = (Map<String, Object>) jsonArgs.get("setuOptions");
		Map<String, Object> uOptions = (Map<String, Object>) jsonArgs.get("userOptions");
		Map<String, Object> driverCaps = (Map<String, Object>) jsonArgs.get("driverCapabilities");
		
		browserPreferences = (Map<String, Object>) jsonArgs.get("browserPreferences");
		browserArgs = (List<String>) jsonArgs.get("browserArgs");
		List<String> exts = (List<String>) jsonArgs.get("browserExtensions");

		for (String key: sOptions.keySet()) {
			this.options.put(SetuGuiAutoActorOption.valueOf(key.toUpperCase()), new AnyRefValue(sOptions.get(key)));
		}
		
		for (String key: uOptions.keySet()) {
			this.userOptions.put(key, new AnyRefValue(uOptions.get(key)));
		}
		
		for (String key: driverCaps.keySet()) {
			this.driverCapabilities.put(key.toUpperCase(), (new AnyRefValue(driverCaps.get(key))).asString());
		}
		
		for (String ext: exts) {
			this.browserExtensions.add(new File(ext));
		}
	}
	
	public Value value(SetuGuiAutoActorOption option) {
		return this.options.get(option);
	}

	public BrowserName getBrowserName() throws Exception {
		return value(SetuGuiAutoActorOption.BROWSER_NAME).asEnum(BrowserName.class);
	}

	public GuiAutomationContext getGuiAutomationContext() throws Exception {
		return value(SetuGuiAutoActorOption.GUIAUTO_CONTEXT).asEnum(GuiAutomationContext.class);
	}

	public MobileOSName getMobileOSName() throws Exception {
		return value(SetuGuiAutoActorOption.MOBILE_OS_NAME).asEnum(MobileOSName.class);
	}

	public GuiAutomatorName getGuiAutomatorName() throws Exception {
		return value(SetuGuiAutoActorOption.GUIAUTO_AUTOMATOR_NAME).asEnum(GuiAutomatorName.class);
	}
	
	public Map<String, String> getDriverCapabilities(){
		return this.driverCapabilities;
	}
	
	public Map<String, Object> getBrowserPreferences(){
		return browserPreferences;
	}
	
	public List<String> getBrowserArgs(){
		return browserArgs;
	}
	
	public List<File> getBrowserExtensions(){
		return browserExtensions;
	}
	
	public boolean isProxyEnabled() throws Exception {
		return this.value(SetuGuiAutoActorOption.BROWSER_PROXY_ON).asBoolean();
	}
	
	public Proxy getProxy() throws Exception {
		if (!isProxyEnabled()) return null;
		Proxy proxy = new Proxy();
		String proxyString = this.value(SetuGuiAutoActorOption.BROWSER_PROXY_HOST).asString() 
				+ ":" + 
				this.value(SetuGuiAutoActorOption.BROWSER_PROXY_PORT).asString();
		proxy.setHttpProxy(proxyString);
		proxy.setSslProxy(proxyString);
		return proxy;
	}
	

}