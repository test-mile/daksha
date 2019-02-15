package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.AlertHandler;

public class SeleniumAlertHandler extends AbstractWDHandler implements AlertHandler {
	private WebDriverWait waiter;
	
	public SeleniumAlertHandler(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.waiter = new WebDriverWait(driver, 1);
	}
	
	protected void validateAlertSupport() throws Exception{
		return;
	}
	
	@Override
	public boolean isAlertPresent() throws Exception {
		try {
			this.waiter.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void confirmAlert() throws Exception {
		validateAlertSupport();
		this.getWebDriver().switchTo().alert().accept();
	}

	@Override
	public void dismissAlert() throws Exception {
		validateAlertSupport();
		this.getWebDriver().switchTo().alert().dismiss();
	}

	@Override
	public void sendTextToAlert(String text) throws Exception {
		validateAlertSupport();
		System.out.println(text);
		this.getWebDriver().switchTo().alert().sendKeys(text);
	}

	@Override
	public String getTextFromAlert() throws Exception {
		validateAlertSupport();
		return this.getWebDriver().switchTo().alert().getText();
	}
}
