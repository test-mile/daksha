package com.testmile.daksha.tpi.guiauto;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;

public interface GuiAutomator extends AppAutomator{

	void launch() throws Exception;
	void quit() throws Exception;

}