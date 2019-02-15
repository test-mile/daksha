package com.testmile.setu.agent.guiauto.core.element;

import java.util.List;

import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public class DefaultGuiMultiElement implements GuiMultiElement {
	private List<GuiElement> elements;

	public DefaultGuiMultiElement(List<GuiElement> elements) {
		super();
		this.elements = elements;
	}

	@Override
	public int getInstanceCount() throws Exception {
		return this.elements.size();
	}
	
	@Override
	public GuiElement getInstanceAtIndex(int index) throws Exception {
		return this.elements.get(index);
	}

}