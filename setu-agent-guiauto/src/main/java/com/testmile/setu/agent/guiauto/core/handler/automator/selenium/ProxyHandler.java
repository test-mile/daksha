package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

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
