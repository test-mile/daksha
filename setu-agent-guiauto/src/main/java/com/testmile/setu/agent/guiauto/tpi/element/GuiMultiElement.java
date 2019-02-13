package com.testmile.setu.agent.guiauto.tpi.element;

import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementStateHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.MultiElementInquirer;
import com.testmile.setu.agent.guiauto.tpi.handler.element.MultiElementStateHandler;

public interface GuiMultiElement {

	int getInstanceCount() throws Exception;

	GuiElement getInstanceAtIndex(int index) throws Exception;

	int getInstanceIndexByText(String text) throws Exception;

	int getInstanceIndexByTextContent(String text) throws Exception;

	MultiElementStateHandler getStateHandler() throws Exception;

	MultiElementInquirer getInquirer() throws Exception;

}