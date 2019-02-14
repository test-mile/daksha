package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Screenshoter;

public class SeleniumScreenshoter extends AbstractWDHandler implements Screenshoter{
	private TakesScreenshot screenshotDriver;
	
	public SeleniumScreenshoter(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.screenshotDriver = (TakesScreenshot) driver;
	}

	@Override
	public String takeScreenshot() throws Exception {
        return screenshotDriver.getScreenshotAs(OutputType.BASE64);
	}
}