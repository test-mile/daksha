package com.testmile.setu.agent.guiauto.tpi.handler.element;

import java.util.List;

public interface MultiElementStateHandler {

	void waitUntilVisible() throws Exception;

	void waitUntilClickable() throws Exception;
	
	List<Boolean> areSelected() throws Exception;

}