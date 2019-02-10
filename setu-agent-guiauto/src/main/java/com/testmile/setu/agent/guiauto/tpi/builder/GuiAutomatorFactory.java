package com.testmile.setu.agent.guiauto.tpi.builder;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.automator.SeleniumGuiAutomator;
import com.testmile.setu.agent.guiauto.core.builder.AppiumBuilder;
import com.testmile.setu.agent.guiauto.core.builder.SeleniumBuilder;
import com.testmile.setu.agent.guiauto.tpi.automator.GuiAutomator;

public class GuiAutomatorFactory {
	
	public static GuiAutomator createAutomator(String strJsonConfig) throws Exception{
		SetuAgentConfig config = new SetuAgentConfig(strJsonConfig);
		switch(config.getAutomatorName()) {
		case APPIUM:
			return createAppiumAutomator(config);
		case SIKULI:
			throw new Exception("Not supported yet");
		case SELENIUM:
			return createSeleniumAutomator(config);
		}
		return null;
	}
	
	private static GuiAutomator createSeleniumAutomator(SetuAgentConfig config) throws Exception {
		return (new SeleniumBuilder(config)).build();
	}
	
	private static GuiAutomator createAppiumAutomator(SetuAgentConfig config) throws Exception {
		return (new AppiumBuilder(config)).build();
	}

}
