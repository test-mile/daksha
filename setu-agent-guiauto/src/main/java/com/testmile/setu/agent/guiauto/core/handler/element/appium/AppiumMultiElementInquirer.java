package com.testmile.setu.agent.guiauto.core.handler.element.appium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.MultiElementInquirer;

import io.appium.java_client.MobileElement;

public class AppiumMultiElementInquirer extends AbstractWDHandler implements MultiElementInquirer {
	private List<MobileElement> elements;

	public AppiumMultiElementInquirer(WebDriver driver, List<MobileElement> elements, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.elements = elements;
	}

	@Override
	public List<String> getTagNames() throws Exception {
		List<String> out = new ArrayList<String>();
		for (MobileElement element: elements) {
			out.add(element.getTagName());
		}
		return out;
	}
	
	@Override
	public List<String> getTextContent() throws Exception {
		List<String> out = new ArrayList<String>();
		for (MobileElement element: elements) {
			out.add(element.getText());
		}
		return out;
	}
	
	@Override
	public List<String> getAttribute(String attr) throws Exception {
		List<String> out = new ArrayList<String>();
		for (MobileElement element: elements) {
			out.add(element.getAttribute(attr));
		}
		return out;
	}
}
