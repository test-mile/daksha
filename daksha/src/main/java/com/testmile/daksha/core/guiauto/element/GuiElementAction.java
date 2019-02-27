package com.testmile.daksha.core.guiauto.element;

import com.testmile.daksha.core.guiauto.setu.AbstractGuiAutoAction;
import com.testmile.daksha.tpi.guiauto.GuiElement;

public class GuiElementAction extends AbstractGuiAutoAction {
	
	public GuiElementAction(GuiElement element, GuiElementActionType action) throws Exception {
		super();		
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("automatorSetuId", element.getAutomator().getSetuId());
		this.getActionRequest().addArg("elementSetuId", element.getSetuId());
		if (element.isPartial()) {
			this.getActionRequest().addArg("isInstanceAction", true);
			this.getActionRequest().addArg("instanceIndex", true);
		}
	}

}
