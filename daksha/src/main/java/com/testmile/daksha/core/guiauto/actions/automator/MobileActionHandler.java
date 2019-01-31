package com.testmile.daksha.core.guiauto.actions.automator;

public interface MobileActionHandler {
	void swipeDown(int downCount) throws Exception;
	void swipeDown() throws Exception;
	public void swipeUp(int upCount)  throws Exception;
	void swipeUp()  throws Exception;
	
	void switchToNativeView() throws Exception;
	void switchToWebView() throws Exception;
	void switchToWebView(String pkg) throws Exception;
}
