package com.testmile.setu.agent.guiauto.tpi.handler.element;

public interface BasicActionsHandler {

	void focus() throws Exception;

	void enterText(String text) throws Exception;

	void setText(String text) throws Exception;

	void clearText() throws Exception;

	void submit() throws Exception;

	void click() throws Exception;

}