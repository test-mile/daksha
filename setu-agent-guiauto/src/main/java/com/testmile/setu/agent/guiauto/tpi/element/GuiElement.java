package com.testmile.setu.agent.guiauto.tpi.element;

import com.testmile.setu.agent.guiauto.tpi.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.tpi.handler.element.BasicActionsHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementInquirer;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementStateHandler;

public interface GuiElement {

	BasicActionsHandler getBasicActionsHandler() throws Exception;

	ElementInquirer getInquirer() throws Exception;

	ElementStateHandler getStateHandler() throws Exception;

	ElementFinder getElementFinder() throws Exception;

	void switchToFrame() throws Exception;

}