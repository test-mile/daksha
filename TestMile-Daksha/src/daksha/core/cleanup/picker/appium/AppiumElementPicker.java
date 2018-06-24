package daksha.core.cleanup.picker.appium;

import daksha.core.cleanup.automator.ConcreteUiAutomator;
import daksha.core.cleanup.element.appium.AppiumDropdownElement;
import daksha.core.cleanup.element.appium.AppiumGenericElement;
import daksha.core.cleanup.element.appium.AppiumMultiElement;
import daksha.core.cleanup.element.proxy.UiElementProxy;
import daksha.core.cleanup.element.proxy.UiMultiElementProxy;
import daksha.core.cleanup.picker.selenium.BaseSeleniumElementPicker;
import daksha.tpi.cleanup.ui.UI;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumElementPicker extends BaseSeleniumElementPicker<AppiumDriver<MobileElement>,MobileElement>{
	
	public AppiumElementPicker(ConcreteUiAutomator<AppiumDriver<MobileElement>,MobileElement> automator) throws Exception {
		super(automator);
	}
	
	@Override
	public void convertToDropDown(UiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumDropdownElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(UiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumGenericElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(UI ui, UiElementProxy proxy) throws Exception {
		proxy.setConcreteElement(new AppiumGenericElement(ui, this.getAutomator(), proxy));
	}
	
	
	protected void setConcreteElement(UiMultiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(this.getAutomator(), proxy));
	}
	
	protected void setConcreteElement(UI ui, UiMultiElementProxy proxy) {
		proxy.setConcreteElement(new AppiumMultiElement(ui, this.getAutomator(), proxy));
	}
}
