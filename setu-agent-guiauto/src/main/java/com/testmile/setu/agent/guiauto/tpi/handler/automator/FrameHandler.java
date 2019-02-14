package com.testmile.setu.agent.guiauto.tpi.handler.automator;

import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;

public interface FrameHandler {

	void switchToFrameByIndex(int index) throws Exception;

	void switchToFrameByName(String name) throws Exception;

	void switchToDefaultFrame() throws Exception;

	void switchToParentFrame() throws Exception;

	void switchToFrameOfElement(GuiElement element) throws Exception;

}