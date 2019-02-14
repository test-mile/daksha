package com.testmile.setu.agent.guiauto.tpi.handler.automator;

import java.util.Map;
import java.util.Set;

public interface WindowHandler {
	
	Map<String, Integer> getCurrentWindowSize() throws Exception;
	
	Set<String> getAllWindowHandles() throws Exception;

	void setWindowSize(int width, int height) throws Exception;

	void maximizeWindow();

	String getCurrentWindowHandle();

	void switchToWindow(String windowHandle);

	void closeCurrentWindow();

	String getTitle();

}