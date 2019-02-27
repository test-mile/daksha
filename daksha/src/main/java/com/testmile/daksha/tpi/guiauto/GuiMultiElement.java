package com.testmile.daksha.tpi.guiauto;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.setu.SetuManagedObject;

public interface GuiMultiElement extends SetuManagedObject{

	AppAutomator getAutomator() throws Exception;

	GuiElement getInstanceAtIndex(int index);

}