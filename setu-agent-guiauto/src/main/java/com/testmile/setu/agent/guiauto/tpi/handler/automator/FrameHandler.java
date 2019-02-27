package com.testmile.setu.agent.guiauto.tpi.handler.automator;

import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;

public interface FrameHandler {

	void switchToFrameByIndex(int index) throws Exception;

	void switchToFrameByName(String name) throws Exception;

	void jumpToHtmlRoot() throws Exception;

	void jumpToParentFrame() throws Exception;

	void jumpToFrame(GuiElement element) throws Exception;

}