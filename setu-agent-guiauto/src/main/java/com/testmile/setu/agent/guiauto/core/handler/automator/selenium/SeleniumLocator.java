package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import org.openqa.selenium.By;

public class SeleniumLocator extends GuiElementLocateBy<By>{
	
	public SeleniumLocator(String by, String value) {
		super(by, value);
	}
	
	public By getByObject() throws Exception {
		switch(this.getBy().toUpperCase()) {
		case "ID": return By.id(this.getValue());
		case "NAME": return By.name(this.getValue());
		case "TAG_NAME": return By.tagName(this.getValue());
		case "CLASS_NAME": return By.className(this.getValue());
		case "LINK_TEXT": return By.linkText(this.getValue());
		case "PARTIAL_LINK_TEXT": return By.partialLinkText(this.getValue());
		case "CSS": return By.cssSelector(this.getValue());
		case "XPATH": return By.xpath(this.getValue());
		default:
			throw new Exception(String.format("Unsupported identifier for %s: Type:%s Value:%s",
					this.getClass().getSimpleName(), this.getBy(), this.getValue())
			);		
		}
	}

}
