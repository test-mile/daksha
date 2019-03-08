package com.testmile.setu.actor.core.guiauto.dispatcher;

import org.openqa.selenium.WebElement;

public class DriverElementDispatchCommander {

	public static void sendText(WebElement element, String text) throws Exception {
		element.sendKeys(text);
	}

	public static void clearText(WebElement element) throws Exception {
		element.clear();
	}

	public static void submit(WebElement element) throws Exception {
		element.submit();
	}

	public static void click(WebElement element) throws Exception {
		element.click();
	}

	public static String getTextContent(WebElement element) throws Exception {
		return element.getText();
	}

	public static String getAttribute(WebElement element, String attr) throws Exception {
		return element.getAttribute(attr);
	}

	public static String getTagName(WebElement element) throws Exception {
		return element.getTagName();
	}
	
	public static boolean isVisible(WebElement element) {
		return element.isDisplayed();
	}
	
	public static boolean isClickable(WebElement element) {
		return element.isEnabled();		
	}
	
	public static boolean isSelected(WebElement element) throws Exception{
		return element.isSelected();		
	}

}
