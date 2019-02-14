package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.MultiElementInquirer;

public class SeleniumMultiElementInquirer extends AbstractWDHandler implements MultiElementInquirer {
	private List<WebElement> elements;

	public SeleniumMultiElementInquirer(WebDriver driver, List<WebElement> elements, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.elements = elements;
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.InquirableElement#getText()
	 */
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.ElementInquirer#getText()
	 */
	@Override
	public List<String> getTextContent() throws Exception {
		List<String> out = new ArrayList<String>();
		for (WebElement element: elements) {
			out.add(element.getText());
		}
		return out;
	}
	
	@Override
	public List<String> getAttribute(String attr) throws Exception {
		List<String> out = new ArrayList<String>();
		for (WebElement element: elements) {
			out.add(element.getAttribute(attr));
		}
		return out;
	}
}
