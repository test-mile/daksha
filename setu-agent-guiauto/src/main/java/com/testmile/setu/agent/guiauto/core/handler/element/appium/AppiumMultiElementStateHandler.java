package com.testmile.setu.agent.guiauto.core.handler.element.appium;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.MultiElementStateHandler;

import io.appium.java_client.MobileElement;

public class AppiumMultiElementStateHandler extends AbstractWDHandler implements MultiElementStateHandler {
	private WebDriverWait waiter;
	private List<MobileElement> elements;

	public AppiumMultiElementStateHandler(WebDriver driver, List<MobileElement> elements, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.elements = elements;
		this.waiter = new WebDriverWait(driver, this.getConfig().getMaxWaitTime());
	}
	
	public void waitUntilVisible() throws Exception {
		for (WebElement element: elements) {
			waiter.until(ExpectedConditions.elementToBeClickable(element));	
		}
	}
	
	public void waitUntilInvisible() throws Exception {
		for (WebElement element: elements) {
			waiter.until(ExpectedConditions.invisibilityOf(element));	
		}
	}
	
	public void waitUntilClickable() throws Exception {
		for (WebElement element: elements) {
			waiter.until(ExpectedConditions.elementToBeClickable(element));	
		}
	}
}
