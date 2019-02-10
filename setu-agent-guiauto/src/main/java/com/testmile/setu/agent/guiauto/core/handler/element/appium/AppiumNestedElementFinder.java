package com.testmile.setu.agent.guiauto.core.handler.element.appium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.appium.AbstractAppiumFinder;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumNestedElementFinder extends AbstractAppiumFinder{
	private MobileElement mobileElement;

	public AppiumNestedElementFinder(AppiumDriver<MobileElement> driver, MobileElement element, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.mobileElement = element;
	}
	
	private List<MobileElement> findElements(String by, String value) throws Exception{
		By finderType = convertToBy(by, value);
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(mobileElement, finderType));
		return mobileElement.findElements(finderType);		
	}

	public GuiMultiElement findAll(String by, String value) throws Exception {
		return this.convetToMultiGuiElement(findElements(by, value));
	}

	public GuiElement find(String by, String value) throws Exception {
		return this.convertToGuiElement(this.findElements(by, value).get(0));
	}
	
}
