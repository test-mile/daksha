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
