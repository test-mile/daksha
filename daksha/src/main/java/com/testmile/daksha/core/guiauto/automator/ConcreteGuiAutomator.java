package com.testmile.daksha.core.guiauto.automator;

import com.testmile.daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import com.testmile.daksha.core.guiauto.identifier.GuiElementIdentifier;
import com.testmile.daksha.tpi.guiauto.automator.GuiAutomator;

public interface ConcreteGuiAutomator<D,E> extends GuiAutomator{
	
	GuiElementIdentifier<D,E> getIdentifier();
	D getGuiDriverEngine()  throws Exception;
	void setProxy(GuiAutomatorProxy proxy);
	GuiAutomatorProxy getProxy();
	void validatePageLoad();
	String getPageTitle();

}
