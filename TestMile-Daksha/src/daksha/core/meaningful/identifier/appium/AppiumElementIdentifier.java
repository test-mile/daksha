package daksha.core.meaningful.identifier.appium;

import daksha.core.meaningful.automator.ConcreteGuiAutomator;
import daksha.core.meaningful.element.appium.AppiumDropdownElement;
import daksha.core.meaningful.element.appium.AppiumGenericElement;
import daksha.core.meaningful.element.appium.AppiumMultiElement;
import daksha.core.meaningful.element.proxy.GuiElementProxy;
import daksha.core.meaningful.element.proxy.GuiMultiElementProxy;
import daksha.core.meaningful.identifier.selenium.BaseSeleniumElementIdentifier;
import daksha.tpi.meaningful.gui.Gui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumElementIdentifier extends BaseSeleniumElementIdentifier<AppiumDriver<MobileElement>,MobileElement>{
	
	public AppiumElementIdentifier(ConcreteGuiAutomator<AppiumDriver<MobileElement>,MobileElement> automator) throws Exception {
		super(automator);
	}
	
	@Override
	public void convertToDropDown(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumDropdownElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumGenericElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(Gui gui, GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumGenericElement(gui, this.getAutomator(), proxy));
	}
	
	
	protected void setConcreteElement(GuiMultiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(Gui gui, GuiMultiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(gui, this.getAutomator(), proxy));
	}
}
