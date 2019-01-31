package com.testmile.daksha.core.guiauto.identifier.appium;

import com.testmile.daksha.core.guiauto.automator.ConcreteGuiAutomator;
import com.testmile.daksha.core.guiauto.element.appium.AppiumDropdownElement;
import com.testmile.daksha.core.guiauto.element.appium.AppiumGenericElement;
import com.testmile.daksha.core.guiauto.element.appium.AppiumMultiElement;
import com.testmile.daksha.core.guiauto.element.proxy.GuiElementProxy;
import com.testmile.daksha.core.guiauto.element.proxy.GuiMultiElementProxy;
import com.testmile.daksha.core.guiauto.identifier.selenium.BaseSeleniumElementIdentifier;
import com.testmile.daksha.tpi.guiauto.gui.Gui;
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
