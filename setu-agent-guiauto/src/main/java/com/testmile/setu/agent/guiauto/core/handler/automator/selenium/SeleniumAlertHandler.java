package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.AlertHandler;

public class SeleniumAlertHandler extends AbstractWDHandler implements AlertHandler {
	
	public SeleniumAlertHandler(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}
	
	protected void validateAlertSupport() throws Exception{
		return;
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.selenium.AlertHandler#confirmAlertIfPresent()
	 */
	@Override
	public void confirmAlertIfPresent() throws Exception {
		validateAlertSupport();
		WebDriver d = getWebDriver();
		try{
			Alert alert = d.switchTo().alert();
			alert.accept();
			d.switchTo().defaultContent();
		} catch (Exception e){ // ignore
		}
	}
}
