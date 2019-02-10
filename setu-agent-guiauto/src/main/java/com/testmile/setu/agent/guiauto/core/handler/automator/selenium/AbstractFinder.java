package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.element.DefaultGuiMultiElement;
import com.testmile.setu.agent.guiauto.core.element.SeleniumGuiElement;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public abstract class AbstractFinder<E extends WebElement> extends AbstractWDHandler {

	protected WebDriverWait wait;

	public AbstractFinder(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}

	protected GuiElement convertToGuiElement(E element) throws Exception {
		return new SeleniumGuiElement(this.getWebDriver(), element, this.getConfig());
	}

	protected GuiMultiElement convetToMultiGuiElement(List<E> rawElements) throws Exception {
		List<GuiElement> elements = new ArrayList<GuiElement>();
		for (E element: rawElements) {
			elements.add(convertToGuiElement(element));
		}
		return new DefaultGuiMultiElement(elements);
	}

}