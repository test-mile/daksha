package daksha.core.uiauto.identifier.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.uiauto.automator.ConcreteGuiAutomator;
import daksha.core.uiauto.element.proxy.GuiElementProxy;
import daksha.core.uiauto.element.proxy.GuiMultiElementProxy;
import daksha.core.uiauto.element.selenium.SeleniumDropdownElement;
import daksha.core.uiauto.element.selenium.SeleniumGenericElement;
import daksha.core.uiauto.element.selenium.SeleniumMultiElement;
import daksha.tpi.uiauto.gui.Gui;

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
