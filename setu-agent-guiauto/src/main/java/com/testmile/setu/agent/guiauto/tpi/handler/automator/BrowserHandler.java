package com.testmile.setu.agent.guiauto.tpi.handler.automator;

public interface BrowserHandler {

	void goTo(String url) throws Exception;

	void refresh() throws Exception;

	void back() throws Exception;

	void forward() throws Exception;

	void close() throws Exception;

	//void validatePageLoad() throws Exception;

}