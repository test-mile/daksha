package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.element.DefaultGuiMultiElement;
import com.testmile.setu.agent.guiauto.core.element.SeleniumGuiElement;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public abstract class AbstractSeleniumFinder<E extends WebElement> extends AbstractWDHandler implements ElementFinder {

	public AbstractSeleniumFinder(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}

	protected GuiMultiElement convetToMultiGuiElement(List<E> rawElements) throws Exception {
		List<GuiElement> elements = new ArrayList<GuiElement>();
		for (E element: rawElements) {
			elements.add(convertToGuiElement(element));
		}
		return new DefaultGuiMultiElement(elements);
	}
	
	protected abstract GuiElement convertToGuiElement(E element) throws Exception;
	protected abstract List<E> findAllInContainer(By by) throws Exception;
	protected abstract By getLocator(String by, String value) throws Exception;
	
	@Override
	public GuiMultiElement findAll(String by, String value) throws Exception {
		return convetToMultiGuiElement(this.findAllInContainer(this.getLocator(by, value)));
	}

	
}