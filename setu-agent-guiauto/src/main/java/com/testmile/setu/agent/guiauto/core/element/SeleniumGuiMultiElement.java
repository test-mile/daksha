package com.testmile.setu.agent.guiauto.core.element;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumMultiElementInquirer;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumMultiElementStateHandler;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;

public class SeleniumGuiMultiElement extends AbstractGuiMultiElement{
	
	public SeleniumGuiMultiElement(WebDriver driver, List<GuiElement> elements, List<WebElement> rawElements, SetuAgentConfig config) throws Exception {
		super(elements);
		setInquirer(new SeleniumMultiElementInquirer(driver, rawElements, config));
		setStateHandler(new SeleniumMultiElementStateHandler(driver, rawElements, config));
	}

}
