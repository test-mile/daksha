package daksha.core.cleanup.picker.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.cleanup.automator.ConcreteGuiAutomator;
import daksha.core.cleanup.element.proxy.GuiElementProxy;
import daksha.core.cleanup.element.proxy.MultiGuiElementProxy;
import daksha.core.cleanup.element.selenium.SeleniumDropdownElement;
import daksha.core.cleanup.element.selenium.SeleniumGenericElement;
import daksha.core.cleanup.element.selenium.SeleniumMultiElement;
import daksha.tpi.cleanup.gui.Gui;

public class SeleniumGuiElementPicker extends BaseSeleniumGuiElementPicker<WebDriver,WebElement>{
	
	public SeleniumGuiElementPicker(ConcreteGuiAutomator<WebDriver,WebElement> automator) throws Exception {
		super(automator);
	}
	
	@Override
	public void convertToDropDown(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumDropdownElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumGenericElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(Gui page, GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumGenericElement(page, this.getGuiAutomator(), proxy));
	}
	
	
	protected void setConcreteElement(MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new SeleniumMultiElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(Gui page, MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new SeleniumMultiElement(page, this.getGuiAutomator(), proxy));
	}

}
