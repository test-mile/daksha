package daksha.core.guiauto.identifier.appium;

import daksha.core.guiauto.automator.ConcreteGuiAutomator;
import daksha.core.guiauto.element.appium.AppiumDropdownElement;
import daksha.core.guiauto.element.appium.AppiumGenericElement;
import daksha.core.guiauto.element.appium.AppiumMultiElement;
import daksha.core.guiauto.element.proxy.GuiElementProxy;
import daksha.core.guiauto.element.proxy.GuiMultiElementProxy;
import daksha.core.guiauto.identifier.selenium.BaseSeleniumElementIdentifier;
import daksha.tpi.guiauto.gui.Gui;
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
