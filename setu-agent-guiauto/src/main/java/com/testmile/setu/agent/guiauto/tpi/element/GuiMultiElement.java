package com.testmile.setu.agent.guiauto.tpi.element;

public interface GuiMultiElement {

	int getInstanceCount() throws Exception;

	GuiElement getInstanceAtIndex(int index) throws Exception;

	int getInstanceIndexByText(String text) throws Exception;

	int getInstanceIndexByTextContent(String text) throws Exception;

}