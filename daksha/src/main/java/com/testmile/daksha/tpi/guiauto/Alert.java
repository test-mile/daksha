package com.testmile.daksha.tpi.guiauto;

import com.testmile.daksha.core.guiauto.automator.AppAutomator;

public interface Alert {
	
	void confirm() throws Exception;
	void dismiss() throws Exception;
	String getText() throws Exception;
	void sendText(String text) throws Exception;
	AppAutomator getAutomator();
	String getSetuId();
	
}
