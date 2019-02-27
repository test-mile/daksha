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

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;

import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractAutomatorHandler;

public class ProxyHandler extends AbstractAutomatorHandler {
	
	public ProxyHandler(SetuAgentConfig config) throws Exception {
		super(config);
	}
	
	public void configureProxy(MutableCapabilities caps) throws Exception {
		if (getConfig().value(DakshaOption.BROWSER_PROXY_ON).asBoolean()){
			Proxy proxy = new Proxy();
			String p = getConfig().value(DakshaOption.BROWSER_PROXY_HOST).asString() + ":" + getConfig().value(DakshaOption.BROWSER_PROXY_PORT).asString();
			setHttpProxy(proxy, p);
			setSslProxy(proxy, p);
			caps.setCapability("proxy", proxy);
		}
	}

	public void setHttpProxy(Proxy proxy, String proxyString) {
		proxy.setHttpProxy(proxyString);
	}
	
	public void setSslProxy(Proxy proxy, String proxyString) {
		proxy.setSslProxy(proxyString);
	}

}
