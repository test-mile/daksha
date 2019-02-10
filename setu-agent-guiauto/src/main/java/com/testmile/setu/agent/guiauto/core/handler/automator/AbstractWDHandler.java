package com.testmile.setu.agent.guiauto.core.handler.automator;

import org.openqa.selenium.WebDriver;

import com.testmile.setu.agent.SetuAgentConfig;

public class AbstractWDHandler extends AbstractAutomatorHandler {
	private WebDriver driver;

	public AbstractWDHandler(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(config);
		this.driver = driver;
	}

	protected WebDriver getWebDriver() {
		return driver;
	}
}
