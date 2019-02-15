package com.testmile.setu.agent.guiauto.tpi.element;

public interface GuiMultiElement {

	int getInstanceCount() throws Exception;

	GuiElement getInstanceAtIndex(int index) throws Exception;
}