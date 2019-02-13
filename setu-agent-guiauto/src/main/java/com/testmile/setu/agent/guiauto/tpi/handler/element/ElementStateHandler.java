package com.testmile.setu.agent.guiauto.tpi.handler.element;

public interface ElementStateHandler {

	boolean isVisible() throws Exception;

	void waitUntilVisible() throws Exception;

	boolean isInvisible() throws Exception;

	void waitUntilInvisible() throws Exception;

	boolean isClickable() throws Exception;

	void waitUntilClickable() throws Exception;

	boolean isSelected() throws Exception;

}