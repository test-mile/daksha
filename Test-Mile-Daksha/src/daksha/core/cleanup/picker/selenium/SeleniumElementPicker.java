package daksha.core.cleanup.picker.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.cleanup.automator.ConcreteUiAutomator;
import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.element.proxy.UiMultiElementProxy;
import daksha.core.cleanup.element.selenium.SeleniumDropdownElement;
import daksha.core.cleanup.element.selenium.SeleniumGenericElement;
import daksha.core.cleanup.element.selenium.SeleniumMultiElement;
import daksha.tpi.cleanup.ui.UI;

public class SeleniumElementPicker extends BaseSeleniumElementPicker<WebDriver,WebElement>{
	
	public SeleniumElementPicker(ConcreteUiAutomator<WebDriver,WebElement> automator) throws Exception {
		super(automator);
	}
	
	@Override
	public void convertToDropDown(UiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumDropdownElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(UiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumGenericElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(UI ui, UiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new SeleniumGenericElement(ui, this.getAutomator(), proxy));
	}
	
	
	protected void setConcreteElement(UiMultiElementProxy proxy) {
		proxy.setConcreteElement(new SeleniumMultiElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(UI ui, UiMultiElementProxy proxy) {
		proxy.setConcreteElement(new SeleniumMultiElement(ui, this.getAutomator(), proxy));
	}

}
