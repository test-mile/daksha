package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import org.openqa.selenium.By;

import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumLocator;

import io.appium.java_client.MobileBy;

public class AppiumLocator extends SeleniumLocator{
	
	public AppiumLocator(String by, String value) {
		super(by, value);
	}
	
	public By getByObject() throws Exception {
		switch(this.getBy().toUpperCase()) {
		case "ACCESSIBILITY_ID": return MobileBy.AccessibilityId(this.getValue());
		default:
			return super.getByObject();	
		}
		
	}

}
