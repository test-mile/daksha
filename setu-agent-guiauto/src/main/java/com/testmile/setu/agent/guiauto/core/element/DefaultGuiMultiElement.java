package com.testmile.setu.agent.guiauto.core.element;

import java.util.List;

import com.testmile.daksha.tpi.batteries.checks.Checks;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;
import com.testmile.setu.agent.guiauto.tpi.handler.element.InquirableElement;

public class DefaultGuiMultiElement implements GuiMultiElement {
	private List<GuiElement> elements;

	public DefaultGuiMultiElement(List<GuiElement> elements) {
		super();
		this.elements = elements;
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiMultiElement#getInstanceCount()
	 */
	@Override
	public int getInstanceCount() throws Exception {
		return this.elements.size();
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiMultiElement#getInstanceAtIndex(int)
	 */
	@Override
	public GuiElement getInstanceAtIndex(int index) throws Exception {
		return this.elements.get(index);
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiMultiElement#getInstanceIndexByText(java.lang.String)
	 */
	@Override
	public int getInstanceIndexByText(String text) throws Exception {
		for(int i=0; i < elements.size(); i++) {
			if (Checks.isEqual(elements.get(i).getInquirer().getText(), text)) {
				return i;
			}
		}
		throw new Exception("None of the element instances has the specified text.");
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.element.GuiMultiElement#getInstanceIndexByTextContent(java.lang.String)
	 */
	@Override
	public int getInstanceIndexByTextContent(String text) throws Exception {
		for(int i=0; i < elements.size(); i++) {
			if (Checks.contains(elements.get(i).getInquirer().getText(), text)) {
				return i;
			}
		}
		throw new Exception("None of the element instances has the specified text content.");
	}
}