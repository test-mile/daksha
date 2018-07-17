package daksha.core.guiauto.identifier.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.guiauto.automator.ConcreteGuiAutomator;
import daksha.core.guiauto.element.proxy.GuiElementProxy;
import daksha.core.guiauto.element.proxy.GuiMultiElementProxy;
import daksha.core.guiauto.element.selenium.SeleniumDropdownElement;
import daksha.core.guiauto.element.selenium.SeleniumGenericElement;
import daksha.core.guiauto.element.selenium.SeleniumMultiElement;
import daksha.tpi.guiauto.gui.Gui;

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
