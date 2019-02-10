package com.testmile.setu.agent.guiauto.core.handler.automator;

import com.testmile.setu.agent.SetuAgentConfig;

public abstract class AbstractAutomatorHandler {
	private SetuAgentConfig config;
	
	public AbstractAutomatorHandler(SetuAgentConfig config) throws Exception {
		this.config = config;
	}
	
	protected SetuAgentConfig getConfig() {
		return this.config;
	}

}
