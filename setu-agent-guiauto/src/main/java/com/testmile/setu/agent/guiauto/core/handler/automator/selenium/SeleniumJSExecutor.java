package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.JSExecutor;

public class SeleniumJSExecutor  extends AbstractWDHandler implements JSExecutor{
	private JavascriptExecutor jse;
	
	public SeleniumJSExecutor(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		jse = (JavascriptExecutor) this.getWebDriver();
	}
	
	private JavascriptExecutor getJSExecutor() {
		return this.jse;
	}
	
	protected void validateJSSupport() throws Exception{
		return;
	}
	
	@Override
	public void executeScript(String script, Object... args) throws Exception {
		validateJSSupport();
		this.getJSExecutor().executeScript(script, args);
	}
}
