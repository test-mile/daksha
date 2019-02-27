package com.testmile.daksha.core.guiauto.gui;

import com.testmile.daksha.core.guiauto.automator.GuiAppAutomatorActionType;
import com.testmile.daksha.core.guiauto.setu.AbstractGuiAutoAction;
import com.testmile.daksha.tpi.guiauto.Gui;

public class GuiAction extends AbstractGuiAutoAction {
	
	public GuiAction(GuiAppAutomatorActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
	}

	public GuiAction(Gui parentGui, GuiActionType action) {
		super();
		this.getActionRequest().setAction(action.toString());
		this.getActionRequest().addArg("guiSetuId", parentGui.getSetuId());
	}

}
