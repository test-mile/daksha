package com.testmile.setu.agent.guiauto.tpi.handler.automator;

public interface AlertHandler {

	void confirmAlert() throws Exception;
	
	void dismissAlert() throws Exception;
	
	void sendTextToAlert(String text) throws Exception;
	
	String getTextFromAlert() throws Exception;

	boolean isAlertPresent() throws Exception;

}