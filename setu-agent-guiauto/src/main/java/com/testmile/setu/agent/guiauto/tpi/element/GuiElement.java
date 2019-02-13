package com.testmile.setu.agent.guiauto.tpi.element;

import com.testmile.setu.agent.guiauto.core.handler.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.tpi.handler.element.BasicActionsHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.DropdownHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementFrameHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementInquirer;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementStateHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.RadioButtonHandler;

public interface GuiElement {

	BasicActionsHandler getBasicActionsHandler() throws Exception;

	ElementFrameHandler getFrameHandler() throws Exception;

	ElementInquirer getInquirer() throws Exception;

	ElementStateHandler getStateHandler() throws Exception;

	RadioButtonHandler getRadioHandler() throws Exception;

	ElementFinder getElementFinder() throws Exception;

	DropdownHandler asDropDown() throws Exception;

}