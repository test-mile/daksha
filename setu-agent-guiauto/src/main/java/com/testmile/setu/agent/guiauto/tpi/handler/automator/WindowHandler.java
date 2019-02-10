package com.testmile.setu.agent.guiauto.tpi.handler.automator;

public interface WindowHandler {

	void resizeWindowAsConfigured() throws Exception;

	void maximizeWindow();

	String getCurrentWindow();

	void switchToWindow(String windowHandle);

	void switchToNewWindow();

	void closeCurrentWindow();

	String getTitle();

}