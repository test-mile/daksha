package com.testmile.daksha.tpi.guiauto;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;
import com.testmile.daksha.core.setu.SetuManagedObject;

public interface GuiElement extends SetuManagedObject{

	void setText(String text) throws Exception;

	void click() throws Exception;

	void waitUntilClickable() throws Exception;

	AppAutomator getAutomator() throws Exception;

	void check() throws Exception;
	
	void uncheck() throws Exception;
	
	boolean isPartial() throws Exception;

}