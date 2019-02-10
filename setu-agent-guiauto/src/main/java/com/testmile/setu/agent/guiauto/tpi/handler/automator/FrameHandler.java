package com.testmile.setu.agent.guiauto.tpi.handler.automator;

public interface FrameHandler {

	void switchToFrameByIndex(int index) throws Exception;

	void switchToFrameByName(String name) throws Exception;

	void switchToDefaultFrame() throws Exception;

}