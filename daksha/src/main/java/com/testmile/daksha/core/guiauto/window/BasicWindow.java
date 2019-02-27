package com.testmile.daksha.core.guiauto.window;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;

public interface BasicWindow {
	String getTitle() throws Exception;
	void jump() throws Exception;
	AppAutomator getAutomator();
	String getSetuId();
}
