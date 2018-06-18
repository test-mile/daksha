package daksha.core.leaping.identifier.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.core.leaping.element.selenium.SeleniumDropdownElement;
import daksha.core.leaping.element.selenium.SeleniumGenericElement;
import daksha.core.leaping.element.selenium.SeleniumMultiElement;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.pageobject.Page;

public class SeleniumGuiElementIdentifier extends BaseSeleniumGuiElementIdentifier<WebDriver,WebElement>{
	
	public SeleniumGuiElementIdentifier(GuiAutomator<WebDriver,WebElement> automator) {
		super(automator);
	}
	
	@Override
	public void convertToDropDown(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumDropdownElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(GuiElementProxy proxy, GuiElementType type) throws Exception {
		switch(type) {
		case DROPDOWN:
			proxy.setConcreteElement(new SeleniumDropdownElement(this.getGuiAutomator(), proxy));
			break;
		default:
			proxy.setConcreteElement(new SeleniumGenericElement(this.getGuiAutomator(), proxy));
			break;
		}
	}
	
	protected void setConcreteElement(Page page, GuiElementProxy proxy, GuiElementType type) throws Exception {
		switch(type) {
		case DROPDOWN:
			proxy.setConcreteElement(new SeleniumDropdownElement(page, this.getGuiAutomator(), proxy));
			break;
		default:
			proxy.setConcreteElement(new SeleniumGenericElement(page, this.getGuiAutomator(), proxy));
			break;
		}
	}
	
	
	protected void setConcreteElement(MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new SeleniumMultiElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(Page page, MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new SeleniumMultiElement(page, this.getGuiAutomator(), proxy));
	}

}
