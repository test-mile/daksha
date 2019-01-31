package com.testmile.daksha.core.guiauto.identifier.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.daksha.core.guiauto.automator.ConcreteGuiAutomator;
import com.testmile.daksha.core.guiauto.element.proxy.GuiElementProxy;
import com.testmile.daksha.core.guiauto.element.proxy.GuiMultiElementProxy;
import com.testmile.daksha.core.guiauto.element.selenium.SeleniumDropdownElement;
import com.testmile.daksha.core.guiauto.element.selenium.SeleniumGenericElement;
import com.testmile.daksha.core.guiauto.element.selenium.SeleniumMultiElement;
import com.testmile.daksha.tpi.guiauto.gui.Gui;

public class SeleniumElementIdentifier extends BaseSeleniumElementIdentifier<WebDriver,WebElement>{
	
	public SeleniumElementIdentifier(ConcreteGuiAutomator<WebDriver,WebElement> automator) throws Exception {
		super(automator);
	}
	
	@Override
	public void convertToDropDown(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumDropdownElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumGenericElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(Gui gui, GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumGenericElement(gui, this.getAutomator(), proxy));
	}
	
	
	protected void setConcreteElement(GuiMultiElementProxy proxy) {
		proxy.setConcreteElement(new SeleniumMultiElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(Gui gui, GuiMultiElementProxy proxy) {
		proxy.setConcreteElement(new SeleniumMultiElement(gui, this.getAutomator(), proxy));
	}

}
