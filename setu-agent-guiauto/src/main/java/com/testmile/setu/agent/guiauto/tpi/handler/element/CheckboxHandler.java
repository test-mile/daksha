package com.testmile.setu.agent.guiauto.tpi.handler.element;

public interface CheckboxHandler {

	void check() throws Exception;

	void uncheck() throws Exception;

	void toggleCheckbox() throws Exception;

	boolean isChecked() throws Exception;

}