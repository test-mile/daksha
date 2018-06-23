package daksha.core.cleanup.picker.appium;

import daksha.core.cleanup.automator.ConcreteGuiAutomator;
import daksha.core.cleanup.element.appium.AppiumDropdownElement;
import daksha.core.cleanup.element.appium.AppiumGenericElement;
import daksha.core.cleanup.element.appium.AppiumMultiElement;
import daksha.core.cleanup.element.proxy.GuiElementProxy;
import daksha.core.cleanup.element.proxy.MultiGuiElementProxy;
import daksha.core.cleanup.picker.selenium.BaseSeleniumGuiElementPicker;
import daksha.tpi.cleanup.gui.Gui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumGuiElementPicker extends BaseSeleniumGuiElementPicker<AppiumDriver<MobileElement>,MobileElement>{
	
	public AppiumGuiElementPicker(ConcreteGuiAutomator<AppiumDriver<MobileElement>,MobileElement> automator) throws Exception {
		super(automator);
	}
	
	@Override
	public void convertToDropDown(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumDropdownElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumGenericElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(Gui gui, GuiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumGenericElement(gui, this.getGuiAutomator(), proxy));
	}
	
	
	protected void setConcreteElement(MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(this.getGuiAutomator(), proxy));
	}
	
	protected void setConcreteElement(Gui gui, MultiGuiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(gui, this.getGuiAutomator(), proxy));
	}
}
