package com.testmile.daksha.tpi.guiauto;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;

public interface Frame {
	void jump() throws Exception;
	void jumpToParent() throws Exception;
	void jumpToRoot() throws Exception;
	AppAutomator getAutomator();
	String getSetuId();
}
