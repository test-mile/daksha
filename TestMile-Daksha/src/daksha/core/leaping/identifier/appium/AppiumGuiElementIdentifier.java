package daksha.core.leaping.identifier.appium;

import daksha.core.leaping.element.appium.AppiumDropdownElement;
import daksha.core.leaping.element.appium.AppiumGenericElement;
import daksha.core.leaping.element.appium.AppiumMultiElement;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.core.leaping.identifier.selenium.BaseSeleniumGuiElementIdentifier;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.pageobject.Page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumGuiElementIdentifier extends BaseSeleniumGuiElementIdentifier<AppiumDriver<MobileElement>,MobileElement>{
	
	public AppiumGuiElementIdentifier(GuiAutomator<AppiumDriver<MobileElement>,MobileElement> automator) {
		super(automator);
	}
	
	@Override
	public void convertToDropDown(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumDropdownElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(GuiElementProxy proxy, GuiElementType type) throws Exception {
		switch(type) {
		case DROPDOWN:
			proxy.setConcreteElement(new AppiumDropdownElement(this.getGuiAutomator(), proxy));
			break;
		default:
			proxy.setConcreteElement(new AppiumGenericElement(this.getGuiAutomator(), proxy));
			break;
		}
	}
	
	protected void setConcreteElement(Page page, GuiElementProxy proxy, GuiElementType type) throws Exception {
		switch(type) {
		case DROPDOWN:
			proxy.setConcreteElement(new AppiumDropdownElement(page, this.getGuiAutomator(), proxy));
			break;
		default:
			proxy.setConcreteElement(new AppiumGenericElement(page, this.getGuiAutomator(), proxy));
			break;
		}
	}
	
	
	protected void setConcreteElement(MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(Page page, MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(page, this.getGuiAutomator(), proxy));
	}
}
