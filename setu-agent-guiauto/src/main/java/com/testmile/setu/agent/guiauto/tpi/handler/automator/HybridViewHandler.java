package com.testmile.setu.agent.guiauto.tpi.handler.automator;

public interface HybridViewHandler {

	void switchToNativeView() throws Exception;

	void switchToWebView() throws Exception;

	void switchToWebView(String pkg) throws Exception;

}