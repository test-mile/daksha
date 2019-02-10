package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;

public class SeleniumWaiter extends AbstractWDHandler{
	private WebDriverWait wait;

	public SeleniumWaiter(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		wait = new WebDriverWait(this.getWebDriver(), this.getConfig().getMaxWaitTime());
	}
	
	public <T> T await(ExpectedCondition<T> condition) {
		return wait.until(condition);
	}

}
