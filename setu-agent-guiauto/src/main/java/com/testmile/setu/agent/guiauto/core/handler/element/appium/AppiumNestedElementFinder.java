package com.testmile.setu.agent.guiauto.core.handler.element.appium;

import java.util.List;

import org.openqa.selenium.By;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.appium.AppiumElementFinder;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumNestedElementFinder extends AppiumElementFinder{
	private MobileElement mobileElement;

	public AppiumNestedElementFinder(AppiumDriver<MobileElement> driver, MobileElement element, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.mobileElement = element;
	}
	
	protected List<MobileElement> findAllInContainer(By by) throws Exception{
		return this.mobileElement.findElements(by);
	}
}
