package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumElementFinder extends AbstractAppiumFinder{
	public AppiumElementFinder(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}
	
	private List<MobileElement> findElements(String by, String value) throws Exception{
		By finderType = convertToBy(by, value);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(finderType));
		return getAppiumDriver().findElements(finderType);		
	}
	
	// Ideally SearchContext should work, but it is able to return only List<WebElement>.
	// Same challenge with WebDriverWait. Here if properly implemented a mobile wait should return List<MobileElement>
	public GuiMultiElement findAll(String by, String value) throws Exception {
		return this.convetToMultiGuiElement(findElements(by, value));
	}

	public GuiElement find(String by, String value) throws Exception {
		return this.convertToGuiElement(this.findElements(by, value).get(0));
	}
	
}
