package daksha.core.leaping.identifier.appium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.batteries.config.Batteries;
import daksha.core.leaping.element.appium.AppiumDropdownElement;
import daksha.core.leaping.element.appium.AppiumGenericElement;
import daksha.core.leaping.element.appium.AppiumMultiElement;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.core.leaping.element.selenium.DefaultSeleniumElementProxy;
import daksha.core.leaping.element.selenium.SeleniumDropdownElement;
import daksha.core.leaping.element.selenium.SeleniumGenericElement;
import daksha.core.leaping.element.selenium.SeleniumMultiElement;
import daksha.core.leaping.enums.WebIdentifyBy;
import daksha.core.leaping.element.selenium.BaseSeleniumDropdownElement;
import daksha.core.leaping.identifier.BaseIdentifier;
import daksha.core.leaping.identifier.Locator;
import daksha.core.leaping.identifier.selenium.BaseSeleniumIdentifier;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.element.MultiGuiElement;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.pageobject.Page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumIdentifier extends BaseSeleniumIdentifier<AppiumDriver<MobileElement>,MobileElement>{
	
	public AppiumIdentifier(GuiAutomator<AppiumDriver<MobileElement>,MobileElement> automator) {
		super(automator);
	}
	
	protected void setConcreteElement(GuiElementProxy proxy, GuiElementType type) throws Exception {
		switch(type) {
		case DROPDOWN:
			proxy.setConcreteElement(new AppiumDropdownElement(this.automator, proxy));
			break;
		default:
			proxy.setConcreteElement(new AppiumGenericElement(this.automator, proxy));
			break;
		}
	}
	
	protected void setConcreteElement(Page page, GuiElementProxy proxy, GuiElementType type) throws Exception {
		switch(type) {
		case DROPDOWN:
			proxy.setConcreteElement(new AppiumDropdownElement(page, this.automator, proxy));
			break;
		default:
			proxy.setConcreteElement(new AppiumGenericElement(page, this.automator, proxy));
			break;
		}
	}
	
	
	protected void setConcreteElement(MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(this.automator, proxy));
	}
	
	protected void setConcreteElement(Page page, MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(page, this.automator, proxy));
	}

}
